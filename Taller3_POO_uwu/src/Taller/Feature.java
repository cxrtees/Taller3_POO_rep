package Taller;

public class Feature extends Tarea {
	public Feature(Proyecto proyecto, String iD, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		super(proyecto, iD, descripcion, estado, responsable, complejidad, fecha);
	}

	@Override
	public void accept(TareaVisitor v) {
		v.visitFeature(this);
	}
}
//no se añadi el parametro tipo porque ya lo representa la clase
