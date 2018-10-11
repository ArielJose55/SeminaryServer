  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.logica.modelo;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static seminaryserver.logica.modelo.Conexion.BLOQUEADO;
import seminaryserver.logica.util.Notificador;


/**
 *
 * @author Ariel Arnedo
 */
public class Cliente implements Conexion{
    
    private final Socket clienteSocket;
    private String clienteIP;
    private String nombreDeHost;
    private java.util.List<String> mensajes;
    private Integer estado = INACTIVO;
    private final javax.swing.JTextArea areaChat ;

    public Cliente(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        this.areaChat = new javax.swing.JTextArea();
        this.areaChat.setEditable(false);
    }
    

    public void setClienteIP(String clienteIP) {
        this.clienteIP = clienteIP;
    }

    public void setNombreDeHost(String nombreDeHost) {
        this.nombreDeHost = nombreDeHost;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getClienteIP() {
        return clienteIP;
    }

    public String getNombreDeHost() {
        return nombreDeHost;
    }

    public Integer getEstado() {
        return estado;
    }
    
    public javax.swing.JScrollPane getScrollPanelChat(){
        return new javax.swing.JScrollPane(areaChat);
    }
    
    public void capturarInformacionCliente(){
        Runnable run = new Runnable() {

            @Override
            public void run() {
                java.io.DataInputStream entrada = null;
                try{
                    entrada = new java.io.DataInputStream(clienteSocket.getInputStream());
                    while(estado == INACTIVO){
                        String mensaje = entrada.readUTF();
                        if( !(mensaje.contains("$$&&$$") || mensaje.contains("$$%%$$"))){
                            extraerInformacion(mensaje);
                            Notificador.getNotificador().notificar(Cliente.this);
                            recibirMensaje();        
                        }
                    }
                }catch(java.io.IOException ex){
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Servidor.runThread(run);
    }
    
    
    /**
     * metodo que contiene un hilo que escucha constantemente la informacion que puede enviar el cliente
     */
    private void recibirMensaje(){
        Runnable run = new Runnable() {
            @Override
            public void run() { 
                while(clienteSocket.isConnected()){
                    if(estado != INACTIVO){
                        java.io.DataInputStream entrada = null;
                        try {
                            entrada = new java.io.DataInputStream(clienteSocket.getInputStream());
                            String mensaje = entrada.readUTF();
                            if(mensaje.contains("$$&&$$")){
                                areaChat.setText(getTextBuilder(false ,mensaje.substring(6 , mensaje.length())).toString());
                            }else if(mensaje.startsWith("$$%%$$")){
                                System.out.println(mensaje);
                                if(mensaje.substring(6 , mensaje.length()).contains("BLOQUEAR")){
                                    Cliente.this.estado = BLOQUEADO;
                                }else{
                                    Cliente.this.estado = DESBLOQUEADO;
                                }
                                Notificador.getNotificador().notificar("ACTUALIZAR_TABLA");
                            }else{
                                throw new RuntimeException("Mensaje No procesado");
                            }                       
                        } catch (java.io.IOException ex) {}
                    }
                }
            }
        };
        Servidor.runThread(run);
    }
    
    /**
     * envia un mensaje a cliente
     * @param mensaje 
     */
    public void enviarMensaje(final String mensaje){
        Runnable run = new Runnable() {

            @Override
            public void run() {
                if(clienteSocket.isConnected()){
                    java.io.DataOutputStream salida = null;
                    try {
                        salida = new java.io.DataOutputStream(clienteSocket.getOutputStream());
                        salida.writeUTF(mensaje);
                        salida.flush();
                        areaChat.setText(getTextBuilder(true, mensaje).toString());
                    } catch (java.io.IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        Servidor.runThread(run);
    }

    public void desconectar() throws IOException{
        if(clienteSocket != null && clienteSocket.isConnected()){
            Runnable run = new Runnable() {

                @Override
                public void run() {
                    java.io.DataOutputStream salida = null;
                    try {
                        salida = new java.io.DataOutputStream(clienteSocket.getOutputStream());
                        salida.writeUTF("DesconexionÑÑÑ");
                        salida.flush();
                        clienteSocket.close();
                    } catch (java.io.IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            };
        }
    }
    
    /**
     * añade el tipo de host, server o cliente a la cadena de texto que procesa
     * 
     * @param io si es true el mensaje es del server, sino es del cliente
     * @param mensaje cadena de texto
     * @return retorna la cadena de texto, con su respectiva etiqueta.
     */
    private StringBuilder getTextBuilder(boolean io, String mensaje){
        StringBuilder sb = new StringBuilder();
        sb.append(areaChat.getText());
        sb.append("\n");
        sb.append( io ? "Server" : nombreDeHost);
        sb.append(": ");
        sb.append(mensaje);
        return sb;
    }
    
    
    /**
     * extrae la informacion de la cadena de texto, obteniendo el nombre de host, es estado (desbloqueado,bloqueado)
     * @param string cadena de texto con la informacion
     */
    private void extraerInformacion(String string){ 
        String [] datos = string.split(" ");
        this.clienteIP = datos[0];
        this.nombreDeHost = datos[1];
        if(datos[2].compareTo("6") == 0)
            this.estado = BLOQUEADO;
        else if(datos[2].compareTo("7") == 0)
             this.estado = DESBLOQUEADO;
        else
             this.estado = INACTIVO;
    }
    
}
