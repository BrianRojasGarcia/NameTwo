package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProcesoInformacionCliente {
   
	private DbConnection conexion;
    
    public ProcesoInformacionCliente(){
        this.conexion = new DbConnection();
    }
    
		public void listarCliente(){
		    String srtConsulta = "SELECT *FROM cliente";
		    int registros = 0;
		    try{
		        PreparedStatement pstm = conexion.getConnection().prepareStatement(srtConsulta);
		        ResultSet res = pstm.executeQuery();
		        
		        String codigo="";
		        String nombre="";
		        String apellido="";
		        String telefono="";
		        String correo="";
		        
		        int i =0;
		        
		        while(res.next()){
		            codigo = res.getString("codigocli");
		            nombre = res.getString("nombrecli");
		            apellido = res.getString("apellidocli");
		            telefono = res.getString("telefonocli");
		            correo = res.getString("correocli");
		            i++;
		            
		            System.out.println(codigo+"\t"+nombre+"\t"+apellido+"\t"+telefono+"\t"+correo);
		        }
		        res.close();
		    }catch(SQLException e){
		        System.out.println(e);
		    }
		    catch(Exception ex){
		        System.out.println(ex.toString());
		    }
		}
		
		public void registrarCliente(Cliente unCliente){
		    
		    String codigo = unCliente.getCodigo();
		    String nombre = unCliente.getNombre();
		    String apellido = unCliente.getApellido();
		    String telefono = unCliente.getTelefono();
		    String correo = unCliente.getCodigo();
		    String registroCliente = "INSERT INTO cliente(codigocli,apellidocli,telefonocli,correocli) VALUES (?,?,?,?,?)";
		    
		    try{
		        PreparedStatement inst =
		                conexion.getConnection().prepareStatement(registroCliente);
		                inst.setString(1, codigo);
		                inst.setString(2, nombre);
		                inst.setString(3, apellido);
		                inst.setString(4, telefono);
		                inst.setString(5, correo);
		                inst.executeUpdate();
		    }
		    catch(SQLException e){
		    System.out.println(e);
		    }
		    catch(Exception ex){
		        System.out.println(ex.toString());
		    }
		}
		
		public void consultarCliente(String codigoCliente){
		             String codigo = codigoCliente;
		             String nombre ="";
		             String apellido ="";
		             String telefono ="";
		             String correo ="";
		             String consultaSql ="SELECT *FROM cliente WHERE codigocli = ?";
		             try{
		                 PreparedStatement inst =
		                 conexion.getConnection().prepareStatement(consultaSql);
		                 inst.setString(1, codigo);
		                 ResultSet rs = inst.executeQuery();
		                 
		                 while(rs.next()==true){
		                     
		                     nombre = rs.getString(2);
		                     apellido = rs.getString(3);
		                     
		                     telefono = rs.getString(4);
		                     correo = rs.getString(5);
		                     
		                     System.out.println(codigo+" "+nombre+" "+apellido+" "+telefono+" "+correo);
		                 }
		             } catch(SQLException e){
		                    System.out.println(e);
		               }
		               catch(Exception ex){
		                    System.out.println(ex.toString());
		                    }
		}
		
		public void actualizarDatosCliente(String codigo, String nombre, String apellido,String telefono, String correo ){
		    String actualizarSql ="UPDATE cliente SET nombrecli=?,apellidocli=?,correocli=? WHERE codigocli=?";
		    
		    try {
		    	PreparedStatement inst =
		    	conexion.getConnection().prepareStatement(actualizarSql);
		    	inst.setString(5, codigo);
		    	inst.setString(1, nombre);
		    	inst.setString(2, apellido);
		    	inst.setString(3, telefono);
		    	inst.setString(4, correo);
		    	inst.executeUpdate();	
		    } catch(SQLException e){
		        System.out.println(e);
			   }
			   catch(Exception ex){
			        System.out.println(ex.toString());
			        }
		}
		
		public void borrarDatosCliente(String codigoCliente) {
			String codigo= codigoCliente;
			String borraSql = "DELETE FROM cliente WHERE codigocli =?";
			try {
				PreparedStatement inst =
				conexion.getConnection().prepareStatement(borraSql);
				inst.setString(1, codigo);
				inst.executeUpdate();
			} catch(SQLException e){
		        System.out.println(e);
			   }
			   catch(Exception ex){
			        System.out.println(ex.toString());
			        }
		}
}