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
public class nodoPrioridad {
    
    private int cuanto = 50;
    private int prioridad  = 0;
    private int contProcesos = 0;
    private int cantidadCuantos = 0;
    private listaProcesos listaProcesos = null;
    

    public nodoPrioridad( int cuanto , int prioridad, int cont) {
        this.listaProcesos = new listaProcesos();
        this.cuanto = cuanto;
        this.prioridad = prioridad;
        this.cantidadCuantos = cont;
       
    }

    public nodoPrioridad() {
        listaProcesos = new listaProcesos();
    }
    
    
    /**
     * @return the cuanto
     */
    public int getCuanto() {
        return cuanto;
    }

    /**
     * @param cuanto the cuanto to set
     */
    public void setCuanto(int cuanto) {
        this.cuanto = cuanto;
    }

    /**
     * @return the prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }

    /**
     * @param prioridad the prioridad to set
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    /**
     * @return the contProcesos
     */
    public int getContProcesos() {
        return contProcesos;
    }

    /**
     * @param contProcesos the contProcesos to set
     */
    public void setContProcesos(int contProcesos) {
        this.contProcesos = contProcesos;
    }


    /**
     * @return the listaProcesos
     */
    public listaProcesos getListaProcesos() {
        return listaProcesos;
    }

    /**
     * @param listaProcesos the listaProcesos to set
     */
    public void setListaProcesos(listaProcesos listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    /**
     * @return the cantidadCuantos
     */
    public int getCantidadCuantos() {
        return cantidadCuantos;
    }

    /**
     * @param cantidadCuantos the cantidadCuantos to set
     */
    public void setCantidadCuantos(int cantidadCuantos) {
        this.cantidadCuantos = cantidadCuantos;
    }
     
    
}
