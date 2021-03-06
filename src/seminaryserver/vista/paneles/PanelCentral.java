/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista.paneles;

import java.awt.event.ActionEvent;
import java.util.Observable;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import seminaryserver.logica.modelo.Cliente;
import seminaryserver.logica.modelo.Conexion;
import seminaryserver.logica.modelo.Servidor;
import seminaryserver.logica.util.Notificador;
import seminaryserver.vista.componentesUI.*;

/**
 *
 * @author Ariel Arnedo
 */
public class PanelCentral extends PanelRoot implements java.util.Observer, Conexion{

    private final Servidor servidor;
    
    private TableModelData modelTable;
    
    public PanelCentral(Servidor servidor) {
        this.servidor = servidor;
        modelTable = new TableModelData();
        initComponents();
        Notificador.getNotificador().añadirObsevador(PanelCentral.this);
    }


    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tableMain = new javax.swing.JTable();
        panelViewChat = new javax.swing.JPanel();
        campoChatServer = createTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        tableMain.setModel(modelTable);
        tableMain.setFillsViewportHeight(true);
        tableMain.setShowVerticalLines(false);

        tableMain.setColumnModel(FactoryColumnModel.createModelColumnTableMain());
        tableMain.setRowHeight(32);
        tableMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMainMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableMain);

        panelViewChat.setBackground(getBackground());
        panelViewChat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 204, 255)));
        panelViewChat.setLayout(new java.awt.BorderLayout());

        campoChatServer.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        campoChatServer.requestFocus();
        campoChatServer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoChatServerActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/seminaryserver/vista/recursos/answer-email-symbol.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        jLabel2.setText("Server");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
                    .addComponent(panelViewChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(campoChatServer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelViewChat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(campoChatServer)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableMainMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMainMouseClicked
        if(evt.getButton() == java.awt.event.MouseEvent.BUTTON1 && evt.getClickCount() == 2){
            if(!tableMain.getSelectionModel().isSelectionEmpty()){
                Cliente cliente = ((TableModelData)tableMain.getModel()).getClienteHost(tableMain.getSelectedRow());
                createPopupMenu(cliente).show(tableMain, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_tableMainMouseClicked

    private void campoChatServerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoChatServerActionPerformed
        if(!tableMain.getSelectionModel().isSelectionEmpty() && campoChatServer.getText().compareTo("") != 0){
                Cliente cliente = ((TableModelData)tableMain.getModel()).getClienteHost(tableMain.getSelectedRow());
                cliente.enviarMensaje(campoChatServer.getText());
                campoChatServer.setText("");
            }
    }//GEN-LAST:event_campoChatServerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField campoChatServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelViewChat;
    private javax.swing.JTable tableMain;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof Cliente){
            modelTable.addList((Cliente) arg);
        }else if(arg instanceof String){
            if(String.valueOf(arg).compareTo("ACTUALIZAR_TABLA") == 0){
                modelTable.update();
                System.out.println("Actualizar Tabla");
            }
        }
    }


    /***
     * crea un menu emergente, que aparece cuando el usuario da click en algun registro de la tabla
     * @param cliente cliente obtenido de la tabla de clientes conectados
     * @return retorna un menu emergente
     */
    protected JPopupMenu createPopupMenu(Cliente cliente) {
        JPopupMenu menu = new JPopupMenu();
        JMenuItem item = new JMenuItem("Apagar");
        item.setActionCommand("Apagar");
        item.setEnabled(cliente.getEstado() != INACTIVO);
        item.addActionListener(new PopupMenuItemListener(cliente));
        menu.add(item);
        menu.addSeparator();
        
        item = new JMenuItem("Bloquear");
        item.setActionCommand("Bloquear");
        item.addActionListener(new PopupMenuItemListener(cliente));
        item.setEnabled(cliente.getEstado() != INACTIVO && cliente.getEstado() != BLOQUEADO);
        menu.add(item);
        
        item = new JMenuItem("Desbloquear");
        item.setActionCommand("Desbloquear");
        item.addActionListener(new PopupMenuItemListener(cliente));
        item.setEnabled(cliente.getEstado() != INACTIVO && cliente.getEstado() != DESBLOQUEADO);
        menu.add(item);
        menu.addSeparator();
        
        item = new JMenuItem("Abrir Chat");
        item.setActionCommand("Abrir Chat");
        item.addActionListener(new PopupMenuItemListener(cliente));
        item.setEnabled(cliente.getEstado() != INACTIVO && cliente.getEstado() != BLOQUEADO);
        menu.add(item);
        
        return menu;
    }
    
    private final class PopupMenuItemListener implements java.awt.event.ActionListener{

        private final Cliente cliente;

        public PopupMenuItemListener(Cliente cliente) {
            this.cliente = cliente;
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()){
                case "Apagar":{
                    cliente.enviarMensaje("ApagarÑÑÑ");
                    break;
                }
                case "Bloquear":{
                    cliente.enviarMensaje("BloquearÑÑÑ");
                    break;
                }
                case "Desbloquear":{
                    cliente.enviarMensaje("DesbloquearÑÑÑ");
                    break;
                }
                case "Abrir Chat":{
                    panelViewChat.removeAll();
                    panelViewChat.add(cliente.getScrollPanelChat(),java.awt.BorderLayout.CENTER);
                    panelViewChat.updateUI();
                    break;
                }
            }
        }
        
    }
}
