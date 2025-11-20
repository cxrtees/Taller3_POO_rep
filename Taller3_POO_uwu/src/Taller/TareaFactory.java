package Taller;

public class TareaFactory {
	public static Tarea crearTarea(
            String tipo,
            Proyecto proyecto,
            String id,
            String descripcion,
            String estado,
            Usuario responsable,
            String complejidad,
            String fecha) {

        if (proyecto == null || responsable == null) {
            return null; // no se puede crear tarea si falta info
        }

        switch (tipo.toLowerCase()) {
            case "bug":
                return new Bug(proyecto, id, descripcion, estado, responsable, complejidad, fecha);

            case "feature":
                return new Feature(proyecto, id, descripcion, estado, responsable, complejidad, fecha);

            case "documentacion": 
                return new Documentacion(proyecto, id, descripcion, estado, responsable, complejidad, fecha);

            default:
                System.out.println("Tipo de tarea desconocido: " + tipo);
                return null;
        }
	}
}
