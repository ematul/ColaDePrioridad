/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrique
 */
public class hiloCreacionProcesos implements Runnable{

    ArrayList<nodoProceso> tabla;
    nodoPrioridad nivel1;
    nodoPrioridad nivel2;
    nodoPrioridad nivel3;
    nodoPrioridad nivel4;
    nodoPrioridad nivel5;
    
    boolean suspender;
    
    Thread t;

    public hiloCreacionProcesos(ArrayList<nodoProceso> tabla, nodoPrioridad nivel1, nodoPrioridad nivel2, nodoPrioridad nivel3, nodoPrioridad nivel4, nodoPrioridad nivel5) {
    
        this.tabla = tabla;
        this.nivel1 = nivel1;
        this.nivel2 = nivel2;
        this.nivel3 = nivel3;
        this.nivel4 = nivel4;
        this.nivel5 = nivel5;
        
        this.suspender =  false;
        
        t = new Thread (this);
        t.start();
    }
    
    @Override
    public void run() {
        int idProceso = 0;
        while(true)
        {
            
            int cantidadProcesos = tabla.size();
            
            try {
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(hiloCreacionProcesos.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            if(cantidadProcesos < 15)
            {
                System.out.println("Creo");
                idProceso ++;
                nodoProceso no;
                int prioridad = (int)(Math.random()*5);
                no =  new nodoProceso();
                no.setNombre("PROCESO " + idProceso);
                no.setDuracion(Math.random()*10);
                no.setPrioridad(prioridad);
                
                if(prioridad == 0)
                {
                    nivel1.getListaProcesos().ingresoProceso(no);
                }
                else if(prioridad == 1)
                {
                    nivel2.getListaProcesos().ingresoProceso(no);
                }
                else if (prioridad  == 2)
                {
                    nivel3.getListaProcesos().ingresoProceso(no);
                }
                else if (prioridad  == 3)
                {
                    nivel4.getListaProcesos().ingresoProceso(no);
                }
                else
                {
                    nivel5.getListaProcesos().ingresoProceso(no);
                }

                tabla.add(no);
                try {
                    Thread.sleep(100000 * (int)Math.random());
                } catch (InterruptedException ex) {
                    Logger.getLogger(hiloCreacionProcesos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //Suspender
                synchronized(this)
                {
                    while(suspender)
                    {
                        try 
                        {
                            wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(hiloCreacionProcesos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            
            }
            
        }
    }
    
    void pausa() {
    suspender = true;
  }
  synchronized void continuar() {
    suspender = false;
    notify();
  }
    
    
}
    
