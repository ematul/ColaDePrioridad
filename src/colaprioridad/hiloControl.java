/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author enrique
 */
public class hiloControl implements Runnable{

    ejecucionProceso hilo1;
    ejecucionProceso hilo2;
    ejecucionProceso hilo3;
    ejecucionProceso hilo4;
    ejecucionProceso hilo5;
    Thread t;
    
    int prioridad1 = 0;
    int prioridad2 = 0;
    int prioridad3 = 0;
    int prioridad4 = 0;
    int prioridad5 = 0;

    public hiloControl(ejecucionProceso hilo1, ejecucionProceso hilo2, ejecucionProceso hilo3, ejecucionProceso hilo4, ejecucionProceso hilo5) {
        this.hilo1 = hilo1;
        this.hilo2 = hilo2;
        this.hilo3 = hilo3;
        this.hilo4 = hilo4;
        this.hilo5 = hilo5;
        this.t = new Thread(this);
        this.t.start();
    }

    @Override
    public void run() 
    {
        while(true)
            {
                //nivel 1 /// 64 cuantos - Ejemplo 15
                boolean bandera = true;
                int cuantos = hilo1.nivel.getCantidadCuantos();
                int cont = 0;
                while(bandera)
                {
                    int resultado = hilo1.ejecutar();
                    cont++;
                    prioridad1 = cont;
                    if(cont == cuantos || resultado == 0 )
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //nivel 2 /// 32 cuantos - Ejemplo 12
                bandera = true;
                cuantos = hilo2.nivel.getCantidadCuantos();
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo2.ejecutar();
                    cont++;
                    prioridad2 = cont;
                    if(cont == cuantos || resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //nivel 3 /// 16 cuantos - Ejemplo 9 cuantos
                bandera = true;
                cuantos = hilo3.nivel.getCantidadCuantos();
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo3.ejecutar();
                    cont++;
                    prioridad3 = cont;
                    if(cont == cuantos || resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //nivel 2 /// 6 cuantos
                bandera = true;
                cuantos = hilo4.nivel.getCantidadCuantos();
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo4.ejecutar();
                    cont++;
                    prioridad4 = cont;
                    if(cont == cuantos || resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //nivel 5 /// 3 cuantos
                bandera = true;
                cuantos = hilo5.nivel.getCantidadCuantos();
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo5.ejecutar();
                    cont++;
                    prioridad5 = cont;
                    if(cont == cuantos || resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        
    }
    
    public int getPrioridad1(){
       
        return prioridad1;
    }
    
    public int getPrioridad2(){
        
        return prioridad2;
    }
    
    public int getPrioridad3(){
        
        return prioridad3;
    }
    
    public int getPrioridad4(){
       
        return prioridad4;
    }
    
    public int getPrioridad5(){
        
        return prioridad5;
    }
}
