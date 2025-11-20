package Taller;

import java.util.ArrayList;

public class PrioridadPorComplejidad implements EstrategiaPrioridad {
    
	private int peso(String c) {
        if (c == null) return 0;
        return switch (c.toLowerCase()) {
            case "alta" -> 3; case "media" -> 2; case "baja" -> 1; default -> 0;
        };
    }
    
	@Override
    public void ordenar(ArrayList<Tarea> tareas) {
        for (int i = 0; i < tareas.size()-1; i++) {
            for (int j = 0; j < tareas.size()-1-i; j++) {
                Tarea a = tareas.get(j), b = tareas.get(j+1);
                if (peso(a.getComplejidad()) < peso(b.getComplejidad())) {
                    tareas.set(j, b); tareas.set(j+1, a);
                }
            }
        }
    }
}

