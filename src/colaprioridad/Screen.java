/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author enrique
 */
public class Screen extends JPanel {

    hiloControl cpu;
    
    nodoPrioridad nivel1;
    nodoPrioridad nivel2;
    nodoPrioridad nivel3;
    nodoPrioridad nivel4;
    nodoPrioridad nivel5;
    
    ejecucionProceso hilo1;
    ejecucionProceso hilo2;
    ejecucionProceso hilo3;
    ejecucionProceso hilo4;
    ejecucionProceso hilo5;
    

    
    hiloCreacionProcesos creacion;
    
    boolean automatico = false;
    
    ArrayList <nodoProceso> tablaProcesos;
    
    //Graficos
    Graphics2D g2;
    Color[] colors = { Color.YELLOW, Color.MAGENTA, Color.CYAN , Color.RED, Color.BLUE, Color.PINK};
    
    
    public Screen() {
        repaint();
        iniciar();
    }
    
    private void iniciar()
    {
        this.nivel1 = new nodoPrioridad(50, 0, 15);
        this.nivel2 = new nodoPrioridad(50, 1, 12);
        this.nivel3 = new nodoPrioridad(50, 2, 9);
        this.nivel4 = new nodoPrioridad(50, 3, 6);
        this.nivel5 = new nodoPrioridad(50, 4, 3);
        
        this.tablaProcesos = new ArrayList();
        
        this.hilo1 = new ejecucionProceso("nivel 1", nivel1, tablaProcesos);
        this.hilo2 = new ejecucionProceso("nivel 2", nivel2, tablaProcesos);
        this.hilo3 = new ejecucionProceso("nivel 3", nivel3, tablaProcesos);
        this.hilo4 = new ejecucionProceso("nivel 4", nivel4, tablaProcesos);
        this.hilo5 = new ejecucionProceso("nivel 5", nivel5, tablaProcesos);
        
      
        this.creacion = new hiloCreacionProcesos(tablaProcesos, nivel1, nivel2, nivel3, nivel4, nivel5);
      
        
        this.cpu = new hiloControl(hilo1, hilo2, hilo3, hilo4, hilo5);
                
    }

    @Override
    public void paint(Graphics g) {
        
        try {
            g.clearRect(0, 0, 1000, 700);
            g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            paintBackground(g2);
            
            int colorIndex = 0;
            g2.setStroke(new BasicStroke(2));
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.75f));
            
            int xColaP1 = 50;
            int xColaP2 = 200;
            int xColaP3 = 350;
            int xColaP4 = 500;
            int xColaP5 = 650;
            
            int yColaP1 = 50;
            int yColaP2 = 50;
            int yColaP3 = 50;
            int yColaP4 = 50;
            int yColaP5 = 50;
            
            for (nodoProceso proceso :tablaProcesos )
            {
                if(proceso!=null)
                {
                    switch(proceso.getPrioridad()){
                        case 0:
                        {
                            paintProceso(proceso, g2, xColaP1, yColaP1,Color.YELLOW);
                            
                            yColaP1 = yColaP1 + 120;
                            break;
                        }
                        case 1:
                        {
                            paintProceso(proceso, g2, xColaP2, yColaP2,Color.MAGENTA);
                            yColaP2 = yColaP2 + 120;
                            break;
                        }
                        case 3:
                        {
                            paintProceso(proceso, g2, xColaP3, yColaP3,Color.CYAN);
                            yColaP3 = yColaP3 + 120;
                            break;
                        }
                        case 4:
                        {
                            paintProceso(proceso, g2, xColaP4, yColaP4,Color.ORANGE);
                            yColaP4 = yColaP4 + 120;
                            break;
                        }    
                        default:
                        {
                            paintProceso(proceso, g2, xColaP5, yColaP5,Color.PINK);
                            yColaP5 = yColaP5 + 120;
                            break;
                        }    
                    }
                }
            }
            paintBorde(g2, 45, 45,110, yColaP1 - 60, Color.YELLOW, "Prioridad 1");
            paintBorde(g2, 195, 45,110, yColaP2 - 60, Color.MAGENTA, "Prioridad 2");
            paintBorde(g2, 345, 45,110, yColaP3 - 60, Color.CYAN, "Prioridad 3");
            paintBorde(g2, 495, 45,110, yColaP4 - 60, Color.ORANGE, "Prioridad 4");
            paintBorde(g2, 645, 45,110, yColaP5 - 60, Color.PINK, "Prioridad 5");
            
            Thread.sleep(100);
            repaint();
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void paintProceso(nodoProceso proceso,Graphics2D g2, int x, int y, Color color)
    {
        Shape s = makeRectangle(x, y, 100, 100);
        g2.setPaint(Color.BLACK);            
        g2.draw(s);
        ///Color Interno
        g2.setPaint(estado(proceso.getEstado(), color));
        g2.fill(s);
        g2.setPaint(Color.BLACK);
        g2.drawString(proceso.getNombre(),x+5, y+15);
        int avance = (int)(proceso.getTiempoTranscurrido()/proceso.getDuracion())*100;
        g2.drawString("Porcentaje " + avance,x+5, y+50);
        
        String t1=String.valueOf(proceso.getDuracion()).substring(0, 3);
        String t2=String.valueOf(proceso.getTiempoTranscurrido()).substring(0, 3);
        g2.drawString("t= "+ t1, x+5, y+75);
        g2.drawString("t= "+ t2, x+50, y+75);
    }
    
    private void paintBorde(Graphics2D g2,int x, int y, int ancho, int largo, Color c, String t)
    {
        Shape s = makeRectangle(x, y, ancho, largo);
        g2.setPaint(c);
        g2.draw(s);
        g2.drawString(t,x+5,y-20);
    }
    
    private Color estado(int estado, Color c)
    {
        if(estado == 1) return Color.GREEN;
        if(estado == 2) return Color.RED;
        return c;
    }
    
    
    private void paintBackground(Graphics2D g2){
      g2.setPaint(Color.LIGHT_GRAY);
      for (int i = 0; i < getSize().width; i += 10) {
        Shape line = new Line2D.Float(i, 0, i, getSize().height);
        g2.draw(line);
      }

      for (int i = 0; i < getSize().height; i += 10) {
        Shape line = new Line2D.Float(0, i, getSize().width, i);
        g2.draw(line);
      }
    }
    
    
    
    
    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
      return new Rectangle2D.Float(x1,y1,x2,y2);
    }
    
    
    
    
    
}
