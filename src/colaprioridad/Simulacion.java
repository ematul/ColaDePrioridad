/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package colaprioridad;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;

/**
 *
 * @author enrique
 */
public class Simulacion extends JFrame{

    private Screen screen;
    
    public Simulacion() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setResizable(true);
        setTitle("Simulación Colas de Prioridad");
        setBackground(Color.gray);
        init();
    }
    
    public void init(){
        //setLocationRelativeTo(null);
        //setLayout(new GridLayout(1, 1, 0, 0));
        screen = new Screen();
        add(screen);
        setVisible(true);
    }
    
    public static void main(String args[]) {
        new Simulacion();
    }
    
}
