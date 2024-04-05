/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Programa de Ingenier�a de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Desarrollo de Software - Gu�a 2 - Actividad 2
 * Ejercicio: tienda
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package interfaz;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * Panel con la imagen de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class PanelImagen extends JPanel {

    // ------------------------------------------------------------
    // Atributos de interfaz
    // ------------------------------------------------------------

    /**
     * Etiqueta con la imagen de t�tulo de la tienda de juegos de celular.
     */
    private JLabel lblImagen;

    // ------------------------------------------------------------
    // Constructor
    // ------------------------------------------------------------

    /**
     * Constructor del panel de la imagen de la tienda.
     */
    public PanelImagen() {
        setLayout(new BorderLayout());

        ImageIcon icono = new ImageIcon("./data/imagenes/Encabezado.png");
        lblImagen = new JLabel("");

        lblImagen.setIcon(icono);
        lblImagen.setBorder(new TitledBorder(""));
        add(lblImagen, BorderLayout.CENTER);

    }
}