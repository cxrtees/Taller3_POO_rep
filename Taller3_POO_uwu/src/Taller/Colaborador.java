package Taller;

import java.util.List;

public class Colaborador extends Usuario {
	
	public Colaborador(String username, String contraseña) {
		super(username,contraseña,"colaborador");
	}	
	public void aplicarVisitor(List<Tarea> misTareas, TareaVisitor visitor) {
		for (Tarea t: misTareas) {
			t.accept(visitor);
		}
	}
	public void mostrarMenu(sistema sistema) {
		sistema.MenuColaborador(this);
	}
}
