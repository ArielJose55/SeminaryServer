/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista.ventanas;


import java.util.logging.Level;
import java.util.logging.Logger;
import seminaryserver.vista.SwingAttribute;





/**
 *
 * @author Klac
 */
public class VentanaRoot extends javax.swing.JFrame{
    
    private javax.swing.JPanel panelWorkCenter;
    
    public VentanaRoot(String title) {
        super(title);
        getContentPane().setBackground(SwingAttribute.COLOR_BACKGROUND);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        
    }
    
    public VentanaRoot(String title , boolean isMaxinized) {
        super(title);
        setExtendedState(isMaxinized ? javax.swing.JFrame.MAXIMIZED_BOTH : javax.swing.JFrame.NORMAL);
        getContentPane().setBackground(SwingAttribute.COLOR_BACKGROUND);
        setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
    }
    
    protected void setPanelWorkCenter(javax.swing.JPanel panelWorkCenter) {
        this.panelWorkCenter = panelWorkCenter;
    }

    
   protected javax.swing.JButton createButton(){
        javax.swing.JButton boton = new javax.swing.JButton();
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        boton.setBackground(SwingAttribute.COLOR_BACKGROUND);
        boton.setForeground(SwingAttribute.COLOR_FOREGROUND);
        return boton;
    }
    
    protected javax.swing.JButton createButtonMenu(){
        javax.swing.JButton boton = createButton();
        boton.setBorderPainted(true);
        boton.setBorder(seminaryserver.vista.SwingAttribute.BORDER_MAIN);
        boton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        boton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        boton.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 19));
        return boton;
    }
    
    protected javax.swing.JTextField createTextField(){
        final javax.swing.JTextField campo = new javax.swing.JTextField();
        campo.setFont(new java.awt.Font("Tahoma", 0, 12));
        campo.setForeground(new java.awt.Color(102, 102, 102));
        campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return campo;
    }
    
    
    protected void runDialog(final Class dialogClass){
        Runnable run = new Runnable(){
            public void run(){
                try {
                    javax.swing.JDialog dialog = (javax.swing.JDialog)dialogClass.newInstance();
                    dialog.setVisible(true);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VentanaRoot.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VentanaRoot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        runThread(run);
    }
    
    protected void runDialog(final Class dialogClass,final VentanaRoot mainFrame){
        Runnable run = new Runnable(){
            public void run(){
                try {
                    javax.swing.JDialog dialog = (javax.swing.JDialog)dialogClass.newInstance();
                    dialog.setVisible(true);
                } catch (InstantiationException ex) {
                    Logger.getLogger(VentanaRoot.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(VentanaRoot.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        runThread(run);
    }
    
    protected void runThread(Runnable run){
        new Thread(run).start();
    }
    
    protected void waitingPanel(){
        if(panelWorkCenter != null){
            panelWorkCenter.removeAll();
            panelWorkCenter.add(
                    new javax.swing.JLabel(new javax.swing.ImageIcon(getClass().getResource("/seminaryclient/vista/recursos/waiting.gif"))),
                    java.awt.BorderLayout.CENTER
            );
            panelWorkCenter.updateUI();
        }
    }
    
    protected void stoppingWaitPanel(){
        if(panelWorkCenter != null){
            panelWorkCenter.removeAll();
        }
        panelWorkCenter.updateUI();
    }
    
    
    protected void changePanelWork(javax.swing.JPanel panel){
        panelWorkCenter.removeAll();
        panelWorkCenter.add(panel, java.awt.BorderLayout.CENTER);
        panelWorkCenter.updateUI();
    }
}
