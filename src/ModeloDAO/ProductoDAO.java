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
    
    
    public String Listar_JSON(int key, String busq) {
    	
    	String retorno = new String("[");
    	
    	this.query = "SELECT idProductos, productos.Nombre, Cantidad, Precio_Venta, Precio_Compra, Caducidad, Descripcion, " + 
    			"categorias.Nombre as Categoria, " + 
    			"proveedores.Proveedor_Nombre as Proveedor " + 
    			"FROM veterinaria.productos " + 
    			"INNER JOIN Categorias ON  productos.R_Categoria=Categorias.idCategorias " + 
    			"INNER JOIN proveedores ON productos.R_Proveedor=proveedores.idProveedores ";
    	
    	switch (key) {
			case 1:
					this.query+="WHERE idProductos like ? ORDER BY idProductos;";
				break;
			case 2:
				this.query+="WHERE productos.Nombre like ? ORDER BY productos.Nombre;";
				break;	
			case 3:
				this.query+="WHERE Precio_Venta like ? ORDER BY Precio_Venta;";
				break;
			case 4:
				this.query+="WHERE Precio_Compra like ? ORDER BY Precio_Compra;";
				break;
			case 5:
				this.query+="WHERE Caducidad like ? ORDER BY Caducidad;";
				break;
			case 6:
				this.query+="WHERE Descripcion like ? ORDER BY Descripcion;";
				break;
			case 7:
				this.query+="WHERE categorias.Nombre like ? ORDER BY Categoria;";
				break;
			case 8:
				this.query+="WHERE proveedores.Proveedor_Nombre like ? ORDER BY Proveedor;";
				break;
			case 9:
				this.query+="WHERE Cantidad like ? ORDER BY Cantidad;";
				break;
			default:
				this.query+="';'";
				break;
		}
    	
    	try {
            ps = getConnection().prepareStatement(query);
            ps.setString(1,"%"+busq+"%");
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
            	
            	retorno+= new_product.crear_JSON();
            	if(!this.rs.isLast())
            		retorno+= ",";
            }
    	
	    } catch (Exception var4) {
	        var4.printStackTrace();
	    }
    	retorno+="]";
    	return retorno;
    }
    
    public List<Producto> listar(){
    	List<Producto> datos = new ArrayList<Producto>();
    	
    	this.query = "SELECT idProductos, productos.Nombre, Cantidad, Precio_Venta, Precio_Compra, Caducidad, Descripcion, " + 
    			"categorias.Nombre as Categoria, " + 
    			"proveedores.Proveedor_Nombre as Proveedor, R_Proveedor, R_Categoria" + 
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

    public boolean add(Producto nuevo_pro, boolean opcion) {

        try {
        	
        	if(opcion) {
        		this.query = "INSERT INTO  productos (idProductos, Nombre, Cantidad, Precio_Venta, "
            			+ "Precio_Compra, Caducidad, Descripcion, R_Categoria, R_Proveedor)"+ 
            				"values (?,?,?,?,?,? ,?,?,?)";
        	}else {
        		this.query = "INSERT INTO  productos (idProductos, Nombre, Cantidad, Precio_Venta, "
            			+ "Precio_Compra, Descripcion, R_Categoria, R_Proveedor)"+ 
            				"values (?,?,?,?,?,? ,?,?)";
        	}
        	
        	ps = getConnection().prepareStatement(query);
            
            ps.setString(1, nuevo_pro.getIDProducto());
            ps.setString(2,nuevo_pro.getNombre());
            ps.setInt(3, nuevo_pro.getCantidad());
            ps.setFloat(4,nuevo_pro.getPrecio_V());
            ps.setFloat(5,nuevo_pro.getPrecio_C());
            if(opcion) {
            	SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
            	ps.setString(6,String.format(objSDF.format((nuevo_pro.getCaducidad()))));
                ps.setString(7, nuevo_pro.getDescripcion());
                ps.setInt(8, nuevo_pro.getR_Categoria());
                ps.setInt(9, nuevo_pro.getR_Proveedor());
            }else {
            	ps.setString(6, nuevo_pro.getDescripcion());
                ps.setInt(7, nuevo_pro.getR_Categoria());
                ps.setInt(8, nuevo_pro.getR_Proveedor());
            }
            
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
    
    public boolean edit(Producto product, boolean opcion) {
        
        try {
        	
        	if(opcion)
        		this.query = "UPDATE  productos SET Nombre =?, Cantidad = ?, Precio_Venta = ?, "
            			+ "Precio_Compra = ? , Caducidad = ?, Descripcion = ?, R_Categoria = ?, R_Proveedor = ? "
            			+ "WHERE idProductos = ?;";
        	else
        		this.query = "UPDATE  productos SET Nombre =?, Cantidad = ?, Precio_Venta = ?, "
            			+ "Precio_Compra = ? , Caducidad = NULL, Descripcion = ?, R_Categoria = ?, R_Proveedor = ? "
            			+ "WHERE idProductos = ?;";
        		
        	ps = getConnection().prepareStatement(query);
            
            ps.setString(1,product.getNombre());
            ps.setInt(2, product.getCantidad());
            ps.setFloat(3,product.getPrecio_V());
            ps.setFloat(4,product.getPrecio_C());
            if(opcion) {
            	SimpleDateFormat objSDF = new SimpleDateFormat("yyyy-mm-dd"); 
                
                ps.setString(5,String.format(objSDF.format((product.getCaducidad()))));
                ps.setString(6, product.getDescripcion());
                ps.setInt(7, product.getR_Categoria());
                ps.setInt(8, product.getR_Proveedor());
                ps.setString(9, product.getIDProducto());
            }else {
            	ps.setString(5, product.getDescripcion());
                ps.setInt(6, product.getR_Categoria());
                ps.setInt(7, product.getR_Proveedor());
                ps.setString(8, product.getIDProducto());
            }
            
            this.ps.executeUpdate();
        } catch (Exception var4) {
            var4.printStackTrace();
            return false;
        }

        return true;
    }

}

