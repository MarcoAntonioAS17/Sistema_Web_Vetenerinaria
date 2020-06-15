package Modelo;

public class Clientes {
	
	private int IDClient;
	private String NombreC;
	private String TelefonoC;
	private String EmailC;
	
	public String getNombreC() {
		return NombreC;
	}
	
	public void setNombreC(String NombreCte) {
		NombreC = NombreCte;
	}
	
	public int getIDClient() {
		return IDClient;
	}
	
	public int setIDClient(int idClient) {
		IDClient = idClient;
	}
	
	public int getTelefonoC() {
		return TelefonoC;
	}
	
	public int setTelefonoC(int TelefonoCte) {
		TelefonoC = TelefonoCte;
	}
	
	public String getEmailC() {
		return EmailC;
	}
	
	public void setEmailC(String emailCte) {
		EmailC = emailCte;
	}
	
	public String crear_JSON() {
		String lista = new String("{ \"IDClient\": \""+String.format("%d", this.IDClient)
				+"\", \"NombreC\":\""+this.NombreC
				+"\", \"TelefonoC\":\""+this.TelefonoC
				+"\", \"EmailC\":\""+this.EmailC
				+"\"}");
		
		return lista;
	}
}
