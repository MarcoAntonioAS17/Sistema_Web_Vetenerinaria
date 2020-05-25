package ModeloDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Config.Conexion;
import Modelo.Producto;

public class ProductoDAO extends Conexion{
	
    PreparedStatement ps;
    ResultSet rs;
    Producto Product = new Producto();
    String query;
    
    public ProductoDAO() {
    	
    }
    
    public List<Producto> listar(){
    	List<Producto> datos = new ArrayList<Producto>();
    	
    	this.query = "SELECT idProductos, productos.Nombre, Cantidad, Precio_Venta, Precio_Compra, Caducidad, Descripcion, " + 
    			"categorias.Nombre as Categoria, " + 
    			"proveedores.Proveedor_Nombre as Proveedor " + 
    			"FROM veterinaria.productos " + 
    			"INNER JOIN Categorias ON  productos.R_Categoria=Categorias.idCategorias " + 
    			"INNER JOIN proveedores ON productos.R_Proveedor=proveedores.idProveedores;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	Producto new_product = new Producto();
            	new_product.setIDProducto(this.rs.getString("idProductos"));
            	new_product.setNombre(this.rs.getString("Nombre"));
            	new_product.setCantidad(this.rs.getInt("Cantidad"));
            	new_product.setPrecio_V(this.rs.getFloat("Precio_Venta"));
            	new_product.setPrecio_C(this.rs.getFloat("Precio_Compra"));
            	new_product.setCaducidad(this.rs.getDate("Caducidad"));
            	new_product.setDescripcion(this.rs.getString("Descripcion"));
            	new_product.setS_Categoria(this.rs.getString("Categoria"));
            	new_product.setS_Proveedor(this.rs.getString("Proveedor"));
            	
            	datos.add(new_product);
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	return datos;
    }

    public boolean add(Producto nuevo_pro) {

        try {
        	this.query = "INSERT INTO  productos (idProductos, Nombre, Cantidad, Precio_Venta, "
        			+ "Precio_Compra, Caducidad, Descripcion, R_Categoria, R_Proveedor)"+ 
        				"values (?,?,?,?,?,? ,?,?,?)";
        	ps = getConnection().prepareStatement(query);
            
            ps.setString(1, nuevo_pro.getIDProducto());
            ps.setString(2,nuevo_pro.getNombre());
            ps.setInt(3, nuevo_pro.getCantidad());
            ps.setFloat(4,nuevo_pro.getPrecio_V());
            ps.setFloat(5,nuevo_pro.getPrecio_C());
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            
            ps.setString(6,String.format(objSDF.format((nuevo_pro.getCaducidad()))));
            ps.setString(7, nuevo_pro.getDescripcion());
            ps.setInt(8, nuevo_pro.getR_Categoria());
            ps.setInt(9, nuevo_pro.getR_Proveedor());
            
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean delete(String ID) {
       
        try {
        	this.query = "DELETE FROM productos WHERE idProductos = ?;";
        	ps = getConnection().prepareStatement(query);
            ps.setString(1, ID);
            this.ps.executeUpdate();
            
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }
    
    public Producto select_one(String IDProducto){
    	Producto new_product = new Producto();
    	this.query = "SELECT idProductos, productos.Nombre, Cantidad, Precio_Venta, Precio_Compra, Caducidad, Descripcion, " + 
    			"R_Categoria as Categoria, R_Proveedor as Proveedor " + 
    			"FROM veterinaria.productos WHERE idProductos=?;";
    	
    	try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1,IDProducto);
            this.rs = this.ps.executeQuery();
            
            while(this.rs.next()) {
            	
            	new_product.setIDProducto(this.rs.getString("idProductos"));
            	new_product.setNombre(this.rs.getString("Nombre"));
            	new_product.setCantidad(this.rs.getInt("Cantidad"));
            	new_product.setPrecio_V(this.rs.getFloat("Precio_Venta"));
            	new_product.setPrecio_C(this.rs.getFloat("Precio_Compra"));
            	new_product.setCaducidad(this.rs.getDate("Caducidad"));
            	new_product.setDescripcion(this.rs.getString("Descripcion"));
            	new_product.setR_Categoria(this.rs.getInt("Categoria"));
            	new_product.setR_Proveedor(this.rs.getInt("Proveedor"));
            	
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	return new_product;
    }
    
public boolean edit(Producto product) {
        
        try {
        	
        	this.query = "UPDATE  productos SET Nombre =?, Cantidad = ?, Precio_Venta = ?, "
        			+ "Precio_Compra = ? , Caducidad = ?, Descripcion = ?, R_Categoria = ?, R_Proveedor = ? "
        			+ "WHERE idProductos = ?;";
        	ps = getConnection().prepareStatement(query);
            
        	ps.setString(9, product.getIDProducto());
            ps.setString(1,product.getNombre());
            ps.setInt(2, product.getCantidad());
            ps.setFloat(3,product.getPrecio_V());
            ps.setFloat(4,product.getPrecio_C());
            SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            
            ps.setString(5,String.format(objSDF.format((product.getCaducidad()))));
            ps.setString(6, product.getDescripcion());
            ps.setInt(7, product.getR_Categoria());
            ps.setInt(8, product.getR_Proveedor());
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }
}

