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
                //nivel 1 /// 64 cuantos
                boolean bandera = true;
                int cuantos = hilo1.nivel.getCantidadCuantos();
                System.out.println(cuantos);
                int cont = 0;
                while(bandera)
                {
                    int resultado = hilo1.ejecutar();
                    //System.out.println(resultado);
                    cont++;
                    prioridad1 = cont;
                    if(cont == cuantos)
                    {
                        bandera = false;
                    }
                    if(resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //nivel 2 /// 32 cuantos
                bandera = true;
                cuantos = hilo2.nivel.getCantidadCuantos();
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo2.ejecutar();
                    //System.out.println(resultado);
                    cont++;
                    prioridad2 = cont;
                    if(cont == cuantos)
                    {
                        bandera = false;
                    }
                    if(resultado == cuantos)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
                //nivel 3 /// 16 cuantos
                bandera = true;
                cuantos = hilo3.nivel.getCantidadCuantos();
                System.out.println(cuantos);
                cont = 0;
              
                while(bandera)
                {
                    int resultado = hilo3.ejecutar();
                    //System.out.println(resultado);
                    cont++;
                    prioridad3 = cont;
                    System.out.println(cont + "priori333");
                    if(cont == cuantos)
                    {
                        bandera = false;
                    }
                    if(resultado == cuantos)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //nivel 2 /// 8 cuantos
                bandera = true;
                cuantos = hilo4.nivel.getCantidadCuantos();
                System.out.println(cuantos);
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo4.ejecutar();
                    //System.out.println(resultado);
                    cont++;
                    prioridad4 = cont;
                    if(cont == cuantos)
                    {
                        bandera = false;
                    }
                    if(resultado == 0)
                    {
                        bandera = false;
                    }
                    try {
                        sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                //nivel 5 /// 4 cuantos
                bandera = true;
                cuantos = hilo5.nivel.getCantidadCuantos();
               
                cont = 0;
                while(bandera)
                {
                    int resultado = hilo5.ejecutar();
                    //System.out.println(resultado);
                    cont++;
                    prioridad5 = cont;
                    if(cont == cuantos)
                    {
                        bandera = false;
                    }
                    if(resultado == 0)
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
