/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista.componentesUI;

import seminaryserver.vista.SwingAttribute;

/**
 *
 * @author Ariel Arnedo
 */
class FactoryCellTableRenderer{
    
    static javax.swing.table.DefaultTableCellRenderer createTableCellRenderer(int columna){
//        if(typeTable.compareTo(TypeTable.TABLE_MAIN) == 0){
            return new RowRendererOfClassOne(columna);
//        }else{
//            return new RowRendererOfClassTwo(columna);
//        }
    }
    
    static javax.swing.table.DefaultTableCellRenderer createTableCellRendererProcess(){
        return new RowRendererOfClassTree();
    }
    
    private static class RowRendererOfClassOne extends javax.swing.table.DefaultTableCellRenderer{
        private final int columna;

        RowRendererOfClassOne(int columna) {
            this.columna = columna;
        }
        
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBorder(null);
            setBackground(java.awt.Color.WHITE);
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

            if(columna < 0){
                setPreferredSize(new java.awt.Dimension(12, 36));
                setFont(new java.awt.Font("Tahoma", 1, 19));
                setForeground(SwingAttribute.COLOR_FOREGROUND);
                return this;
            }
            if(isSelected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }else{
                setForeground(new java.awt.Color(39,38,39));
            }
            setFont(new java.awt.Font("Arial", 0, 18));
            
            return this;
        }
    }
    
//    private static class RowRendererOfClassTwo extends javax.swing.table.DefaultTableCellRenderer{
//        
//        private final int columna;
//
//        RowRendererOfClassTwo(int columna) {
//            this.columna = columna;
//        }
//        
//        @Override
//        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
//            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
//            setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
//            setBackground(java.awt.Color.WHITE);
//            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
//            if(columna < 0){
//                setPreferredSize(new java.awt.Dimension(12, 40));
//                setFont(new java.awt.Font("Arial", 1, 20));
//                setForeground(new java.awt.Color(120,120,120));
//                return this;
//            }
//          
//            if(isSelected){
//                setBackground(new java.awt.Color(228,240,255));
//                setForeground(table.getSelectionForeground());
//            }else{
//                setForeground(new java.awt.Color(130,130,130));
//                
//            }
//            if(table.getRowCount() - 1 == row)
//                setFont(new java.awt.Font("Arial", 1, 17));
//            else
//                setFont(new java.awt.Font("Arial", 0, 17));
//            return this;
//        }
//    }
    
    private static class RowRendererOfClassTree extends javax.swing.table.DefaultTableCellRenderer{

        private final javax.swing.ImageIcon images[] = {new javax.swing.ImageIcon(getClass().getResource("/prociencia2/views/resources/Procesado28x28.png")),
                                                        new javax.swing.ImageIcon(getClass().getResource("/prociencia2/views/resources/NoProcesado28x28.png"))};
        
        RowRendererOfClassTree() {
        }
        
        @Override
        public java.awt.Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            
             javax.swing.table.TableModel model = table.getModel(); 
             boolean winner = ((Boolean) model.getValueAt(table.convertRowIndexToModel(row), 
                     5)).booleanValue();
             
             if(winner){
                 setIcon(images[0]);
             }else{
                 setIcon(images[1]);
             }
            setText("");
            setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
            setBackground(java.awt.Color.WHITE);
            setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            if(isSelected){
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            }else{
                setForeground(new java.awt.Color(39,38,39));
            }

            return this;
        }
    }
}
