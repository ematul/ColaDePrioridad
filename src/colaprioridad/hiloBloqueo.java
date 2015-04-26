/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrique
 */
public class hiloBloqueo implements Runnable {

    nodoProceso actual;
    Thread t;
    
    public hiloBloqueo(nodoProceso proceso) {
        this.actual = proceso;
        this.t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        
        double duracion = actual.getDuracion()-actual.getTiempoTranscurrido();
        double tBloqueo = (Math.random()*duracion);
        
        
        actual.setEstado(2);
        
        
        boolean bandera = true;
        
        while(bandera)
        {
            try {
                    tBloqueo -= 0.01;
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(hiloBloqueo.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            if(tBloqueo <= 0)
            {
                bandera = false;
            }
        }
        
        actual.setEstado(0);
        
        
    }
    
    
}
