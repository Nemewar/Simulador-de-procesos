
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Frank Pizarro
 */
public class VentanaSimulador extends javax.swing.JFrame {

    int numeroProcesos;
    ListaES<Proceso> listaProcesos;
    
    
    
    public VentanaSimulador(int n,ListaES<Proceso> lista) {
        initComponents();
        this.numeroProcesos = n;
        this.listaProcesos = lista;
        iniciarTablas();
    }
    
    
    
    public void iniciarTablas()
    {
        tblSimulador.setShowHorizontalLines(true);
        tblSimulador.setShowVerticalLines(true);
        tblTiempoSalida.setShowHorizontalLines(true);
        tblTiempoSalida.setShowVerticalLines(true);
        tblTiempoSalida.setVisible(false);
        
        
        DefaultTableModel model1 = (DefaultTableModel)tblSimulador.getModel();
        DefaultTableModel model2 = (DefaultTableModel)tblTiempoSalida.getModel();
        for(int i=1;i<=numeroProcesos;i++)
        {
            Object obj1[] = {"P"+i,null,null,null};
            Object obj2[] = {null};
            model1.addRow(obj1);
            model2.addRow(obj2);
        }
        
        
        

    }
    
    public boolean validarVacio()
    {
        DefaultTableModel model = (DefaultTableModel)tblSimulador.getModel();
        int nfilas,ncol;
        nfilas = model.getRowCount();
        ncol = model.getColumnCount();
        boolean vacio = false;
        
        for(int i=0;i<=nfilas-1;i++)
        {
            for(int j=0;j<=ncol-1;j++)
            {
                if(model.getValueAt(i, j)==null)
                {
                    vacio = true;
                }
            }
        }
        return vacio;
    }
    
    public boolean validarTiempoLlegadaIgual()
    {
        DefaultTableModel model = (DefaultTableModel)tblSimulador.getModel();
        int nfilas;
        nfilas = model.getRowCount();
        int tiempoLlegadas[] = new int[nfilas];
        boolean resultado = false;
        
        for(int i=0;i<=nfilas-1;i++)
        {
            tiempoLlegadas[i] = (Integer)model.getValueAt(i, 1);
        }
        
        for(int i=0;i<=tiempoLlegadas.length-1;i++)
        {
            for(int j=0;j<=tiempoLlegadas.length-1;j++)
            {
                if(i==j)
                {
                    continue;
                }
                if(tiempoLlegadas[i]==tiempoLlegadas[j])
                {
                    resultado = true;
                }
            }
        }
        
        
        return resultado;
    }
    
    public void llenarAtributos()
    {
        int tiempoLlegada=0,duracion=0,prioridad=0;
        
        DefaultTableModel model = (DefaultTableModel)tblSimulador.getModel();
        for(int i=0;i<=model.getRowCount()-1;i++)
        {
            Proceso p = listaProcesos.devolverElementoPorPosicion(i+1);
            for(int j=0;j<=model.getColumnCount()-1;j++)
            {
                if(j==1)
                {
                    p.setTiempoLlegada((Integer)model.getValueAt(i, 1));
                }
                if(j==2)
                {
                    p.setDuracion((Integer)model.getValueAt(i, 2));
                }
                if(j==3)
                {
                    p.setPrioridad((Integer)model.getValueAt(i, 3));
                }
            }
        }
    }
    
    public void ordenarTablaSegunTiempoLlegada()
    {
        listaProcesos.ordenar();
        DefaultTableModel model = (DefaultTableModel)tblSimulador.getModel();
        int posLista = 1;
        
        for(int i=0;i<=model.getRowCount()-1;i++)
        {
            Proceso p = listaProcesos.devolverElementoPorPosicion(posLista);
            for(int j=0;j<=model.getColumnCount()-1;j++)
            {
                if(j==0)//PID
                {
                    model.setValueAt(p.getPID(), i, j);
                }
                if(j==1)//tiempo de llegada
                {
                    model.setValueAt(p.getTiempoLlegada(), i, j);
                }
                if(j==2)//duracion
                {
                    model.setValueAt(p.getDuracion(), i, j);
                }
                if(j==3)//prioridad
                {
                    model.setValueAt(p.getPrioridad(), i, j);
                }
            }
            posLista++;
        }
    }
    
    public void mostrarTiemposDeSalida()
    {
        ColaDeEjecucion<Proceso> colaDeEjecucion = new ColaDeEjecucion<>(listaProcesos);
        colaDeEjecucion.pasarProcesos();
        colaDeEjecucion.ejecutarCola();
        
        DefaultTableModel model = (DefaultTableModel)tblTiempoSalida.getModel();
        this.tblTiempoSalida.setVisible(true);
        
        
        int columna=0;
        int posLista=1;
        for(int i=0;i<=Proceso.getContadorProcesos()-1;i++)
        {
            model.setValueAt(listaProcesos.devolverElementoPorPosicion(posLista).getTiempoSalida(), i, columna);
            posLista++;
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
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSimulador = new javax.swing.JTable();
        btnEjecutar = new javax.swing.JButton();
        lblIngreseValores = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTiempoSalida = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblSimulador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tblSimulador.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblSimulador.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "PID", "TiempoLlegada", "Duracion", "Prioridad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblSimulador.setRowHeight(30);
        jScrollPane1.setViewportView(tblSimulador);

        btnEjecutar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        lblIngreseValores.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblIngreseValores.setText("Ingrese los valores de cada proceso:");

        tblTiempoSalida.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblTiempoSalida.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TiempoSalida"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTiempoSalida.setRowHeight(30);
        jScrollPane3.setViewportView(tblTiempoSalida);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Notas:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("- Despues de ingresar el último valor apretar enter para deseleccionar la tabla ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("- Se deben ingresar todos los los valores antes de darle a ejecutar");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("- No se deben ingresar tiempos de llegada iguales para los procesos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIngreseValores, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(138, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(lblIngreseValores)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9))
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        tblSimulador.clearSelection();
        if(validarVacio())
        {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        }
        else
        {
            if(validarTiempoLlegadaIgual())
            {
                JOptionPane.showMessageDialog(null, "No se permiten tiempos de llegada iguales");
            }
            else
            {
                llenarAtributos();
                ordenarTablaSegunTiempoLlegada();
                mostrarTiemposDeSalida();
            }
        }
        
            
    }//GEN-LAST:event_btnEjecutarActionPerformed

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
            java.util.logging.Logger.getLogger(VentanaSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaSimulador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblIngreseValores;
    private javax.swing.JTable tblSimulador;
    private javax.swing.JTable tblTiempoSalida;
    // End of variables declaration//GEN-END:variables
}
