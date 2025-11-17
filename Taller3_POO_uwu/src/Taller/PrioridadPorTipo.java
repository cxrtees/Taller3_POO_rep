package Taller;

import java.util.ArrayList;

public class PrioridadPorTipo implements EstrategiaPrioridad {

	@Override
	public void ordenar(ArrayList<Tarea> tareas) {
		 for (int i = 0; i < tareas.size() - 1; i++) {
	            for (int j = 0; j < tareas.size() - 1 - i; j++) {

	                Tarea t1 = tareas.get(j);
	                Tarea t2 = tareas.get(j + 1);

	                int p1 = pesoTipo(t1);
	                int p2 = pesoTipo(t2);

	                //MAYOR peso queda antes
	                if (p1 < p2) {
	                    tareas.set(j, t2);
	                    tareas.set(j + 1, t1);
	                }
	            }
		 }
	}
	 private int pesoTipo(Tarea t) {
	        if (t instanceof Bug) {
	            return 3;
	        } else if (t instanceof Feature) {
	            return 2;
	        } else if (t instanceof Documentacion) {
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	}


