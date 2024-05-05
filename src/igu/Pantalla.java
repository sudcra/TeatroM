// Evaluación formativa 3

// Importaciones
package igu;
import logica.Ticket;
import logica.Venta;
import logica.Funcion;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
// fin de importaciones

// CLASE PRINCIPAL: PANTALLA
public class Pantalla extends javax.swing.JFrame {
    // VARIABLES GLOBALES DE GESTIÓN
    private DefaultTableModel modeloDatos;
    private final DefaultTableModel modeloDatos2;
    private final HashMap<Integer,String> tipoEntrada = new HashMap<>();
    private final HashMap<String,Integer> funcionCod = new HashMap<>();
    private final HashMap<String,Integer> asientoCod = new HashMap<>();
    public final List<Venta> ventas = new ArrayList<>();
    
    //public final List<Ticket> tickets = new ArrayList<>();
    private int[] retorno;
    private int totalEntradasVendidas = 0;
    private int idVenta = 1;
    private boolean permiteingreso = true;
    
    String fechaString1 = "31/06/2024";
    DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fechaEspecifica1 = LocalDate.parse(fechaString1, formatter1);
    int[] capacidad1 = { 15,50,30,20,20 };
    int[] precios1 = { 50000,35000,15000,17000,17000 };
    public final Funcion funcion1 = new Funcion("Lala",fechaEspecifica1,capacidad1,precios1);
    
    String fechaString2 = "15/05/2024";
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fechaEspecifica2 = LocalDate.parse(fechaString2, formatter2);
    int[] capacidad2 = { 15,50,30,20,20 };
    int[] precios2 = { 25000,15000,10000,12000,12000 };
    Funcion funcion2 = new Funcion("El Mar",fechaEspecifica2,capacidad2,precios2);
    String fechaString3 = "01/07/2024";
    DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate fechaEspecifica3 = LocalDate.parse(fechaString3, formatter3);
    int[] capacidad3 = {15,50,30,20,20};
    int[] precios3 = {30000,20000,10000,15000,15000};
    Funcion funcion3 = new Funcion("Loco loco",fechaEspecifica3,capacidad3,precios3);

        // CLASE INTERNA VENTA  -------------------
       
    
    // CONSTRUCTOR PRINCIPAL DE PANTALLA
    public Pantalla() {
        initComponents();
        // Inicialización de asientos disponibles
        
        tipoEntrada.put(1,"Estudiante");
        tipoEntrada.put(2,"General");
        tipoEntrada.put(3,"Tercera edad");
        
        funcionCod.put("Lala",0);
        funcionCod.put("El Mar",1);
        funcionCod.put("Loco loco",2);
        
        asientoCod.put("FRONT STAGE", 0);
        asientoCod.put("RUEDO-PLATEA", 1);
        asientoCod.put("TENDIDO-ANFITEATRO", 2);
        asientoCod.put("GRADA-ANFITEATRO", 3);
        asientoCod.put("ANDANADA-ANFITEATRO", 4);
        
        // Actualización inicial de asientos disponibles
        actualizarAsientosDisponibles();
        // Inicialización de array para retorno
        retorno = new int[3]; 
        // Label para mostrar disponibilidad de asientos
        jPanel1.add(jLabel6);
        
        modeloDatos=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
        }
        };
        
        // Inicializacion de elementos de la tabla
        //modeloDatos = new DefaultTableModel();
        Carrito.setModel(modeloDatos);
        modeloDatos.addColumn("Funcion");
        modeloDatos.addColumn("Asiento");
        modeloDatos.addColumn("Edad");
        modeloDatos.addColumn("Tipo entrada");
        modeloDatos.addColumn("Precio ($)");
        modeloDatos.addColumn("Descuento (%)");
        modeloDatos.addColumn("Precio final ($)");        
        Carrito.getColumnModel().getColumn(0).setPreferredWidth(100);
        Carrito.getColumnModel().getColumn(1).setPreferredWidth(100);
        Carrito.getColumnModel().getColumn(2).setPreferredWidth(100);
        Carrito.getColumnModel().getColumn(3).setPreferredWidth(60);
        Carrito.getColumnModel().getColumn(4).setPreferredWidth(95);
        Carrito.getColumnModel().getColumn(5).setPreferredWidth(85);
        
        
        modeloDatos2=new DefaultTableModel(){
        @Override
        public boolean isCellEditable(int i, int i1) {
        return false; //To change body of generated methods, choose Tools | Templates.
        }
        };
        Vendido.setModel(modeloDatos2);
        modeloDatos2.addColumn("Id");
        modeloDatos2.addColumn("Cantidad ticket");
        modeloDatos2.addColumn("Monto ($)");
        modeloDatos2.addColumn("Marca temporal");
        Vendido.getColumnModel().getColumn(0).setMaxWidth(50);
        Vendido.getColumnModel().getColumn(1).setMaxWidth(230);
        Vendido.getColumnModel().getColumn(2).setMaxWidth(150);
        Vendido.getColumnModel().getColumn(3).setMaxWidth(350);
        //funcion1.getCapacidad();
        actualizaPrecios();
        
    }
    
    // METODOS PRINCIPALES DE PANTALLA
    
    // Método para inicializar la información de asientos disponibles
    private void actualizaPrecios() {
        // Inicializar las funciones
        int[] preciosini= null;
        int[] cantidadini= null;
        
        int funcionSeleccionada = Combofuncion.getSelectedIndex();
        
        switch (funcionSeleccionada) {
            case 0 -> {cantidadini = funcion1.getCapacidad();
                       preciosini= funcion1.getPrecio();
                        }
            case 1 -> {cantidadini = funcion2.getCapacidad();
                       preciosini= funcion2.getPrecio();
                        }
            case 2 -> {cantidadini = funcion3.getCapacidad();
                       preciosini= funcion3.getPrecio();
                        }
        }
        
        
        
        
        
       
        precio0.setText("$"+preciosini[0]);
        precio1.setText("$"+preciosini[1]);
        precio2.setText("$"+preciosini[2]);
        precio3.setText("$"+preciosini[3]);
        precio4.setText("$"+preciosini[4]);
        cant0.setText(""+cantidadini[0]);
        cant1.setText(""+cantidadini[1]);
        cant2.setText(""+cantidadini[2]);
        cant3.setText(""+cantidadini[3]);
        cant4.setText(""+cantidadini[4]);
        
    }
    
    // Método para obtener la cantidad de asientos disponibles para una zona específica
   
    
    // Método para descontar los asientos vendidos del total de asientos disponibles
    
    // Método público para obtener el JComboBox tipoAsiento desde fuera de la clase Pantalla
    public JComboBox<String> getTipoAsiento() {
        return tipoAsiento;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Combofuncion = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        tipoAsiento = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tEdad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        Carrito = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        Total = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        limpiar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jLabel4 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Vendido = new javax.swing.JTable();
        verTicket = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel9 = new javax.swing.JLabel();
        precio0 = new javax.swing.JLabel();
        cant0 = new javax.swing.JLabel();
        jDesktopPane3 = new javax.swing.JDesktopPane();
        precio1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cant1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDesktopPane4 = new javax.swing.JDesktopPane();
        precio2 = new javax.swing.JLabel();
        cant2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jDesktopPane5 = new javax.swing.JDesktopPane();
        precio3 = new javax.swing.JLabel();
        cant3 = new javax.swing.JLabel();
        precio4 = new javax.swing.JLabel();
        jDesktopPane6 = new javax.swing.JDesktopPane();
        jLabel15 = new javax.swing.JLabel();
        cant4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Venta de entradas Teatro Moro");

        Combofuncion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lala", "El Mar", "Loco loco" }));
        Combofuncion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CombofuncionItemStateChanged(evt);
            }
        });
        Combofuncion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CombofuncionActionPerformed(evt);
            }
        });

        jLabel2.setText("Seleccione tipo de asiento");

        tipoAsiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FRONT STAGE", "RUEDO-PLATEA", "TENDIDO-ANFITEATRO", "GRADA-ANFITEATRO", "ANDANADA-ANFITEATRO" }));
        tipoAsiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoAsientoItemStateChanged(evt);
            }
        });
        tipoAsiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoAsientoActionPerformed(evt);
            }
        });

        jLabel3.setText("Ingrese edad");

        tEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tEdadKeyTyped(evt);
            }
        });

        Carrito.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Asiento", "Edad", "Tipo entrada", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Carrito.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(Carrito);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Total a cancelar:");

        Total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Total.setToolTipText("");

        jButton1.setText("Agregar al carro");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ingresar Venta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        limpiar.setText("Limpiar");
        limpiar.setAutoscrolls(true);
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTabbedPane1FocusGained(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/igu/teatroMoro.png"))); // NOI18N
        jLabel4.setToolTipText("");
        jLabel4.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/igu/teatro.png"))); // NOI18N
        jTabbedPane1.addTab("Mapa asientos", jLabel4);

        Vendido.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        Vendido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Vendido.setFocusable(false);
        Vendido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Vendido.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        Vendido.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                VendidoFocusGained(evt);
            }
        });
        Vendido.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                VendidoCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        jScrollPane2.setViewportView(Vendido);

        verTicket.setText("Ver tickets");
        verTicket.setActionCommand("Ver ventas");
        verTicket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verTicketActionPerformed(evt);
            }
        });

        jButton5.setText("Eliminar venta");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(126, Short.MAX_VALUE)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(verTicket, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(11, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(538, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verTicket)
                    .addComponent(jButton5))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                    .addContainerGap(11, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(54, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Ventas", jPanel2);

        jLabel7.setText("Seleccione función");

        jLabel13.setText("PRECIO");

        jLabel14.setText("DISPONIBLES");

        jDesktopPane2.setBackground(new java.awt.Color(230, 214, 74));
        jDesktopPane2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jLabel9.setText("FRON STAGE");

        precio0.setText("jLabel15");

        cant0.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cant0.setText("jLabel15");
        cant0.setFocusable(false);
        cant0.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jDesktopPane3.setBackground(new java.awt.Color(251, 65, 78));
        jDesktopPane3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPane3Layout = new javax.swing.GroupLayout(jDesktopPane3);
        jDesktopPane3.setLayout(jDesktopPane3Layout);
        jDesktopPane3Layout.setHorizontalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jDesktopPane3Layout.setVerticalGroup(
            jDesktopPane3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        precio1.setText("jLabel15");

        jLabel10.setText("RUEDO-PLATEA");

        cant1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cant1.setText("jLabel15");
        cant1.setFocusable(false);
        cant1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel11.setText("TENDIDO-ANFITEATRO");

        jDesktopPane4.setBackground(new java.awt.Color(228, 103, 11));
        jDesktopPane4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPane4Layout = new javax.swing.GroupLayout(jDesktopPane4);
        jDesktopPane4.setLayout(jDesktopPane4Layout);
        jDesktopPane4Layout.setHorizontalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jDesktopPane4Layout.setVerticalGroup(
            jDesktopPane4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        precio2.setText("jLabel15");

        cant2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cant2.setText("jLabel15");
        cant2.setFocusable(false);
        cant2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel12.setText("GRADA-ANFITEATRO ");

        jDesktopPane5.setBackground(new java.awt.Color(76, 75, 231));
        jDesktopPane5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPane5Layout = new javax.swing.GroupLayout(jDesktopPane5);
        jDesktopPane5.setLayout(jDesktopPane5Layout);
        jDesktopPane5Layout.setHorizontalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jDesktopPane5Layout.setVerticalGroup(
            jDesktopPane5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        precio3.setText("jLabel15");

        cant3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cant3.setText("jLabel15");
        cant3.setFocusable(false);
        cant3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        precio4.setText("jLabel15");

        jDesktopPane6.setBackground(new java.awt.Color(74, 234, 138));
        jDesktopPane6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jDesktopPane6Layout = new javax.swing.GroupLayout(jDesktopPane6);
        jDesktopPane6.setLayout(jDesktopPane6Layout);
        jDesktopPane6Layout.setHorizontalGroup(
            jDesktopPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        jDesktopPane6Layout.setVerticalGroup(
            jDesktopPane6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 19, Short.MAX_VALUE)
        );

        jLabel15.setText("ANDANADA-ANFITEATRO");

        cant4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cant4.setText("jLabel15");
        cant4.setFocusable(false);
        cant4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel7))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tipoAsiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(tEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(62, 62, 62)
                                                        .addComponent(jButton1))
                                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addGap(8, 8, 8)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                            .addComponent(Combofuncion, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDesktopPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(71, 71, 71))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(55, 55, 55))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(45, 45, 45)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(precio0)
                                        .addGap(88, 88, 88)
                                        .addComponent(cant0))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(precio1)
                                        .addGap(88, 88, 88)
                                        .addComponent(cant1))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(precio2)
                                        .addGap(88, 88, 88)
                                        .addComponent(cant2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(precio3)
                                        .addGap(88, 88, 88)
                                        .addComponent(cant3))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(precio4)
                                        .addGap(88, 88, 88)
                                        .addComponent(cant4))))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(limpiar)
                        .addGap(44, 44, 44)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Total, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Combofuncion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tipoAsiento, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limpiar)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Total, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(61, 61, 61)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(precio0)
                        .addComponent(cant0))
                    .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(precio1)
                        .addComponent(cant1))
                    .addComponent(jDesktopPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(precio2)
                        .addComponent(cant2))
                    .addComponent(jDesktopPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(precio3)
                        .addComponent(cant3))
                    .addComponent(jDesktopPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(precio4)
                        .addComponent(cant4))
                    .addComponent(jDesktopPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(149, 149, 149))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void jTabbedPane1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTabbedPane1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTabbedPane1FocusGained

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        try {
            modeloDatos2.setRowCount(0);
        } catch (Exception e) {
            // Manejo de la excepción
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        }
        for (Venta ventam : ventas) {

            Object[] fila = {ventam.getId(), ventam.getTotalticket(), ventam.getTotalMonto(), ventam.getMarcaTemporal()};
            modeloDatos2.addRow(fila);

        }
        //JOptionPane.showMessageDialog(null, "Hola", "Boleta:", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        int selectedRow = Vendido.getSelectedRow();

        if (selectedRow != -1) {
            DefaultTableModel modelo = (DefaultTableModel) Vendido.getModel();
            muestraTicket();
            ventas.remove(selectedRow);
            modelo.removeRow(selectedRow);
            permiteingreso = true;
            
            limpiarTabla();
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void verTicketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verTicketActionPerformed
        muestraTicket();
    }//GEN-LAST:event_verTicketActionPerformed
    private void muestraTicket(){
        int selectedRow = Vendido.getSelectedRow();
        int id = 0;
        if (selectedRow != -1) {
            id = (int) Vendido.getValueAt(selectedRow, 0);
        }
        Total.setText("");
        modeloDatos.setRowCount(0);
        permiteingreso = false;
        // Iterar sobre la lista de ventas y agregarlas a la tabla
        for (Venta ventam : ventas) {
            if (ventam.getId() == id || id==0){
                for  (Ticket ticketm : ventam.getTickets()){
                    System.out.println(ticketm.getFuncion());
                    Object[] fila = {ticketm.getFuncion(),ticketm.getAsiento(), ticketm.getEdad(), ticketm.getTipo(), ticketm.getValor(), ticketm.getDescuento(), ticketm.getMonto()};
                    modeloDatos.addRow(fila);
                }
                Total.setText("" + ventam.getTotalMonto());
            }
        }
    }
    private void VendidoCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_VendidoCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_VendidoCaretPositionChanged

    private void VendidoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_VendidoFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_VendidoFocusGained

    // Método para limpiar la tabla de ventas
    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiarTabla(); // Llama al método limpiarTabla() para limpiar la tabla
        permiteingreso = true;
        totalEntradasVendidas=0;

        Total.setText("");
    }//GEN-LAST:event_limpiarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Iterar sobre las filas de la tabla y agregarlas a la lista de ventas
        if (permiteingreso) {
            ArrayList<Ticket> tickets = new ArrayList<>();
            for (int i = 0; i < modeloDatos.getRowCount(); i++) {
                String funcion = modeloDatos.getValueAt(i, 0).toString();
                String asiento = modeloDatos.getValueAt(i, 1).toString();
                int edad = (int) modeloDatos.getValueAt(i, 2);
                String tipoEntrada = modeloDatos.getValueAt(i, 3).toString();
                int precio = (int) modeloDatos.getValueAt(i, 4);
                int dcto = (int) modeloDatos.getValueAt(i, 5);
                int precioFinal = (int) modeloDatos.getValueAt(i, 6);

                // Crear un objeto Venta y agregarlo a la lista de ventas
                tickets.add(new Ticket(funcion,tipoEntrada, asiento, edad,precio,dcto));

            }
            Venta venta1=  new Venta(idVenta, new ArrayList<>(tickets));

            System.out.println(venta1.boleta() );
            ventas.add(venta1);
            idVenta = idVenta +1;
            // Limpiar la tabla y actualizar el total a cancelar
            //tickets.clear();
            modeloDatos.setRowCount(0);
            Total.setText("");
            tEdad.setText("");
            tipoAsiento.setSelectedItem("A1");
            totalEntradasVendidas=0;

            try {
                modeloDatos2.setRowCount(0);
            } catch (Exception e) {
                // Manejo de la excepción
                System.out.println("Ha ocurrido un error: " + e.getMessage());
            }
            for (Venta ventam : ventas) {

                Object[] fila = {ventam.getId(), ventam.getTotalticket(), ventam.getTotalMonto(), ventam.getMarcaTemporal()};
                modeloDatos2.addRow(fila);

            }
             actualizaPrecios();   
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Obtener los valores de los campos
        int nunAsientos = GetAsiento(Combofuncion.getSelectedIndex(),tipoAsiento.getSelectedIndex());
        if (permiteingreso && nunAsientos > 0) {
            String asiento = tipoAsiento.getSelectedItem().toString();
            String funcion = Combofuncion.getSelectedItem().toString();
            int edad = Integer.parseInt(tEdad.getText()); // Convertir el texto a entero
            String entradaT; // Variable para almacenar el tipo de entrada
            int precioBase; // Variable para almacenar el precio base
            int descuento; // Variable para almacenar el descuento
            int precioFinal; // Variable para almacenar el precio final
            int[] entrada = new int[2];
            int seleccionF=Combofuncion.getSelectedIndex();
            int seleccionA = tipoAsiento.getSelectedIndex();
            entrada = getTipoEntrada(edad);
            entradaT=tipoEntrada.get(entrada[0]);
            descuento =entrada[1];
            // Calcular el precio
            //int[] retorno = calcularPrecio(edad, totalEntradasVendidas); // Ajuste aquí
            precioBase = getPrecio(seleccionF,seleccionA); // Obtener el precio base
            precioFinal = precioBase - precioBase*descuento/100;// Obtener el precio final

            // Agregar los valores a la tabla
            Object[] fila = {funcion,asiento, edad, entradaT, precioBase, descuento, precioFinal};

            modeloDatos.addRow(fila);

            // Actualizar el total a cancelar
            actualizarTotal();

            // Incrementa el contador de entradas vendidas
            totalEntradasVendidas++;

            // Descontar los asientos vendidos del total de asientos disponibles
            modificaAsiento(seleccionF,seleccionA,-1);
            //descontarAsientos(asiento, 1); // Se descuenta solo un asiento
            actualizarAsientosDisponibles();
            tEdad.setText("");

            // Actualizar la tabla para reflejar el descuento adicional si es necesario
            if (totalEntradasVendidas >= 6) {
                actualizarTabla();
            }

            // Guardar el arreglo retorno como atributo de la clase Pantalla
            this.retorno = retorno;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tEdadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tEdadKeyTyped
        char car = evt.getKeyChar();
        String textoActual = tEdad.getText();

        // Verifica si el carácter es un dígito y si el texto actual tiene menos de dos dígitos
        if (!Character.isDigit(car) || textoActual.length() >= 2) {
            evt.consume(); // No permite ingresar más caracteres
        }
    }//GEN-LAST:event_tEdadKeyTyped

    private void tipoAsientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoAsientoActionPerformed

    }//GEN-LAST:event_tipoAsientoActionPerformed

    private void tipoAsientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoAsientoItemStateChanged
        // TODO add your handling code here:
        //String elementoSeleccionado = (String) Combofuncion.getSelectedItem();
        //JOptionPane.showMessageDialog(null, elementoSeleccionado, "cambio:", JOptionPane.INFORMATION_MESSAGE);
        actualizarAsientosDisponibles();
        actualizaPrecios();
    }//GEN-LAST:event_tipoAsientoItemStateChanged

    private void CombofuncionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CombofuncionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CombofuncionActionPerformed

    private void CombofuncionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CombofuncionItemStateChanged
        actualizarAsientosDisponibles();  
        actualizaPrecios();// TODO add your handling code here:
    }//GEN-LAST:event_CombofuncionItemStateChanged

   
    // CONTINUACION DE METODOS DE LA CLASE PRINCIPAL
    // 
    // Método para actualizar la cantidad de asientos disponibles
    private void actualizarAsientosDisponibles() {
        int funcionSeleccionada = Combofuncion.getSelectedIndex();
        int[] capacidad = null;
        switch (funcionSeleccionada) {
            case 0 -> capacidad = funcion1.getCapacidad();
            case 1 -> capacidad = funcion2.getCapacidad();
            case 2 -> capacidad = funcion3.getCapacidad();                
        }
        
        int asientoselect = tipoAsiento.getSelectedIndex();
        int asientosDisponibles = capacidad[asientoselect];
        
        jLabel6.setText("plazas disponibles: " + asientosDisponibles);
    }
    
   
   
    // Método para actualizar la tabla
    private void actualizarTabla() {
        for (int i = 0; i < modeloDatos.getRowCount(); i++) {
            String asiento = modeloDatos.getValueAt(i, 0).toString();
            int edad = (int) modeloDatos.getValueAt(i, 1);
            int[] entrada = getTipoEntrada(edad);
            int descuento =entrada[1];
            
            int precioBase = getPrecio(funcionCod.get("Lala"),asientoCod.get(asiento)); // Obtener el precio base
            int precioFinal = precioBase - precioBase*descuento/100;// Obtener el precio final

                     
           modeloDatos.setValueAt(precioBase, i, 3);
           modeloDatos.setValueAt(descuento, i, 4);
           modeloDatos.setValueAt(precioFinal, i, 5);
        }
    }

   
    
    private void limpiarTabla() {
        if (permiteingreso){
            int rowCount = modeloDatos.getRowCount();

            for (int i = 0; i < rowCount; i++) {
                Object asiento = modeloDatos.getValueAt(i, 1);
                Object funcion = modeloDatos.getValueAt(i, 0);// "columnaAsiento" es el índice de la columna Asiento
                modificaAsiento(funcionCod.get(funcion.toString()),asientoCod.get(asiento.toString()),1);
               // incrementaAsientos(asiento.toString(),1);
                //System.out.println("Asiento: " + asiento.toString());
            }
            actualizarAsientosDisponibles();
        }
        modeloDatos.setRowCount(0); // Elimina todas las filas de la tabla
        Total.setText("");
        actualizaPrecios();
    }
    // Función para obtener el precio base según la función y asiento
    private int getPrecio(int seleccionF,int seleccionA) {
        int precioBase;
        int[] precios = null;
        switch (seleccionF) {
            case 0 -> precios = funcion1.getPrecio();
            case 1 -> precios = funcion2.getPrecio();
            case 2 -> precios = funcion3.getPrecio();                
        }
        precioBase = precios[seleccionA];  
       
    
        return precioBase;
    }
    
      // Función para obtener el precio base según la función y asiento
    private void modificaAsiento(int seleccionF,int seleccionA, int val) {
        int precioBase;
        int[] capacidad = null;
        switch (seleccionF) {
            case 0 -> {
                capacidad= funcion1.getCapacidad();
                capacidad[seleccionA]= capacidad[seleccionA] + val;
                funcion1.setCapacidad(capacidad);
            }
            case 1 -> {
                capacidad= funcion2.getCapacidad();
                capacidad[seleccionA]= capacidad[seleccionA] + val;
                funcion2.setCapacidad(capacidad);
            }
            case 2 -> {
                capacidad= funcion3.getCapacidad();
                capacidad[seleccionA]= capacidad[seleccionA] + val;
                funcion3.setCapacidad(capacidad);
            }
                           
        }
        
    }
      public int GetAsiento(int seleccionF,int seleccionA) {
        int[] capacidad = null;
        int num;
        switch (seleccionF) {
            case 0 -> capacidad= funcion1.getCapacidad();                            
            case 1 -> capacidad= funcion2.getCapacidad();
            case 2 -> capacidad= funcion3.getCapacidad();                   
        }
        num = capacidad[seleccionA];
        return num;        
      }
    
    // Función para obtener el tipo de entrada y el descuento
      private int[] getTipoEntrada(int edad) {
        int[] valores = new int[2];  
        if(edad < 18){
            valores[0]=1;
            valores[1]=10;        
        }
        else if(edad > 64){
            valores[0]=3;
            valores[1]=15;
        }
        else{
             valores[0]=2;
            valores[1]=0;
        }
        if (totalEntradasVendidas >= 6){
            valores[1]=valores[1]+10;
        }
        return valores;
    }
    // Método para actualizar el total a cancelar
    private void actualizarTotal() {
    // Suma
    int total = 0;
    for (int i = 0; i < modeloDatos.getRowCount(); i++) {
        total += (int) modeloDatos.getValueAt(i, 6); // Sumar el precio de cada fila
    }
    Total.setText("$" + Integer.toString(total)); // Actualizar el label del total a cancelar
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable Carrito;
    private javax.swing.JComboBox<String> Combofuncion;
    private javax.swing.JLabel Total;
    private javax.swing.JTable Vendido;
    private javax.swing.JLabel cant0;
    private javax.swing.JLabel cant1;
    private javax.swing.JLabel cant2;
    private javax.swing.JLabel cant3;
    private javax.swing.JLabel cant4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JDesktopPane jDesktopPane3;
    private javax.swing.JDesktopPane jDesktopPane4;
    private javax.swing.JDesktopPane jDesktopPane5;
    private javax.swing.JDesktopPane jDesktopPane6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limpiar;
    private javax.swing.JLabel precio0;
    private javax.swing.JLabel precio1;
    private javax.swing.JLabel precio2;
    private javax.swing.JLabel precio3;
    private javax.swing.JLabel precio4;
    private javax.swing.JTextField tEdad;
    private javax.swing.JComboBox<String> tipoAsiento;
    private javax.swing.JButton verTicket;
    // End of variables declaration//GEN-END:variables
}
