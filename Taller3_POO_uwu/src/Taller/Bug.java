package Taller;

public class Bug extends Tarea {
	// Aceptar visitor 


	public Bug(Proyecto proyecto, String iD, String descripcion, String estado, Usuario responsable,
			String complejidad, String fecha) {
		super(proyecto, iD, descripcion, estado, responsable, complejidad, fecha);
	}

	@Override
	public void accept(TareaVisitor v) {
		v.visitBug(this);
	}


}
