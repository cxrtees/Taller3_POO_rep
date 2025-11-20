package Taller;

public class Administrador extends Usuario {

	public Administrador(String username, String contraseña) {
		super(username, contraseña,"Admin");
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "Administrador [getUsername()=" + getUsername();
	}

	@Override
	public void mostrarMenu(sistema sistema) {
		sistema.MenuAdministrador(this);
	}
	

}
