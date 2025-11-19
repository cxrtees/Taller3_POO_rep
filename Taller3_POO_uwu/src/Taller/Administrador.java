package Taller;

public class Administrador extends Usuario {

	public Administrador(String username, String contraseña) {
		super(username, contraseña,"Admin");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void mostrarMenu(Sistema sistema) {
		sistema.MenuAdministradror(this);
	
	}

	@Override
	public String toString() {
		return "Administrador [getUsername()=" + getUsername();
	}
	

}
