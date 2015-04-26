/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

/**
 *
 * @author enrique
 */
public class listaProcesos {
    
    nodoProceso raiz = null;
    nodoProceso auxiliar = null;

    void ingresoProceso(nodoProceso proceso)
    {
        if(raiz == null)
        {
            this.raiz = proceso;
            auxiliar = raiz;
        }
        else
        {
            nodoProceso nodo = proceso;
            auxiliar.setSiguiente(nodo);
            auxiliar = nodo;
        }
    }
    
    void eliminarNodo(nodoProceso proceso)
    {
        nodoProceso aux = raiz;
        
        while(aux!=null)
        {
            if(aux.equals(proceso))
            {
                if(aux.equals(raiz))
                {
                   this.raiz =  raiz.getSiguiente();
                   aux.setSiguiente(null);
                }
                else if(aux.equals(auxiliar))
                {
                    nodoProceso aux2 = raiz;
                    while(aux2!=null)
                    {
                        if(aux2.getSiguiente() == aux)
                        {
                            aux2.setSiguiente(null);
                            auxiliar = aux2;
                            
                        }
                        aux2=aux2.getSiguiente();
                    }
                    
                }
                else
                {
                    nodoProceso aux2 = raiz;
                    while(aux2!=null)
                    {
                        if(aux2.getSiguiente() == aux)
                        {
                            aux2.setSiguiente(aux.getSiguiente());
                            aux.setSiguiente(null);
                        }
                        aux2=aux2.getSiguiente();
                    }
                }
                break;
                
            }
            aux=aux.getSiguiente();
        }
    }
    
    void mostrar()
    {
        nodoProceso aux = raiz;
        
        while(aux!=null)
        {
            System.out.println("Duracion: "+aux.getDuracion() + " prioridad: " +aux.getPrioridad() + " tiempo transcurrido: "+ aux.getTiempoTranscurrido());
            aux=aux.getSiguiente();
        }
                
    }
    
    
    
    
}
