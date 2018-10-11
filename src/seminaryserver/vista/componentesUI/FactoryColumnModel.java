/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.vista.componentesUI;

/**
 *
 * @author Ariel Arnedo
 */
public class FactoryColumnModel {
    
    public static javax.swing.table.TableColumnModel createModelColumnTableMain(){
        javax.swing.table.DefaultTableColumnModel tablaColumn = new  javax.swing.table.DefaultTableColumnModel();
        tablaColumn.setColumnMargin(0);
        int ancho[] ={15,100,135,88};
        String cabeceras[] = {" No ","IP","Nombre de Host","Estado"}; 
        for(int i = 0; i < cabeceras.length ; i++){
                javax.swing.table.TableColumn columna = new javax.swing.table.TableColumn(i,ancho[i]);
                columna.setHeaderValue(cabeceras[i]);
                javax.swing.table.DefaultTableCellRenderer rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(-1);
                columna.setHeaderRenderer(rowRenderer);
                if(i == 5){
                    rowRenderer = FactoryCellTableRenderer.createTableCellRendererProcess();
                }else{
                    rowRenderer = FactoryCellTableRenderer.createTableCellRenderer(i);
                }
                columna.setCellRenderer(rowRenderer);
                tablaColumn.addColumn(columna);
        }
        return tablaColumn;
    }
    
}
