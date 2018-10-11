/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista;

/**
 *
 * @author Klac
 */
public interface SwingAttribute {
    
    public int BUSCAR = 1;
    public int AÑADIR_PALABRA = 3;
    
    public java.awt.Font FONT_MAIN = new java.awt.Font("Arial", 1, 17);
    public java.awt.Color COLOR_BACKGROUND = new java.awt.Color(250, 250, 255);
    public java.awt.Color COLOR_FOREGROUND = new java.awt.Color(5, 132, 249,255);
    public javax.swing.border.Border BORDER_MAIN = javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED, new java.awt.Color(152, 204, 255), new java.awt.Color(152, 204, 255));
}
