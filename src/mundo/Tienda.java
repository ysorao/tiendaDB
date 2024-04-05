/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot? - Colombia)
 * Programa de Ingenier?a de Sistemas
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Desarrollo de Software - Gu?a 2 - Actividad 2
 * Ejercicio: tienda
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package mundo;

import java.sql.*;

/**
 * Tienda que maneja 4 productos.
 */
public class Tienda {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Producto 1 de la tienda.
     */
    private Producto producto1;

    /**
     * Producto 2 de la tienda.
     */
    private Producto producto2;

    /**
     * Producto 3 de la tienda.
     */
    private Producto producto3;

    /**
     * Producto 4 de la tienda.
     */
    private Producto producto4;

    /**
     * Dinero total recibido por las ventas.
     */
    private double dineroEnCaja;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la tienda con sus 4 productos. <br>
     * <b> post: </b> El dinero en caja fue inicializado en 0.<br>
     * Los 4 productos fueron inicializados con los siguientes valores: <br>
     * Producto 1 - Tipo: PAPELERIA, Nombre: L?piz, Valor unitario: 550.0, Cantidad en bodega: 18, Cantidad m?nima: 5, Imagen: lapiz.png. <br>
     * Producto 2 - Tipo: DROGUERIA, Nombre: Aspirina, Valor unitario: 109.5, Cantidad en bodega: 25, Cantidad m?nima: 8, Imagen: aspirina.png. <br>
     * Producto 3 - Tipo: PAPELERIA, Nombre: Borrador, Valor unitario: 207.3, Cantidad en bodega: 30, Cantidad m?nima: 10, Imagen: borrador.png. <br>
     * Producto 4 - Tipo: SUPERMERCADO, Nombre: Pan, Valor unitario: 150.0, Cantidad en bodega: 15, Cantidad m?nima: 20, Imagen: pan.png. <br>
     */
    public Tienda() {

        try {
        String url = "jdbc:mysql://localhost:3306/tienda?serverTimezone=UTC";
        String username = "root";
        String password = "Sharito1977*";

        Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            String query = "SELECT tipo.nombre, producto.nombre, producto.valor , bodega.cantidad, producto.minimo, producto.imagen FROM tienda.producto INNER JOIN tienda.tipo on producto.tipo = tipo.id INNER JOIN tienda.bodega on producto.id = bodega.idProducto";

            ResultSet resultSet = statement.executeQuery(query);
            int productoContador = 1;


            while (resultSet.next() && productoContador <= 4) {
                Producto producto = new Producto(
                        resultSet.getString(1), // tipo.nombre
                        resultSet.getString(2), // producto.nombre
                        resultSet.getDouble(3), // producto.valor
                        resultSet.getInt(4),    // bodega.cantidad
                        resultSet.getInt(5),    // producto.minimo
                        resultSet.getString(6)  // producto.imagen
                );

                switch (productoContador) {
                    case 1:
                        producto1 = producto;
                        break;
                    case 2:
                        producto2 = producto;
                        break;
                    case 3:
                        producto3 = producto;
                        break;
                    case 4:
                        producto4 = producto;
                        break;
                }
                productoContador++;
            }

            // Cerrar la conexión
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        dineroEnCaja = 0;
    }

    // -----------------------------------------------------------------
    // M?todos
    // -----------------------------------------------------------------

    /**
     * Retorna el producto 1.
     *
     * @return Producto 1 de la tienda.
     */
    public Producto darPrimerProducto() {
        return producto1;
    }

    /**
     * Retorna el producto 2.
     *
     * @return Producto 2 de la tienda.
     */
    public Producto darSegundoProducto() {
        return producto2;
    }

    /**
     * Retorna el producto 3.
     *
     * @return Producto 3 de la tienda.
     */
    public Producto darTercerProducto() {
        return producto3;
    }

    /**
     * Retorna el producto 4.
     *
     * @return Producto 4 de la tienda.
     */
    public Producto darCuartoProducto() {
        return producto4;
    }

    /**
     * Retorna el dinero en caja.
     *
     * @return Dinero en caja.
     */
    public double darDineroEnCaja() {
        return dineroEnCaja;
    }

    /**
     * Retorna el producto con el nombre dado por par?metro.
     *
     * @param pNombre Nombre del producto buscado. pNombre != null && pNombre != "".
     * @return Producto con el nombre dado por par?metro, null si no lo encuentra.
     */
    public Producto darProducto(String pNombre) {
        Producto buscado = null;

        if(pNombre == producto1.darNombre()){
            buscado = producto1;
        } else if(pNombre == producto2.darNombre()){
            buscado = producto2;
        } else if(pNombre == producto3.darNombre()){
            buscado = producto3;
        } else if(pNombre == producto4.darNombre()){
            buscado = producto4;
        }
        return buscado;

    }

    /**
     * Retorna el promedio de las ventas.
     *
     * @return Dinero promedio obtenido por unidad de producto vendida.
     */
    public double darPromedioVentas() {

        int unidvend = producto1.darCantidadUnidadesVendidas() + producto2.darCantidadUnidadesVendidas() +  producto3.darCantidadUnidadesVendidas() + producto4.darCantidadUnidadesVendidas();
        double respuesta = darDineroEnCaja()/unidvend;

        return respuesta;
    }

    /**
     * Retorna el producto con m?s unidades vendidas.
     *
     * @return Producto con m?s unidades vendidas. Null si ning?n producto tiene unidades vendidas.
     */
    public Producto darProductoMasVendido() {
        Producto masVendido = null;

        int maxVent1 = producto1.darCantidadUnidadesVendidas();
        int maxVent2 = producto2.darCantidadUnidadesVendidas();
        int maxVent3 = producto3.darCantidadUnidadesVendidas();
        int maxVent4 = producto4.darCantidadUnidadesVendidas();

        if (maxVent1 > maxVent2 && maxVent1 > maxVent3 && maxVent1 >maxVent4){
            masVendido = producto1;
        } else if (maxVent2 > maxVent3 && maxVent2 > maxVent4 && maxVent2 >maxVent1){
            masVendido = producto2;
        } else if (maxVent3 > maxVent1 && maxVent3 > maxVent2 && maxVent3 >maxVent4){
            masVendido = producto3;

        } else {
            masVendido = producto4;
        }

        return masVendido;
    }

    /**
     * Retorna el producto con menos unidades vendidas.
     *
     * @return Producto con menos unidades vendidas. Null si ning?n producto tiene unidades vendidas.
     */
    public Producto darProductoMenosVendido() {
        Producto menosVendido = null;

        int minVent1 = producto1.darCantidadUnidadesVendidas();
        int minVent2 = producto2.darCantidadUnidadesVendidas();
        int minVent3 = producto3.darCantidadUnidadesVendidas();
        int minVent4 = producto4.darCantidadUnidadesVendidas();

        if (minVent1 < minVent2 && minVent1 < minVent3 && minVent1 < minVent4){
            menosVendido = producto1;
        } else if (minVent2 < minVent3 && minVent2 < minVent4 && minVent2 < minVent1){
            menosVendido = producto2;
        } else if (minVent3 < minVent1 && minVent3 < minVent2 && minVent3 < minVent4){
            menosVendido = producto3;

        } else {
            menosVendido = producto4;
        }

        return menosVendido;

    }

    /**
     * Vende una cantidad de unidades de producto de la tienda, dado su nombre por par?metro. <br>
     * <b>post: </b> Disminuy? la cantidad de unidades del producto dado
     * y se actualiz? el dinero de la caja a partir de la cantidad real vendida multiplicada
     * por el precio final (con IVA) del producto vendido..
     *
     * @param pNombreProducto Nombre del producto a vender.
     * @param pCantidad       Cantidad de unidades del producto a vender. pCantidad > 0.
     * @return Cantidad que fue efectivamente vendida.
     */
    public int venderProducto(String pNombreProducto, int pCantidad) {
        int cantidadVendida = 0;

        Producto prodVenta = darProducto(pNombreProducto); // Verificar que exista el producto en la tienda.
        if (prodVenta != null){
            cantidadVendida = prodVenta.vender(pCantidad);
            cambiarDineroEnCaja(prodVenta.calcularPrecioFinal() * cantidadVendida);
        }

        return cantidadVendida;
    }

    /**
     * Abastece un producto dado su nombre, con la cantidad de unidades dadas. <br>
     * <b>post: </b> Aument? la cantidad de unidades en bodega del producto dado.
     *
     * @param pNombreProducto Nombre del producto a abastecer.
     * @param pCantidad       La cantidad de unidades a abastecer. cantidad >= 0.
     * @return Retorna true si se pudo efectuar el abastecimiento, false en caso contrario.
     */
    public boolean abastecerProducto(String pNombreProducto, int pCantidad) {
        boolean abastece = false;
        Producto prod = darProducto(pNombreProducto);
        if (prod.puedeAbastecer()){
            prod.abastecer(pCantidad);
            abastece=true;
        }

        return abastece;
    }

    /**
     * Cambia el producto que tiene el nombre actual con los nuevos valores dados por par?metro. <br>
     * <b>post: </b> El nombre, tipo, valor unitario, cantidad en bodega y cantidad m?nima fueron cambiados con los valores dados por par?metro.
     *
     * @param pNombreActual   Nombre actual del producto.
     * @param pNombreNuevo    Nuevo nombre del producto.
     * @param pTipo           Tipo del producto.
     * @param pValorUnitario  Valor unitario del producto
     * @param pCantidadBodega Cantidad en bodega del producto.
     * @param pCantidadMinima Cantidad m?nima en bodega para hacer un pedido del producto.
     * @param pRutaImagen     Ruta de la imagen del producto.
     * @return Retorna true si cambi? la informaci?n del producto,
     * Retorna false si ya exist?a un producto con el nuevo nombre.
     */
    public boolean cambiarProducto(String pNombreActual, String pNombreNuevo, String pTipo, double pValorUnitario, int pCantidadBodega, int pCantidadMinima, String pRutaImagen) {
        boolean cambio = false;
        Producto prodExist = darProducto(pNombreActual);
        Producto prodNewExist = darProducto(pNombreNuevo);
        if (prodExist != null && prodNewExist == null){
            prodExist.cambiarNombre(pNombreNuevo);
            prodExist.cambiarTipo(pTipo);
            prodExist.cambiarValorUnitario(pValorUnitario);
            prodExist.cambiarCantidadBodega(pCantidadBodega);
            prodExist.cambiarCantidadMinima(pCantidadMinima);
            prodExist.cambiarRutaImagen(pRutaImagen);

            cambio = true;
        }

        return cambio;
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi?n
    // -----------------------------------------------------------------

    /**
     * Obtiene la cantidad de productos que tienen un precio inferior al promedio de los precios
     * La solucion se realiza basada en el valor unitario antes de impuestos
     */
    public int metodo1() {
        double preProd1 = darPrimerProducto().darValorUnitario();
        double preProd2 = darSegundoProducto().darValorUnitario();
        double preProd3 = darTercerProducto().darValorUnitario();
        double preProd4 = darCuartoProducto().darValorUnitario();

        double sumaPrecioUnidades= preProd1 + preProd2 + preProd3 + preProd4;
        double promedioPrecio = sumaPrecioUnidades/4;

        int contador = 0;

        if(preProd1 < promedioPrecio) contador += 1;
        if(preProd2 < promedioPrecio) contador += 1;
        if(preProd3 < promedioPrecio) contador += 1;
        if(preProd4 < promedioPrecio) contador += 1;


        return contador;
    }

    /**
     * Obtiene el nombre del producto m?s barato de la tienda
     */
    public String metodo2() {

        double preProd1 = darPrimerProducto().darValorUnitario();
        double preProd2 = darSegundoProducto().darValorUnitario();
        double preProd3 = darTercerProducto().darValorUnitario();
        double preProd4 = darCuartoProducto().darValorUnitario();


        if(preProd1 < preProd2 && preProd1 < preProd3 && preProd1 < preProd4){
            return  darPrimerProducto().darNombre();
        } else if  (preProd2 < preProd1 && preProd2 < preProd3 && preProd2<preProd4){
            return  darSegundoProducto().darNombre();
        } else if  (preProd3 < preProd1 && preProd3 < preProd2 && preProd3<preProd4){
            return  darTercerProducto().darNombre();
        } else {
            return  darCuartoProducto().darNombre();
        }

    }

    public void cambiarDineroEnCaja(double dineroIngresa) {
        this.dineroEnCaja = dineroEnCaja + dineroIngresa;
    }
}