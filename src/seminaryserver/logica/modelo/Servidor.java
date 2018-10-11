/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.logica.modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ariel Arnedo
 */
public class Servidor {
    
    private final Integer puerto;
    private java.net.ServerSocket serverSocket;
    private final java.util.List<Cliente> clientes;

    public Servidor(Integer puerto) {
        this.puerto = puerto;
        clientes = new java.util.ArrayList<>();
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
    
    public void conectar() throws RuntimeException{
        Runnable run = new Runnable() {

            @Override
            public void run() {
                try {
                    serverSocket = new ServerSocket(puerto);
                } catch (IOException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    throw new RuntimeException(ex.getMessage());
                }
                while(serverSocket.isBound()){
                    try{
                        Socket conexionCliente = serverSocket.accept();
                        Cliente cliente = new Cliente(conexionCliente);
                        clientes.add(cliente);
                        cliente.capturarInformacionCliente();
                        
                    }catch(IOException ex){
                    }
                }
            }
        };
        Servidor.runThread(run);
    }
    
    public void desconectar()  {
        boolean error = false;
        for(Cliente cliente : clientes){
            try {
                cliente.desconectar();
            } catch (IOException ex) {
                error = true;
            }
        }
        
        clientes.clear();
        try {
            serverSocket.close();
        } catch (IOException ex) {}
        
        if(error){
             throw new RuntimeException("Algunos Clientes no fueron notificados");
        }
    }
    
    static void runThread(Runnable run){
        new Thread(run).start();
    }
}
