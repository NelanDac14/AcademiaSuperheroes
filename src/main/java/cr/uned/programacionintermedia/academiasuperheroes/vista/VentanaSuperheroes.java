/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cr.uned.programacionintermedia.academiasuperheroes.vista;

import cr.uned.programacionintermedia.academiasuperheroes.servicio.GestionSuperheroes;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.Superheroe;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeTecnologico;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMutante;
import cr.uned.programacionintermedia.academiasuperheroes.modelo.HeroeMagico;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Ventana gráfica de Superhéroes
 *
 * Se encarga de mostrar el entorno gráfico donde se desarrollará toda la
 * gestión de superhéroes
 *
 * @author Nelson Andres Acuña Cambronero
 *
 * @version 1.0
 */
public class VentanaSuperheroes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaSuperheroes.class.getName());
    private final GestionSuperheroes gestionSuperheroes;

    /**
     * Constructor para pruebas independientes.
     */
    public VentanaSuperheroes() {
        this(new GestionSuperheroes());
    }

    /**
     * Constructor principal.
     *
     * @param gestionSuperheroes Administrador compartido.
     */
    public VentanaSuperheroes(
            GestionSuperheroes gestionSuperheroes) {

        initComponents();

        this.gestionSuperheroes = gestionSuperheroes;

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        configurarTablaSuperheroes();
        configurarAtributosEspecificos();
        configurarEventos();

        txtfNombreHeroico.setText("");
        txtfNombreReal.setText("");

        actualizarTablaSuperheroes();
    }

    /**
     * Configura la tabla para mostrar los superhéroes.
     */
    private void configurarTablaSuperheroes() {

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "ID",
                    "Nombre heroico",
                    "Nombre real",
                    "Tipo",
                    "Estado",
                    "Salario mensual",
                    "Bonificación"
                }
        ) {
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tableSuperheroes.setModel(modelo);
        tableSuperheroes.setSelectionMode(
                javax.swing.ListSelectionModel.SINGLE_SELECTION
        );
        tableSuperheroes.setColumnSelectionAllowed(false);
        tableSuperheroes.setRowSelectionAllowed(true);
    }

    /**
     * Actualiza la información mostrada en la tabla.
     */
    private void actualizarTablaSuperheroes() {

        DefaultTableModel modelo
                = (DefaultTableModel) tableSuperheroes.getModel();

        modelo.setRowCount(0);

        ArrayList<Superheroe> heroes
                = gestionSuperheroes.obtenerTodosLosSuperheroes();

        for (Superheroe heroe : heroes) {

            String tipo;

            if (heroe instanceof HeroeTecnologico) {
                tipo = "Tecnológico";

            } else if (heroe instanceof HeroeMutante) {
                tipo = "Mutante";

            } else {
                tipo = "Mágico";
            }

            modelo.addRow(new Object[]{
                heroe.getIdHeroe(),
                heroe.getNombreHeroico(),
                heroe.getNombreReal(),
                tipo,
                heroe.getEstado(),
                heroe.getSalarioMensual(),
                heroe.calcularBonificacion()
            });
        }
    }

    /**
     * Cambia las opciones de los atributos específicos según el tipo de
     * superhéroe seleccionado.
     */
    private void configurarAtributosEspecificos() {

        String tipo = String.valueOf(
                jcbTipoSuperheroe.getSelectedItem()
        );

        jcbEspecificoString.removeAllItems();
        jcbEspecificoString.addItem("Elige una opción...");

        if (tipo.equals("Tecnológico")) {

            txtEspecificoString.setText("Nivel tecnológico");
            txtEspecificoInt.setText("Cantidad de gadgets");

            jcbEspecificoString.addItem("Básico");
            jcbEspecificoString.addItem("Intermedio");
            jcbEspecificoString.addItem("Avanzado");

            txtfEspecificoInt.setValue(0);

        } else if (tipo.equals("Mutante")) {

            txtEspecificoString.setText("Tipo de mutación");
            txtEspecificoInt.setText("Control de poder (1-100)");

            jcbEspecificoString.addItem("Genética");
            jcbEspecificoString.addItem("Radioactiva");
            jcbEspecificoString.addItem("Experimental");

            txtfEspecificoInt.setValue(1);

        } else if (tipo.equals("Mágico")) {

            txtEspecificoString.setText("Tipo de magia");
            txtEspecificoInt.setText("Cantidad de hechizos");

            jcbEspecificoString.addItem("Elemental");
            jcbEspecificoString.addItem("Oscura");
            jcbEspecificoString.addItem("Sagrada");

            txtfEspecificoInt.setValue(0);

        } else {

            txtEspecificoString.setText("Atributo específico");
            txtEspecificoInt.setText("Cantidad");
            txtfEspecificoInt.setValue(0);
        }

        jcbEspecificoString.setSelectedIndex(0);
    }

    /**
     * Conecta los botones y selectores con sus operaciones.
     */
    private void configurarEventos() {

        // Actualiza los atributos específicos cuando cambia el tipo de héroe.
        jcbTipoSuperheroe.addActionListener(
                e -> configurarAtributosEspecificos()
        );

        // Carga los datos del héroe cuando se selecciona una fila de la tabla.
        tableSuperheroes.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()
                    && tableSuperheroes.getSelectedRow() >= 0) {
                cargarSuperheroeSeleccionado();
            }
        });
    }

    /**
     * Recopila los datos del formulario y registra el superhéroe según el tipo
     * seleccionado.
     */
    private void registrarSuperheroe() {

        try {

            String nombreHeroico = txtfNombreHeroico.getText().trim();
            String nombreReal = txtfNombreReal.getText().trim();

            String estado = String.valueOf(jCB_Estado.getSelectedItem());
            String tipo = String.valueOf(jcbTipoSuperheroe.getSelectedItem());

            // Validamos las selecciones iniciales.
            if (estado.startsWith("Elige")
                    || tipo.startsWith("Elige")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccioná el estado y tipo de superhéroe.",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Recuperamos el salario.
            fTxtF_SalarioMensual.commitEdit();

            Object valorSalario = fTxtF_SalarioMensual.getValue();

            if (!(valorSalario instanceof Number)) {
                throw new NumberFormatException("Salario inválido.");
            }

            double salario
                    = ((Number) valorSalario).doubleValue();

            // Recuperamos los atributos específicos.
            String atributoString
                    = String.valueOf(jcbEspecificoString.getSelectedItem());

            txtfEspecificoInt.commitEdit();

            Object valorEntero = txtfEspecificoInt.getValue();

            if (!(valorEntero instanceof Number)) {
                throw new NumberFormatException(
                        "El atributo numérico es inválido.");
            }

            int atributoEntero
                    = ((Number) valorEntero).intValue();

            if (atributoString.startsWith("Elige")) {
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccioná el atributo específico.",
                        "Datos incompletos",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            boolean registrado = false;

            // Registramos según el tipo de superhéroe.
            switch (tipo) {

                case "Tecnológico":

                    registrado
                            = gestionSuperheroes.agregarHeroeTecnologico(
                                    nombreHeroico,
                                    nombreReal,
                                    estado,
                                    salario,
                                    atributoString,
                                    atributoEntero
                            );

                    break;

                case "Mutante":

                    registrado
                            = gestionSuperheroes.agregarHeroeMutante(
                                    nombreHeroico,
                                    nombreReal,
                                    estado,
                                    salario,
                                    atributoString,
                                    atributoEntero
                            );

                    break;

                case "Mágico":

                    registrado
                            = gestionSuperheroes.agregarHeroeMagico(
                                    nombreHeroico,
                                    nombreReal,
                                    estado,
                                    salario,
                                    atributoString,
                                    atributoEntero
                            );

                    break;
            }

            if (registrado) {

                // Actualizamos la tabla.
                actualizarTablaSuperheroes();

                // Mostramos el último identificador generado.
                txtIdHeroe.setText(
                        String.format(
                                "HER-%02d",
                                Superheroe.getContadorId()
                        )
                );

                JOptionPane.showMessageDialog(
                        this,
                        "Superhéroe registrado correctamente.\n"
                        + "Identificador: " + txtIdHeroe.getText(),
                        "Registro exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );

                limpiarFormulario();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible registrar el superhéroe.\n"
                        + "Revisá los nombres, salario y atributos.",
                        "Error de validación",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (ParseException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Verificá que los valores numéricos sean correctos.",
                    "Error en los datos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Restablece los campos para ingresar otro superhéroe.
     */
    private void limpiarFormulario() {

        txtfNombreHeroico.setText("");
        txtfNombreReal.setText("");

        jCB_Estado.setSelectedIndex(0);
        jcbTipoSuperheroe.setSelectedIndex(0);

        fTxtF_SalarioMensual.setValue(0.00);

        tableSuperheroes.clearSelection();
        configurarAtributosEspecificos();

        txtfNombreHeroico.requestFocus();
    }

    /**
     * Obtiene el identificador del superhéroe seleccionado en la tabla.
     *
     * @return Identificador seleccionado o null si no hay selección.
     */
    private String obtenerIdSeleccionado() {

        int filaVista = tableSuperheroes.getSelectedRow();

        if (filaVista < 0) {
            return null;
        }

        int filaModelo = tableSuperheroes.convertRowIndexToModel(filaVista);

        Object valorId = tableSuperheroes.getModel().getValueAt(filaModelo, 0);

        return valorId == null ? null : valorId.toString();
    }

    /**
     * Carga los datos del superhéroe seleccionado en los componentes del
     * formulario.
     */
    private void cargarSuperheroeSeleccionado() {

        String id = obtenerIdSeleccionado();

        if (id == null) {
            return;
        }

        Superheroe heroe = gestionSuperheroes.buscarHeroePorId(id);

        if (heroe == null) {
            return;
        }

        txtIdHeroe.setText(heroe.getIdHeroe());

        txtfNombreHeroico.setText(heroe.getNombreHeroico());
        txtfNombreReal.setText(heroe.getNombreReal());

        jCB_Estado.setSelectedItem(heroe.getEstado());

        fTxtF_SalarioMensual.setValue(heroe.getSalarioMensual());

        // Identificamos el tipo y cargamos sus atributos.
        if (heroe instanceof HeroeTecnologico) {

            HeroeTecnologico tecnologico = (HeroeTecnologico) heroe;

            jcbTipoSuperheroe.setSelectedItem("Tecnológico");
            configurarAtributosEspecificos();

            jcbEspecificoString.setSelectedItem(
                    tecnologico.getNivelTecnologia());

            txtfEspecificoInt.setValue(
                    tecnologico.getCantidadGadgets());

        } else if (heroe instanceof HeroeMutante) {

            HeroeMutante mutante = (HeroeMutante) heroe;

            jcbTipoSuperheroe.setSelectedItem("Mutante");
            configurarAtributosEspecificos();

            jcbEspecificoString.setSelectedItem(
                    mutante.getTipoMutacion());

            txtfEspecificoInt.setValue(
                    mutante.getControlPoder());

        } else if (heroe instanceof HeroeMagico) {

            HeroeMagico magico = (HeroeMagico) heroe;

            jcbTipoSuperheroe.setSelectedItem("Mágico");
            configurarAtributosEspecificos();

            jcbEspecificoString.setSelectedItem(
                    magico.getTipoMagia());

            txtfEspecificoInt.setValue(
                    magico.getCantHechizos());
        }
    }

    /**
     * Actualiza los datos del superhéroe seleccionado.
     */
    private void actualizarSuperheroe() {

        String id = obtenerIdSeleccionado();

        if (id == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná un superhéroe de la tabla.",
                    "Selección requerida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {

            String nombreHeroico = txtfNombreHeroico.getText().trim();
            String nombreReal = txtfNombreReal.getText().trim();

            String estado = String.valueOf(jCB_Estado.getSelectedItem());
            String tipo = String.valueOf(jcbTipoSuperheroe.getSelectedItem());

            fTxtF_SalarioMensual.commitEdit();
            txtfEspecificoInt.commitEdit();

            Object salarioValor = fTxtF_SalarioMensual.getValue();
            Object enteroValor = txtfEspecificoInt.getValue();

            if (!(salarioValor instanceof Number)
                    || !(enteroValor instanceof Number)) {

                throw new NumberFormatException("Revisá los valores numéricos.");
            }

            double salario = ((Number) salarioValor).doubleValue();
            int cantidad = ((Number) enteroValor).intValue();

            String atributo = String.valueOf(
                    jcbEspecificoString.getSelectedItem());

            Superheroe nuevosDatos;

            switch (tipo) {

                case "Tecnológico":

                    nuevosDatos = new HeroeTecnologico(
                            atributo,
                            cantidad,
                            id,
                            nombreHeroico,
                            nombreReal,
                            estado,
                            salario
                    );
                    break;

                case "Mutante":

                    nuevosDatos = new HeroeMutante(
                            atributo,
                            cantidad,
                            id,
                            nombreHeroico,
                            nombreReal,
                            estado,
                            salario
                    );
                    break;

                case "Mágico":

                    nuevosDatos = new HeroeMagico(
                            atributo,
                            cantidad,
                            id,
                            nombreHeroico,
                            nombreReal,
                            estado,
                            salario
                    );
                    break;

                default:

                    JOptionPane.showMessageDialog(
                            this,
                            "Seleccioná un tipo de superhéroe válido."
                    );
                    return;
            }

            boolean actualizado
                    = gestionSuperheroes.actualizarHeroe(id, nuevosDatos);

            if (actualizado) {

                actualizarTablaSuperheroes();

                seleccionarFilaPorId(id);

                JOptionPane.showMessageDialog(
                        this,
                        "Superhéroe actualizado correctamente.",
                        "Actualización exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "No fue posible actualizar el superhéroe.\n"
                        + "Verificá los datos y el tipo de héroe.",
                        "Error de actualización",
                        JOptionPane.WARNING_MESSAGE
                );
            }

        } catch (ParseException | NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Verificá los valores numéricos ingresados.",
                    "Datos incorrectos",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Cambia el estado del superhéroe seleccionado.
     */
    private void cambiarEstadoSeleccionado() {

        String id = obtenerIdSeleccionado();

        if (id == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná un superhéroe de la tabla.",
                    "Selección requerida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String nuevoEstado = String.valueOf(
                jCB_Estado.getSelectedItem());

        if (nuevoEstado.startsWith("Elige")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná el nuevo estado.",
                    "Estado requerido",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        boolean cambiado
                = gestionSuperheroes.cambiarEstadoHeroe(id, nuevoEstado);

        if (cambiado) {

            actualizarTablaSuperheroes();
            seleccionarFilaPorId(id);

            JOptionPane.showMessageDialog(
                    this,
                    "Estado actualizado correctamente.",
                    "Operación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible cambiar el estado."
            );
        }
    }

    /**
     * Elimina el superhéroe seleccionado después de confirmar la operación.
     */
    private void eliminarSuperheroeSeleccionado() {

        String id = obtenerIdSeleccionado();

        if (id == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccioná un superhéroe de la tabla.",
                    "Selección requerida",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Deseás eliminar el superhéroe " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = gestionSuperheroes.eliminarHeroe(id);

        if (eliminado) {

            actualizarTablaSuperheroes();
            limpiarFormulario();

            txtIdHeroe.setText("HER-00");

            JOptionPane.showMessageDialog(
                    this,
                    "Superhéroe eliminado correctamente.",
                    "Eliminación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No fue posible eliminar el superhéroe."
            );
        }
    }
    
    /**
 * Selecciona una fila de la tabla mediante el identificador.
 *
 * @param id Identificador del superhéroe.
 */
private void seleccionarFilaPorId(String id) {

    DefaultTableModel modelo =
            (DefaultTableModel) tableSuperheroes.getModel();

    for (int i = 0; i < modelo.getRowCount(); i++) {

        String idTabla = modelo.getValueAt(i, 0).toString();

        if (idTabla.equalsIgnoreCase(id)) {

            int filaVista =
                    tableSuperheroes.convertRowIndexToView(i);

            tableSuperheroes.setRowSelectionInterval(
                    filaVista, filaVista);

            return;
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtfNombreHeroico = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtfNombreReal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        fTxtF_SalarioMensual = new javax.swing.JFormattedTextField();
        jCB_Estado = new javax.swing.JComboBox<>();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jcbTipoSuperheroe = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        txtEspecificoString = new javax.swing.JLabel();
        jcbEspecificoString = new javax.swing.JComboBox<>();
        txtEspecificoInt = new javax.swing.JLabel();
        txtfEspecificoInt = new javax.swing.JFormattedTextField();
        btnAgregar = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        btnCambiarEstado = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableSuperheroes = new javax.swing.JTable();
        btnGuardarDatos = new javax.swing.JButton();
        btnCargarDatos = new javax.swing.JButton();
        txtIdHeroe = new javax.swing.JLabel();
        txtIdHeroe1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("GESTIÓN DE SUPERHÉROES");

        jPanel1.setBackground(new java.awt.Color(11, 5, 78));

        jLabel1.setFont(new java.awt.Font("Aero Matics", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTIÓN DE SUPERHÉROES");

        jLabel2.setFont(new java.awt.Font("Aero Matics Display Light", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("ACADEMIA INTERNACIONAL DE SUPERHÉROES");

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator1.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Aero Matics", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DATOS GENERALES");

        jLabel4.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre heroico");

        txtfNombreHeroico.setText("Ingrese el nombre heroico");
        txtfNombreHeroico.addActionListener(this::txtfNombreHeroicoActionPerformed);

        jLabel5.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre real");

        txtfNombreReal.setText("Ingrese el nombre real");
        txtfNombreReal.addActionListener(this::txtfNombreRealActionPerformed);

        jLabel6.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Estado");

        jLabel7.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Salario mensual");

        fTxtF_SalarioMensual.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        fTxtF_SalarioMensual.setText("0.00");

        jCB_Estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una opción...", "Disponible", "En misión", "Recuperación" }));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator2.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Aero Matics", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("TIPO DE SUPERHÉROE");

        jcbTipoSuperheroe.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige el tipo de superhéroe...", "Tecnológico", "Mutante", "Mágico" }));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator3.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Aero Matics", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ATRIBUTOS ESPECÍFICOS");

        txtEspecificoString.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        txtEspecificoString.setForeground(new java.awt.Color(255, 255, 255));
        txtEspecificoString.setText("Tipo String");

        jcbEspecificoString.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Elige una opción..." }));

        txtEspecificoInt.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        txtEspecificoInt.setForeground(new java.awt.Color(255, 255, 255));
        txtEspecificoInt.setText("Tipo int");

        txtfEspecificoInt.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        btnAgregar.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(this::btnAgregarActionPerformed);

        btnActualizar.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnActualizar.setText("Actualizar");
        btnActualizar.addActionListener(this::btnActualizarActionPerformed);

        btnCambiarEstado.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnCambiarEstado.setText("Cambiar estado");
        btnCambiarEstado.addActionListener(this::btnCambiarEstadoActionPerformed);

        btnEliminar.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(this::btnEliminarActionPerformed);

        btnLimpiar.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jSeparator5.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N

        tableSuperheroes.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        tableSuperheroes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nombre heroico", "Nombre real", "Tipo", "Estado", "Salario", "Bonificación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableSuperheroes.setColumnSelectionAllowed(true);
        tableSuperheroes.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tableSuperheroes);
        tableSuperheroes.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnGuardarDatos.setBackground(new java.awt.Color(204, 204, 204));
        btnGuardarDatos.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnGuardarDatos.setText("Guardar datos");
        btnGuardarDatos.addActionListener(this::btnGuardarDatosActionPerformed);

        btnCargarDatos.setBackground(new java.awt.Color(204, 204, 204));
        btnCargarDatos.setFont(new java.awt.Font("Aero Matics", 0, 14)); // NOI18N
        btnCargarDatos.setText("Cargar datos");
        btnCargarDatos.addActionListener(this::btnCargarDatosActionPerformed);

        txtIdHeroe.setFont(new java.awt.Font("Aero Matics", 0, 18)); // NOI18N
        txtIdHeroe.setForeground(new java.awt.Color(255, 255, 255));
        txtIdHeroe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        txtIdHeroe.setText("HER-00");

        txtIdHeroe1.setFont(new java.awt.Font("Aero Matics", 0, 18)); // NOI18N
        txtIdHeroe1.setForeground(new java.awt.Color(255, 255, 255));
        txtIdHeroe1.setText("ID:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addComponent(jSeparator5)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6)
                            .addComponent(txtfNombreHeroico, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
                            .addComponent(jCB_Estado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel7)
                                .addComponent(jLabel5)
                                .addComponent(fTxtF_SalarioMensual)
                                .addComponent(txtfNombreReal, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtIdHeroe1)
                                .addGap(18, 18, 18)
                                .addComponent(txtIdHeroe))))
                    .addComponent(jcbTipoSuperheroe, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEspecificoString)
                            .addComponent(jcbEspecificoString, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAgregar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnCambiarEstado)
                                .addGap(18, 18, 18)
                                .addComponent(btnEliminar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtEspecificoInt)
                                    .addGap(285, 285, 285))
                                .addComponent(txtfEspecificoInt, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnGuardarDatos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCargarDatos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLimpiar)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtIdHeroe)
                        .addComponent(txtIdHeroe1)))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfNombreHeroico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfNombreReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fTxtF_SalarioMensual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCB_Estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jcbTipoSuperheroe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEspecificoInt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtfEspecificoInt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtEspecificoString)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbEspecificoString, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar)
                    .addComponent(btnActualizar)
                    .addComponent(btnCambiarEstado)
                    .addComponent(btnEliminar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnGuardarDatos)
                    .addComponent(btnCargarDatos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtfNombreRealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfNombreRealActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfNombreRealActionPerformed

    private void txtfNombreHeroicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtfNombreHeroicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtfNombreHeroicoActionPerformed

    private void btnCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDatosActionPerformed
        
    boolean resultado = gestionSuperheroes.cargarSuperheroes();

    if (resultado) {

        actualizarTablaSuperheroes();
        limpiarFormulario();

        JOptionPane.showMessageDialog(
                this,
                "Los superhéroes se cargaron correctamente.",
                "Carga exitosa",
                JOptionPane.INFORMATION_MESSAGE
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No fue posible cargar los archivos.",
                "Error de carga",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnCargarDatosActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        registrarSuperheroe();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        actualizarSuperheroe();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarEstadoActionPerformed
        cambiarEstadoSeleccionado();
    }//GEN-LAST:event_btnCambiarEstadoActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        eliminarSuperheroeSeleccionado();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnGuardarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDatosActionPerformed
        
    boolean resultado = gestionSuperheroes.guardarSuperheroes();

    if (resultado) {

        JOptionPane.showMessageDialog(
                this,
                "Los superhéroes se guardaron correctamente.",
                "Guardado exitoso",
                JOptionPane.INFORMATION_MESSAGE
        );

    } else {

        JOptionPane.showMessageDialog(
                this,
                "No fue posible guardar los superhéroes.",
                "Error de guardado",
                JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnGuardarDatosActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new VentanaSuperheroes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCambiarEstado;
    private javax.swing.JButton btnCargarDatos;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardarDatos;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JFormattedTextField fTxtF_SalarioMensual;
    private javax.swing.JComboBox<String> jCB_Estado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JComboBox<String> jcbEspecificoString;
    private javax.swing.JComboBox<String> jcbTipoSuperheroe;
    private javax.swing.JTable tableSuperheroes;
    private javax.swing.JLabel txtEspecificoInt;
    private javax.swing.JLabel txtEspecificoString;
    private javax.swing.JLabel txtIdHeroe;
    private javax.swing.JLabel txtIdHeroe1;
    private javax.swing.JFormattedTextField txtfEspecificoInt;
    private javax.swing.JTextField txtfNombreHeroico;
    private javax.swing.JTextField txtfNombreReal;
    // End of variables declaration//GEN-END:variables
}
