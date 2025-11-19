package Taller;

public class Feature extends Tarea {
<<<<<<< Updated upstream

	public Feature(Taller.Proyecto proyecto, String iD, Tarea tipo, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		super(proyecto, iD, tipo, descripcion, estado, responsable, complejidad, fecha);
	}

	@Override
	public void accept(TareaVisitor v) {
		v.visitFeature(this);
	}

=======
	//aceptar visitor
>>>>>>> Stashed changes
}
