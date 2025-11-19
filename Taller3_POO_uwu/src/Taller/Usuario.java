package Taller;

public abstract class Usuario {
	private String Username;
	private String Contraseña;
	private Usuario Rol;
	
	public Usuario(String username, String contraseña, Usuario rol) {
		super();
		Username = username;
		Contraseña = contraseña;
		Rol = rol;
	}

	public String getUsername() {
		return Username;
	}

	public String getContraseña() {
		return Contraseña;
	}

	public Usuario getRol() {
		return Rol;
	}
	
	public boolean validarPassword(String pass) {// para validar contraseña
	        return this.Contraseña.equals(pass);
	    }

public abstract void mostrarMenu(Sistema sistema); // clase abstracta para que cada uno implemente su menu
	

}
