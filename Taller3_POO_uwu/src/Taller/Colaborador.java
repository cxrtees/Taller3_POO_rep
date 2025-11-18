package Taller;

import java.util.List;

public class Colaborador extends Usuario {
	
	public void aplicarVisitor(List<Tarea> misTareas, TareaVisitor visitor) {
		for (Tarea t: misTareas) {
			t.accept(visitor);
		}
	}
}
