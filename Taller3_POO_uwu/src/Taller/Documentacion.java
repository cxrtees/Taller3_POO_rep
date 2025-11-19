package Taller;

public class Documentacion extends Tarea {

	public Documentacion(Proyecto proyecto, String iD, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		super(proyecto, iD, descripcion, estado, responsable, complejidad, fecha);
	}

	@Override
	public void accept(TareaVisitor v) {
		v.visitDocumentacion(this);
	}

}
