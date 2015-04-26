/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

import java.awt.Point;

/**
 *
 * @author enrique
 */
public class nodoProceso {
    
    private String nombre ; //nombre Procesos 
    private double duracion; // Tiempo de duración de proceso.
    private int prioridad;  //Prioridad del proceso
    private int estado ; // 0 = listo ; 1 = ejecucion ; 2 = bloqueado
    private double tiempoTranscurrido; //tiempo transcurrido
    private nodoProceso siguiente = null;
    private Point posXY;

    public nodoProceso(String nombre, double duracion, int prioridad, int estado)
    {
        this.nombre = nombre;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.estado = estado;
    }
    
    public nodoProceso(String nombre, double duracion, int prioridad, int estado, Point posXY)
    {
        this.nombre = nombre;
        this.duracion = duracion;
        this.prioridad = prioridad;
        this.estado = estado;
        this.posXY = posXY;
    }

    public nodoProceso() {
    }
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the duracion
     */
    public double getDuracion() {
        return duracion;
    }

    /**
     * @param duracion the duracion to set
     */
    public void setDuracion(double duracion) {
        this.duracion = duracion;
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
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }

    /**
     * @return the siguiente
     */
    public nodoProceso getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(nodoProceso siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * @return the tiempoTranscurrido
     */
    public double getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    /**
     * @param tiempoTranscurrido the tiempoTranscurrido to set
     */
    public void setTiempoTranscurrido(double tiempoTranscurrido) {
        this.tiempoTranscurrido = tiempoTranscurrido;
    }

    /**
     * @return the posXY
     */
    public Point getPosXY() {
        return posXY;
    }

    /**
     * @param posXY the posXY to set
     */
    public void setPosXY(Point posXY) {
        this.posXY = posXY;
    }
    
    
    
}
