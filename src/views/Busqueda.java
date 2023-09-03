package views;

import Dao.HuespedDao;
import Dao.ReservaDao;
import Modelos.Huesped;
import Modelos.Reserva;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import logica.CalcularReserva;
import logica.Conexion;
import static views.ReservasView.txtFechaEntrada;
import static views.ReservasView.txtFechaSalida;
import static views.ReservasView.txtValor;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;
    int xMouse, yMouse;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);

        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));

        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");
        JScrollPane scroll_table = new JScrollPane(tbReservas);
        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        //funcion que llena la tabla
        llenarTablaReservas();

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");
        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        //llenar tabla
        llenarTablaHuespedes();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);

        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //
                System.out.println("se orpimio el boton de buscar");
                
                System.out.println("buscar:" + txtBuscar.getText());
                //buscar y llenar tablas
                if (!txtBuscar.getText().equals("")) {
                    //vaciar tablas
                    modelo.setRowCount(0);
                    modeloHuesped.setRowCount(0);
                    llenarTablaReservasPorBuesqueda(txtBuscar.getText());
                    llenarTablaHuespedesPorBusqueda(txtBuscar.getText());
                }

            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);
        btnEliminar.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent e) {
                System.out.println("|hacer cosas");
                int filaReserva = tbReservas.getSelectedRow();
                int columnsReserva = 0;//columna 0, la del id
                int filaHuesped = tbHuespedes.getSelectedRow();
                if (filaReserva >= 0) {//checar cuando oprimo y no hay roe selected, -1 si no hay nada seleccionado
                    System.out.println("se elimina reserva");
                    //obtener datos
                    tbHuespedes.getSelectionModel().clearSelection();//limpio seleccin de la otra tabla si lahay
                    String value = tbReservas.getModel().getValueAt(filaReserva, columnsReserva).toString();
                    int idReservaEliminar = Integer.valueOf(value);
                    System.out.println(idReservaEliminar);
                    //actualizar
                    eliminarReserva(idReservaEliminar);
                    //cargar de nuevo
                    System.out.println("filaR:" + filaReserva);
                    System.out.println("reserva d");
                    modelo.setRowCount(0);
                    modeloHuesped.setRowCount(0);
                    llenarTablaReservas();
                    llenarTablaHuespedes();
                } else {

                    if (filaHuesped >= 0) {//checar cuando oprimo y no hay roe selected, -1 si no hay nada seleccionado
                        System.out.println("a");
                        tbReservas.getSelectionModel().clearSelection();//vaciar seleccion de la otra tabla
                        System.out.println("b");
                        System.out.println("filaH:" + filaHuesped);
                        System.out.println("aaaaa"+tbHuespedes.getModel().getValueAt(filaHuesped, columnsReserva).toString());
                        String valueH = tbHuespedes.getModel().getValueAt(filaHuesped, columnsReserva).toString();
                        System.out.println("c");
                        int idHuespedEliminar = Integer.valueOf(valueH);
                        //
                        System.out.println("filaH:" + filaHuesped);
                        System.out.println("huesped d");
                        eliminarHuesped(idHuespedEliminar);
                        modeloHuesped.setRowCount(0);
                        modelo.setRowCount(0);
                        llenarTablaReservas();
                        llenarTablaHuespedes();
                        
                    }

                }
            }

        });

        btnEditar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                System.out.println("se oprimio editar");
                int filaReserva = tbReservas.getSelectedRow();//obtener fila seleccionada
                int filaHuesped = tbHuespedes.getSelectedRow();

                if (filaReserva >= 0) {
                    //extraer los datos de las columnas de reservas
                    String idEditadoStr = tbReservas.getModel().getValueAt(filaReserva, 0).toString();
                    int idEditado = Integer.parseInt(idEditadoStr);
                    String fechaEntradaEditadaStr = tbReservas.getModel().getValueAt(filaReserva, 1).toString();
                    String fechaSalidaEditadaStr = tbReservas.getModel().getValueAt(filaReserva, 2).toString();
                    
                    String formaDePagoStr = tbReservas.getModel().getValueAt(filaReserva, 4).toString();

                    //String value = tbReservas.getModel().getValueAt(filaReserva, columnsReserva).toString();
                    if (tbReservas.getSelectedColumn() == 0) {
                        JOptionPane.showMessageDialog(null, "No se puede modificar el id");

                        System.out.println("no se puede modificar el id");
                        return;
                    }
                    if (tbReservas.getSelectedColumn() == 3) {
                        JOptionPane.showMessageDialog(null, "No se puede modificar el valor");

                        System.out.println("no se puede modificar el valor");
                        return;
                    }
                    //calcular el nuevo valor y fijarlo
                    LocalDate localDate1 = LocalDate.parse(fechaEntradaEditadaStr);
                    LocalDate localDate2 = LocalDate.parse(fechaSalidaEditadaStr);
                    //System.out.println("FECHAS:" + fechaEntradaEditadaStr+", " + fechaSalidaEditadaStr);
                    double cantidadPorPagar = CalcularReserva.calcularReserva(localDate1, localDate2);
                    //System.out.println("total por pagar: " + cantidadPorPagar);
                    String cantidadPorPagarString = String.valueOf(cantidadPorPagar);

                    //fijar el nuevo valor en la tabla
                    if (tbReservas.getSelectedColumn() == 1 || tbReservas.getSelectedColumn() == 2) {
                        System.out.println("se cambio la fecha recalcular el valor en:" + tbReservas.getSelectedRow() + ", " + "3");
                        tbReservas.setValueAt(cantidadPorPagarString, tbReservas.getSelectedRow(), 3);
                    }
                    //crear modelo
                    Reserva reservaActualizado = new Reserva(idEditado, localDate1, localDate2, cantidadPorPagar, formaDePagoStr);
                    actualizarReserva(reservaActualizado);
                    //guardarhuesped
                    System.out.println("datos editados: " + idEditadoStr + ", " + fechaEntradaEditadaStr + ", " + fechaSalidaEditadaStr + ", " + cantidadPorPagar + ", " + formaDePagoStr);
                    modelo.setRowCount(0);
                    llenarTablaReservas();
                } else {
                    if (filaHuesped >= 0) {
                        System.out.println("dentro de editar huesped");
                        //extraer los datos de las columnas de huespedes
                        String idEditadoStr = tbHuespedes.getModel().getValueAt(filaHuesped, 0).toString();
                        int idEditado = Integer.parseInt(idEditadoStr);
                        String nombreEditadoStr = tbHuespedes.getModel().getValueAt(filaHuesped, 1).toString();
                        String apellidoEditadoStr = tbHuespedes.getModel().getValueAt(filaHuesped, 2).toString();
                        String fechaNacimientoEditadaStr = tbHuespedes.getModel().getValueAt(filaHuesped, 3).toString();
                        String nacionalidadEditadoStr = tbHuespedes.getModel().getValueAt(filaHuesped, 4).toString();
                        String telefonoEditadoStr = tbHuespedes.getModel().getValueAt(filaHuesped, 5).toString();
                        int telefonoEditado = Integer.parseInt(telefonoEditadoStr);
                        String idRecervaStr = tbHuespedes.getModel().getValueAt(filaHuesped, 6).toString();
                        int idReserva = Integer.parseInt(idRecervaStr);
                        
                        LocalDate localDate1 = LocalDate.parse(fechaNacimientoEditadaStr);
                        
                        if (tbReservas.getSelectedColumn() == 0) {
                            JOptionPane.showMessageDialog(null, "No se puede modificar el id");

                            System.out.println("no se puede modificar el id");
                            return;
                        }
                                                               
                       
                        //crear modelo
                        Huesped huespedActualizado = new Huesped(idEditado,nombreEditadoStr,apellidoEditadoStr,localDate1,nacionalidadEditadoStr,telefonoEditado,idReserva);
                        actualizarHuesped(huespedActualizado);
                        //guardarhuesped
                        System.out.println("datos editados: " + idEditadoStr + ", " + fechaNacimientoEditadaStr + ", " +nacionalidadEditadoStr + ", " + nombreEditadoStr + ", " + apellidoEditadoStr);
                        modeloHuesped.setRowCount(0);
                        llenarTablaHuespedes();
                    }
                }
            }
        });

    }

//Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

    //dibujar tabla de reservas
    private void llenarTablaReservas() {

        try {
            Conexion con = new Conexion();
            //obtener datos de la consulta
            ReservaDao rd = new ReservaDao(con.recuperaConexion());
            List<Reserva> r1 = rd.listarReservas();

            Object[] newRow = new Object[modelo.getColumnCount()];

            for (Reserva reserva : r1) {
                //imprimimos el objeto pivote
                newRow[0] = reserva.getId();
                newRow[1] = reserva.getFechaEntrada();
                newRow[2] = reserva.getFechaSalida();
                newRow[3] = reserva.getValor();
                newRow[4] = reserva.getFormaDePago();
                modelo.addRow(newRow);
            }

        } catch (SQLException ex) {
        }

    }

    //dibujar tabla de huespedes
    private void llenarTablaHuespedes() {

        try {
            Conexion con = new Conexion();
            //obtener datos de la consulta
            HuespedDao hd = new HuespedDao(con.recuperaConexion());
            List<Huesped> h1 = hd.listarHuespedes();
            System.out.println("" + h1.size());
            Object[] newRowHuesped = new Object[modeloHuesped.getColumnCount()];

            for (Huesped huesped : h1) {
                //imprimimos el objeto pivote
                newRowHuesped[0] = huesped.getId();
                newRowHuesped[1] = huesped.getNombre();
                newRowHuesped[2] = huesped.getApellido();
                newRowHuesped[3] = huesped.getFechaNacimiento();
                newRowHuesped[4] = huesped.getNacionalidad();
                newRowHuesped[5] = huesped.getTelefono();
                newRowHuesped[6] = huesped.getIdResrva();

                modeloHuesped.addRow(newRowHuesped);
            }

        } catch (SQLException ex) {
        }

    }

    //dibujar tabla de reservas en base a busqueda
    private void llenarTablaReservasPorBuesqueda(String input) {
        try {
            Conexion con = new Conexion();
            //obtener datos de la consulta
            ReservaDao rdBusqueda = new ReservaDao(con.recuperaConexion());
            List<Reserva> reservaBusqueda = rdBusqueda.listarReservasPorBusqueda(input);
            if(reservaBusqueda.size() == 0){
                 JOptionPane.showMessageDialog(null, "No hay coincidencias en reservas");
                System.out.println("no hay coincidencias en reservas");
                
                return;
            }
            Object[] newRowBusqueda = new Object[modelo.getColumnCount()];
            System.out.println("llenando tabla de reserva por busqueda");
            for (Reserva reservaB : reservaBusqueda) {
                //imprimimos el objeto pivote
                newRowBusqueda[0] = reservaB.getId();
                newRowBusqueda[1] = reservaB.getFechaEntrada();
                newRowBusqueda[2] = reservaB.getFechaSalida();
                newRowBusqueda[3] = reservaB.getValor();
                newRowBusqueda[4] = reservaB.getFormaDePago();
                modelo.addRow(newRowBusqueda);
            }

        } catch (SQLException ex) {
            System.out.println("error al cargar algo");
        }
    }

    private void llenarTablaHuespedesPorBusqueda(String input) {

        try {
            Conexion con = new Conexion();
            //obtener datos de la consulta
            HuespedDao hd = new HuespedDao(con.recuperaConexion());
            List<Huesped> h1 = hd.listarHuespedesPorBusqueda(input);
            //System.out.println("" + h1.size());
            if(h1.size() == 0){
                 JOptionPane.showMessageDialog(null, "No hay coincidencias en huespedes");
                System.out.println("no hay coincidencias en huespedes");
                return;
            }
            Object[] newRowHuesped = new Object[modeloHuesped.getColumnCount()];
            System.out.println("llenando tabla de huesped por busqueda");
            for (Huesped huesped : h1) {
                //imprimimos el objeto pivote
                newRowHuesped[0] = huesped.getId();
                newRowHuesped[1] = huesped.getNombre();
                newRowHuesped[2] = huesped.getApellido();
                newRowHuesped[3] = huesped.getFechaNacimiento();
                newRowHuesped[4] = huesped.getNacionalidad();
                newRowHuesped[5] = huesped.getTelefono();
                newRowHuesped[6] = huesped.getIdResrva();

                modeloHuesped.addRow(newRowHuesped);
            }

        } catch (SQLException ex) {
            System.out.println("error al cargar algo");
        }

    }

    private void eliminarReserva(int idReservaEliminar) {
        try {
            Conexion con = new Conexion();
            ReservaDao rdEliminar = new ReservaDao(con.recuperaConexion());
            int resultado = rdEliminar.eliminarReserva(idReservaEliminar);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Eliminacion exitosa");

            }
        } catch (Exception e) {
        }
    }

    private void eliminarHuesped(int idHuespedEliminar) {
        try {
            Conexion con = new Conexion();
            HuespedDao hdEliminar = new HuespedDao(con.recuperaConexion());
            int resultado = hdEliminar.eliminarHuesped(idHuespedEliminar);
            if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Eliminacion de huesped exitosa");

            }
        } catch (Exception e) {
        }
    }

    private void actualizarReserva(Reserva reserva) {
        try {
            Conexion con = new Conexion();
            ReservaDao hd = new ReservaDao(con.recuperaConexion());

           int resultado =  hd.actualizarReserva(reserva);
           if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Actualizacion de Reserva exitosa");

            }
        } catch (Exception e) {
        }
    }
    
    public void actualizarHuesped(Huesped huesped){
      try {
            Conexion con = new Conexion();
            HuespedDao hd = new HuespedDao(con.recuperaConexion());

           int resultado =  hd.actualizarHuesped(huesped);
           if (resultado > 0) {
                JOptionPane.showMessageDialog(null, "Actualizacion de Huesped exitosa");

            }
           
        } catch (Exception e) {
        }
    }

}
