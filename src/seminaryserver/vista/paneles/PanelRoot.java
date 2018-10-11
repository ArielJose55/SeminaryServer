/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista.paneles;

import seminaryserver.vista.SwingAttribute;



/**
 *
 * @author Klac
 */
public abstract class PanelRoot extends javax.swing.JPanel{

    public PanelRoot() {
        initComponents();
    }
    
    private void initComponents(){
        setBackground(SwingAttribute.COLOR_BACKGROUND);
    }
    
    protected javax.swing.JButton createButton(){
        javax.swing.JButton boton = new javax.swing.JButton();
        boton.setFocusPainted(false);
        boton.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        boton.setBackground(SwingAttribute.COLOR_BACKGROUND);
        boton.setForeground(SwingAttribute.COLOR_FOREGROUND);
        boton.setFont(new java.awt.Font("Arial", 1, 17));
        boton.setBorder(SwingAttribute.BORDER_MAIN);
        return boton;
    }
    
    protected javax.swing.JButton createButtonSecondary(){
        javax.swing.JButton boton = new javax.swing.JButton();
        boton.setFocusPainted(false);
        boton.setCursor(java.awt.Cursor.getPredefinedCursor(java.awt.Cursor.HAND_CURSOR));
        boton.setBorder(SwingAttribute.BORDER_MAIN);
        boton.setForeground(SwingAttribute.COLOR_FOREGROUND);
        boton.setFont(new java.awt.Font("Arial", 0, 15));
        return boton;
    }
    
    protected javax.swing.JTextField createTextField(){
        final javax.swing.JTextField campo = new javax.swing.JTextField();
        campo.setFont(new java.awt.Font("Arial", 0, 17));
        campo.setForeground(new java.awt.Color(102, 102, 102));
        campo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        return campo;
    }
    
    protected javax.swing.JTextField createNumberField(){
        final javax.swing.JTextField campo = createTextField();
        javax.swing.text.Document document = new javax.swing.text.PlainDocument(){

            @Override
            public void insertString(int offs, String str, javax.swing.text.AttributeSet a) throws javax.swing.text.BadLocationException {
                char fuente[] = str.toCharArray();
                char resultado[] = new char[fuente.length];
                int j = 0;
                for(int i = 0 ; i < fuente.length ; i++){
                    if(fuente[i] >= '0' && fuente[i] <= '9'){
                        resultado[j++] = fuente[i];
                    }
                }
                super.insertString(offs, new String(resultado, 0, j), a);
            }
          
        };
        campo.setDocument(document);
        return campo;
    }
    
//    
//    protected abstract StringBuilder verificaCampos();
    
    protected boolean isCampoVacio(javax.swing.JTextField campo){
        return (campo == null) ? false : campo.getText().compareTo("") == 0;
    }
}
