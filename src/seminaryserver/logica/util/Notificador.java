/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package seminaryserver.logica.util;

import java.util.Observable;
import java.util.Observer;
 
/**
 *
 * @author Klac
 */
public class Notificador extends Observable{
  
    private static Notificador notificador = null;
     
    public static Notificador getNotificador(){
        if(notificador == null){
            notificador = new Notificador();
        }
        return notificador;
    }
     
    public void notificar(Object obj){
        this.setChanged();
        notificador.notifyObservers(obj);
    }
     
    public void añadirObsevador(Observer obser){
        notificador.addObserver(obser);
    }
}