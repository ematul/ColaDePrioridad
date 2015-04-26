/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package colaprioridad;

import java.awt.Component;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author enrique
 */
public class ColaPrioridad extends javax.swing.JFrame {

      
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
    

    startTimer hilo;
    
    hiloCreacionProcesos creacion;
    
    boolean automatico = false;
    
    ArrayList <nodoProceso> tablaProcesos;
    
    /**
     * Creates new form ColaPrioridad
     */
    public ColaPrioridad() {
        initComponents();
        iniciar();
        jPanel3.setVisible(false);
       
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
                
        this.hilo = new startTimer();
        this.hilo.start();
        
        
        
        
    }

     private String estado(int i )
    {
        String Estado = "Estado Desconocido";
        if(tablaProcesos.get(i).getEstado()== 0) 
        {
                Estado = "Listo";
        }
        else 
        { 
            if(tablaProcesos.get(i).getEstado()== 1) 
            {
                Estado = "Ejecucion";
            }
            else 
            {
                Estado = "Bloqueado";
            }
        }
        return Estado;
    }
    
     private void graficar()
    {
        
        jProgressBar2.setMaximum(hilo1.nivel.getCantidadCuantos());
        jProgressBar3.setMaximum(hilo2.nivel.getCantidadCuantos());
        jProgressBar7.setMaximum(hilo3.nivel.getCantidadCuantos());
        
        jProgressBar5.setMaximum(hilo4.nivel.getCantidadCuantos());
        jProgressBar6.setMaximum(hilo5.nivel.getCantidadCuantos());
        
        jProgressBar2.setMinimum(0);
        jProgressBar3.setMinimum(0);
        jProgressBar7.setMinimum(0);
        jProgressBar5.setMinimum(0);
        jProgressBar6.setMinimum(0);
        
        
        int T_priori1 = -1; 
        int T_priori2 = -1;
        int T_priori3 = -1;
        int T_priori4 = -1;
        int T_priori5 = -1;
        
        int tamanio = tablaProcesos.size();
        limpiarTablas();
        
        
        
        for(int i=0; i< tamanio; i++)
        {
              if(tablaProcesos.get(i) != null){
                 if(tablaProcesos.get(i).getPrioridad() == 0)
                 {
                    T_priori1 += 1; 
                    jProgressBar2.setValue(cpu.getPrioridad1());
                   // jLabel1.setText(Integer.toString(cpu.getPrioridad1()));
                    int avance = (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);        
                    jTable1.setValueAt(tablaProcesos.get(i).getNombre(), T_priori1, 0);
                    jTable1.setValueAt(estado(i), T_priori1, 1); 
                    jTable1.setValueAt(avance + "%", T_priori1, 2); // para barra de progreso  
                    jTable1.setValueAt(tablaProcesos.get(i).getDuracion(), T_priori1, 3);
                    jTable1.setValueAt(tablaProcesos.get(i).getTiempoTranscurrido(), T_priori1, 4);
                    jTable1.setValueAt(bloqueo(i), T_priori1, 5);
                    
                    boolean estado = (boolean)jTable1.getValueAt(T_priori1, 5);
                    if (estado != false)
                        if(tablaProcesos.get(i)!=null)
                           tablaProcesos.get(i).setEstado(2);
                }
              }
              if(tablaProcesos.get(i) != null){
                 if(tablaProcesos.get(i).getPrioridad() == 1)
                {
                    T_priori2 += 1;
                    jProgressBar3.setValue(cpu.getPrioridad2());
                    //jLabel2.setText(Integer.toString(cpu.getPrioridad2()));
                     int avance= (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);            
                    jTable2.setValueAt(tablaProcesos.get(i).getNombre(), T_priori2, 0);
                    jTable2.setValueAt(estado(i), T_priori2, 1); 
                    jTable2.setValueAt(avance + "%", T_priori2, 2);// para barra de progreso  
                    jTable2.setValueAt(tablaProcesos.get(i).getDuracion(), T_priori2, 3);
                    jTable2.setValueAt(tablaProcesos.get(i).getTiempoTranscurrido(), T_priori2, 4);
                    jTable2.setValueAt(bloqueo(i), T_priori2, 5);
                    
                    boolean estado = (boolean) jTable2.getValueAt(T_priori2, 5);
                    if (estado == true)
                        
                        if(tablaProcesos.get(i)!=null)
                        tablaProcesos.get(i).setEstado(2);
                    
                }
              }
               if(tablaProcesos.get(i) != null){ 
                  if(tablaProcesos.get(i).getPrioridad() == 2)
                {
                    T_priori3 += 1;
                    jProgressBar7.setValue(this.cpu.getPrioridad3());
                    
                   // jLabel3.setText(Integer.toString(cpu.getPrioridad3()));
                     int avance= (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);
                    jTable3.setValueAt(tablaProcesos.get(i).getNombre(), T_priori3, 0);
                    jTable3.setValueAt(estado(i), T_priori3, 1); 
                    jTable3.setValueAt(avance + "%", T_priori3, 2); //para barra de progreso  
                    jTable3.setValueAt(tablaProcesos.get(i).getDuracion(), T_priori3, 3);
                    jTable3.setValueAt(tablaProcesos.get(i).getTiempoTranscurrido(), T_priori3, 4);
                    jTable3.setValueAt(bloqueo(i), T_priori3, 5);
                    
                    boolean estado = (boolean)jTable3.getValueAt(T_priori3, 5);
                    if (estado == true)
                        if(tablaProcesos.get(i)!=null)
                        tablaProcesos.get(i).setEstado(2);
                    
                }
               }
               if(tablaProcesos.get(i) != null){
                   if(tablaProcesos.get(i).getPrioridad() == 3)
                {
                    T_priori4 += 1;
                    jProgressBar5.setValue(cpu.getPrioridad4());
                    //jLabel4.setText(Integer.toString(cpu.getPrioridad4()));
                     int avance= (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);
                   
                    jTable4.setValueAt(tablaProcesos.get(i).getNombre(), T_priori4, 0);
                    jTable4.setValueAt(estado(i), T_priori4, 1); 
                    jTable4.setValueAt(avance + "%", T_priori4, 2);// para barra de progreso  
                    jTable4.setValueAt(tablaProcesos.get(i).getDuracion(), T_priori4, 3);
                    jTable4.setValueAt(tablaProcesos.get(i).getTiempoTranscurrido(), T_priori4, 4);
                    jTable4.setValueAt(bloqueo(i), T_priori4, 5);
                    
                     boolean estado = (boolean)jTable4.getValueAt(T_priori4, 5);
                    if (estado == true)
                        if(tablaProcesos.get(i)!=null)
                        tablaProcesos.get(i).setEstado(2);
                }
               }
               
               if(tablaProcesos.get(i) != null){
                    if(tablaProcesos.get(i).getPrioridad() == 4)
                {
                    T_priori5 += 1;
                    jProgressBar6.setValue(cpu.getPrioridad5());
                    
                    //jLabel5.setText(Integer.toString(cpu.getPrioridad5()));
                     int avance= (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);
                    jTable5.setValueAt(tablaProcesos.get(i).getNombre(), T_priori5, 0);
                    jTable5.setValueAt(estado(i), T_priori5, 1); 
                    jTable5.setValueAt(avance + "%", T_priori5, 2); //para barra de progreso  
                    jTable5.setValueAt(tablaProcesos.get(i).getDuracion(), T_priori5, 3);
                    jTable5.setValueAt(tablaProcesos.get(i).getTiempoTranscurrido(), T_priori5, 4);
                    jTable5.setValueAt(bloqueo(i), T_priori5, 5);
                    
                     boolean estado = (boolean)jTable5.getValueAt(T_priori5, 5);
                    if (estado == true)
                        if(tablaProcesos.get(i)!=null)
                        tablaProcesos.get(i).setEstado(2);
                }
               }
          
                if(tablaProcesos.get(i).getEstado() == 1){
                    int prioridad = tablaProcesos.get(i).getPrioridad()+1;
                    jLabel6.setText("Prioridad: " + prioridad + "   Proceso: " + tablaProcesos.get(i).getNombre());
                    jProgressBar1.setMaximum((int)tablaProcesos.get(i).getDuracion());
                    jProgressBar1.setMinimum(0);
                    jProgressBar1.setValue((int)tablaProcesos.get(i).getTiempoTranscurrido());
                    int avance= (int)((tablaProcesos.get(i).getTiempoTranscurrido()/tablaProcesos.get(i).getDuracion())*100);
              
                    jProgressBar1.setString(avance + "%");
                    jProgressBar1.setStringPainted(true);
                }
         
      
            }
        
         
        
        } 
            
    
    private void limpiarTablas()
    {
          for(int m=0; m< 10; m++)
          {
            for(int n=0; n<= 4; n++)
            {
                jTable1.setValueAt(" ", m, n);
                jTable2.setValueAt(" ", m, n);
                jTable3.setValueAt(" ", m, n);
                jTable4.setValueAt(" ", m, n);
                jTable5.setValueAt(" ", m, n);
            }  
        }        
    }
        
    public class ProgressCellRenderer extends JProgressBar
                        implements TableCellRenderer {
 
  /**
   * Creates a JProgressBar with the range 0,100.
   */
  public ProgressCellRenderer(int valor){
    super(0, 100);
    setValue(valor);
    setString(valor+"%");
    setStringPainted(true);
  }
 
  public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column) {
 
    //value is a percentage e.g. 95%
    final String sValue = value.toString();
    int index = sValue.indexOf('%');
    if (index != -1) {
      int p = 0;
      try{
        p = Integer.parseInt(sValue.substring(0, index));
      }
      catch(NumberFormatException e){
      }
      setValue(p);
      setString(sValue);
    }
    return this;
  }
}    
        
        private boolean bloqueo(int i)
        {
            boolean bandera= false;
            
            if(tablaProcesos.get(i).getEstado()==2)
                bandera = true;
            
            
            return bandera;
        }
     
    public class startTimer extends Thread {

        @Override
        public void run() {
            super.run();
            
            while(true)
            {   
                graficar();
                try {
                    sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ColaPrioridad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
  }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel6 = new javax.swing.JLabel();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jProgressBar5 = new javax.swing.JProgressBar();
        jProgressBar6 = new javax.swing.JProgressBar();
        jProgressBar7 = new javax.swing.JProgressBar();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Prioridad 1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Proceso", "Duración", "Atendido", "Bloqueo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Prioridad 2");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Proceso", "Duración", "Atendido", "Bloqueo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Prioridad 3");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null,  new Boolean(false)},
                {null, null, null, null, null,  new Boolean(false)},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Proceso", "Duración", "Atendido", "Bloqueo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jLabel4.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Prioridad 4");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Proceso", "Duración", "Atendido", "Bloqueo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel5.setFont(new java.awt.Font("Papyrus", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Prioridad 5");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Nombre", "Estado", "Proceso", "Duración", "Atendido", "Bloqueo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable5);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("CPU"));

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jProgressBar2.setBackground(new java.awt.Color(102, 102, 102));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jProgressBar5, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jProgressBar7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jProgressBar6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        jButton1.setText("Menú");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jButton2.setText("Proceso");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Automatico");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 164, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(1, 1, 1))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        nodoProceso no;
        int prioridad = (int)(Math.random()*5);
        no =  new nodoProceso();
        no.setNombre("PROC");
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
        
        tablaProcesos.add(no);
        
        
        
        
    }//GEN-LAST:event_jButton2ActionPerformed
    int bloqueo = 0;
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(bloqueo == 0){
           jPanel3.setVisible(true);
           bloqueo = 1;
        }
        else
        if(bloqueo == 1){
           jPanel3.setVisible(false);
           bloqueo = 0;
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    int a = 1;
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(a == 1){
            creacion.continuar();
            automatico = false;
            jButton3.setText("Manual");
            a = 0;
        }else
            if(a == 0){
                creacion.pausa();
                automatico = true;
                jButton3.setText("Automatico");
                a = 1;
            }
    }//GEN-LAST:event_jButton3ActionPerformed

    
 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ColaPrioridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ColaPrioridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ColaPrioridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ColaPrioridad.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ColaPrioridad().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JProgressBar jProgressBar5;
    private javax.swing.JProgressBar jProgressBar6;
    private javax.swing.JProgressBar jProgressBar7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
