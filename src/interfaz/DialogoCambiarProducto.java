/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Programa de Ingeniería de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Desarrollo de Software - Guía 2 - Actividad 2
 * Ejercicio: tienda
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package interfaz;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Dialogo que se muestra cuando se quiere obtener descuentos por volumen.
 */
@SuppressWarnings("serial")
public class DialogoCambiarProducto extends JDialog implements ActionListener {
    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Representa el comando para seleccionar imagen.
     */
    public final static String SELECCIONAR_IMAGEN = "Seleccionar imagen";

    /**
     * Representa el comando para aceptar.
     */
    public final static String ACEPTAR = "Aceptar";

    /**
     * Representa el comando para cancelar.
     */
    public final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta Nombre actual.
     */
    private JLabel lblNombreActual;

    /**
     * Etiqueta Nombre nuevo.
     */
    private JLabel lblNombreNuevo;

    /**
     * Etiqueta Tipo.
     */
    private JLabel lblTipo;

    /**
     * Etiqueta Valor unitario.
     */
    private JLabel lblValorUnitario;

    /**
     * Etiqueta Cantidad bodega.
     */
    private JLabel lblCantidadBodega;

    /**
     * Etiqueta Cantidad mínima.
     */
    private JLabel lblCantidadMinima;

    /**
     * Etiqueta Imagen.
     */
    private JLabel lblImagen;

    /**
     * Combo box con el tipo.
     */
    private JComboBox cbTipo;

    /**
     * Campo de texto con el nombre actual.
     */
    private JTextField txtNombreActual;

    /**
     * Campo de texto con el nombre nuevo.
     */
    private JTextField txtNombreNuevo;

    /**
     * Campo de texto con el valor unitario.
     */
    private JTextField txtValorUnitario;

    /**
     * Campo de texto con la cantidad en bodega.
     */
    private JTextField txtCantidadBodega;

    /**
     * Campo de texto con la cantidad mínima de unidades para abastecer,
     */
    private JTextField txtCantidadMinima;

    /**
     * Campor de texto con laruta de la imagen.
     */
    private JTextField txtImagen;

    /**
     * Botón para aceptar.
     */
    private JButton btnAceptar;

    /**
     * Botón para cancelar.
     */
    private JButton btnCancelar;

    /**
     * Botón seleccionar imagen
     */
    private JButton btnSeleccionarImagen;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Ventana principal de la aplicación.
     */
    private InterfazTienda principal;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el diálogo para consultar ventas en volumen.
     *
     * @param pPrincipal      Ventana principal de la aplicación. pPrincipal != null.
     * @param pNombreProducto Nombre actual del producto. pNombreProducto != null && pNombreActual != "".
     */
    public DialogoCambiarProducto(InterfazTienda pPrincipal, String pNombreProducto) {
        principal = pPrincipal;

        setTitle("Cambiar producto");
        setSize(430, 280);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setLayout(new GridLayout(8, 2, 5, 5));

        lblNombreActual = new JLabel("Nombre actual:");
        add(lblNombreActual);
        txtNombreActual = new JTextField(pNombreProducto);
        txtNombreActual.setEditable(false);
        add(txtNombreActual);

        lblNombreNuevo = new JLabel("Nombre nuevo:");
        add(lblNombreNuevo);
        txtNombreNuevo = new JTextField();
        add(txtNombreNuevo);

        lblValorUnitario = new JLabel("Valor unitario:");
        add(lblValorUnitario);
        txtValorUnitario = new JTextField();
        add(txtValorUnitario);

        lblTipo = new JLabel("Tipo:");
        add(lblTipo);
        cbTipo = new JComboBox();
        cbTipo.addItem("Papeleria");
        cbTipo.addItem("Supermercado");
        cbTipo.addItem("Drogueria");
        add(cbTipo);

        lblCantidadBodega = new JLabel("Cantidad bodega:");
        add(lblCantidadBodega);
        txtCantidadBodega = new JTextField();
        add(txtCantidadBodega);

        lblCantidadMinima = new JLabel("Cantidad mínima:");
        add(lblCantidadMinima);
        txtCantidadMinima = new JTextField();
        add(txtCantidadMinima);

        lblImagen = new JLabel("Imagen:");
        add(lblImagen);
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new GridLayout(1, 2));
        txtImagen = new JTextField();
        txtImagen.setEditable(false);
        panelImagen.add(txtImagen);
        btnSeleccionarImagen = new JButton("Seleccionar");
        btnSeleccionarImagen.setActionCommand(SELECCIONAR_IMAGEN);
        btnSeleccionarImagen.addActionListener(this);
        panelImagen.add(btnSeleccionarImagen);
        add(panelImagen);

        btnAceptar = new JButton("Aceptar");
        btnAceptar.setActionCommand(ACEPTAR);
        btnAceptar.addActionListener(this);
        add(btnAceptar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setActionCommand(CANCELAR);
        btnCancelar.addActionListener(this);
        add(btnCancelar);

        setModal(true);
        setLocationRelativeTo(pPrincipal);
        setResizable(false);
    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de los eventos de los botones.
     *
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed(ActionEvent pEvento) {
        String comando = pEvento.getActionCommand();
        if (comando.equals(CANCELAR)) {
            dispose();
        } else if (comando.equals(ACEPTAR)) {
            String nombreNuevo = txtNombreNuevo.getText();
            String tipoStr = (String) cbTipo.getSelectedItem();
            String valorStr = txtValorUnitario.getText();
            String cantBodegStr = txtCantidadBodega.getText();
            String cantMinStr = txtCantidadMinima.getText();
            String imagenStr = txtImagen.getText();
            if (nombreNuevo.equals("") || tipoStr.equals("") || valorStr.equals("") || cantBodegStr.equals("") || cantMinStr.equals("") || imagenStr.equals("")) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.", "Cambiar producto", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double valorUnitario = Double.parseDouble(valorStr);
                    int cantidadBodega = Integer.parseInt(cantBodegStr);
                    int cantidadMinima = Integer.parseInt(cantMinStr);

                    if (cantidadBodega < 0 || cantidadMinima < 0 || valorUnitario < 0) {
                        JOptionPane.showMessageDialog(this, "La cantidad en bodega, cantidad mínima y valor unitario no pueden ser valores negativos.", "Cambiar producto", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String tipo = tipoStr.toLowerCase();

                        principal.cambiarProducto(txtNombreActual.getText(), nombreNuevo, tipo, valorUnitario, cantidadBodega, cantidadMinima, imagenStr);
                        dispose();
                    }

                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "El valor unitario, cantidad en bodega, cantidad mínima deben ser valores numéricos.", "Cambiar producto", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (comando.equals(SELECCIONAR_IMAGEN)) {
            JFileChooser jfc = new JFileChooser("./data/imagenes");
            int resultado = jfc.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivo = jfc.getSelectedFile();
                txtImagen.setText(archivo.getName());
            }
        }
    }

}
