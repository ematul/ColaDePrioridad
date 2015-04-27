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
public class ejecucionProceso {
    
    String nombre; // nombre del hilo
    nodoPrioridad nivel; // nivel de prioridad
    nodoProceso actual; // proceso actual
    ArrayList <nodoProceso> tablaProceso ;
    int tiempo;
  
  ejecucionProceso(String nombre, nodoPrioridad nivel, ArrayList <nodoProceso> tabla) 
  {
      this.nombre = nombre;
      this.nivel = nivel;
      this.tablaProceso = tabla;
      this.tiempo = 50;
 }
    
  public int ejecutar()
  {
      if(actual == null)
      {
          actual = nivel.getListaProcesos().raiz;
      }
      
      if (actual!=null)
      {
                  
          if(actual.getEstado() == 0)
          {   
              actual.setEstado(1);
          for(int i = 0; i < 50 ; i++ )
          {
                  double probabilidad =Math.random();
                  if(probabilidad>=0 && probabilidad<0.001)
                  {
                       new hiloBloqueo(actual);
                       actual = actual.getSiguiente();
                       System.out.println("Existe bloqueo");
                       return 1;
                  }
                  else
                  {  
                      try 
                      {
                          sleep(tiempo);
                      } 
                      catch (InterruptedException ex) 
                      {
                        Logger.getLogger(ejecucionProceso.class.getName()).log(Level.SEVERE, null, ex);
                      }
                  
                      if(actual.getDuracion() > actual.getTiempoTranscurrido())
                        {
                            double n = actual.getTiempoTranscurrido();
                            actual.setTiempoTranscurrido(n + 0.01);
                        }
                        else
                        {
                            //bandera = true;
                            nodoProceso borrar = actual;
                            actual = actual.getSiguiente();
                            nivel.getListaProcesos().eliminarNodo(borrar);
                            tablaProceso.remove(borrar);
                            System.out.println("BORRO PROCESO");
                            return -1; // eliminado
                        }
                    }
                } 
          }
          else
          {
              actual = actual.getSiguiente();
              return 1; //Proceso Ejecutado, pedir siguiente.
          }
          
      }
      
      if(actual == null)
      {
          return 0; // vacio
      }
      else
      {
          actual.setEstado(0);
          actual = actual.getSiguiente();
          return 1; // sigue
      }
      
  }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getTiempo() {
        return tiempo;
    }
  
    
  
  
    
}
