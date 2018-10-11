  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 
package seminaryserver.vista.componentesUI;

import java.util.List;
import seminaryserver.logica.modelo.Cliente;
import seminaryserver.logica.modelo.Conexion;

/**
 *
 * @author Ariel Arnedo
 */
public class TableModelData extends javax.swing.table.AbstractTableModel implements Conexion{
 
    private java.util.List<Cliente> lista;
 
    public TableModelData() {
        this.lista = new java.util.ArrayList<>();
    }
     
    public java.util.List<Cliente> getLista() {
        return lista;
    }
    
    public void addList(Cliente cliente) {
        int side = lista.size();
        if(lista.add(cliente))
            fireTableRowsInserted(side, side+1);
    }

    public Cliente getClienteHost(int fila){
        return lista.get(fila);
    }
 
    public void update(){
        fireTableDataChanged();
    }
     
    @Override
    public int getRowCount() {
        return (lista == null)? 0:lista.size();
    }
 
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
 
    @Override
    public Class getColumnClass(int column){
        return String.class;
    }
     
    @Override
    public int getColumnCount() {
        return 4;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return getAtributeFeature(lista.get(rowIndex), columnIndex,rowIndex);
    }
     
    private Object getAtributeFeature(Cliente cliente, int column, int row){
        switch(column){
            case 0: return row+1;
            case 1: return cliente.getClienteIP();
            case 2: return cliente.getNombreDeHost();
            case 3:{
                if(cliente.getEstado() == BLOQUEADO)
                    return "BLOQUEADO";
                else if(cliente.getEstado() == DESBLOQUEADO)
                    return "DESBLOQUEADO";
                else
                    return "INACTIVO";
            }
            default: return null;
        }
    }  
}
