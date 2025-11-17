package Taller;

import java.util.ArrayList;

public class PrioridadPorFecha implements EstrategiaPrioridad {

	
	public void ordenar(ArrayList<Tarea> tareas) {
		for (int i = 0; i < tareas.size() - 1; i++) {
            for (int j = 0; j < tareas.size() - 1 - i; j++) {

                Tarea t1 = tareas.get(j);
                Tarea t2 = tareas.get(j + 1);

                // si t1 tiene fecha MAYOR que t2, las cambio
                if (t1.getFecha().compareTo(t2.getFecha()) > 0) {
                    tareas.set(j, t2);
                    tareas.set(j + 1, t1);
                }
            }
        }

	}

}
