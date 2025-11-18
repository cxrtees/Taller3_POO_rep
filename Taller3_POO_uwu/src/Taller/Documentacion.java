package Taller;

public class Documentacion extends Tarea {

	public Documentacion(Taller.Proyecto proyecto, String iD, Tarea tipo, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		super(proyecto, iD, tipo, descripcion, estado, responsable, complejidad, fecha);
	}

	@Override
	public void accept(TareaVisitor v) {
		v.visitDocumentacion(this);
	}

}
