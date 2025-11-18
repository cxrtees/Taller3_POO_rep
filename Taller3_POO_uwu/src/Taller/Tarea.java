package Taller;

public abstract class Tarea {
	private Proyecto Proyecto;
	private String ID;
	private Tarea tipo;
	private String Descripcion;
	private String Estado;
	private Usuario Responsable;
	private String Complejidad;
	private String fecha;

	public Tarea(Taller.Proyecto proyecto, String iD, Tarea tipo, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		Proyecto = proyecto;
		ID = iD;
		this.tipo = tipo;
		Descripcion = descripcion;
		Estado = estado;
		Responsable = responsable;
		Complejidad = complejidad;
		this.fecha = fecha;
	}
	
	public abstract void accept(TareaVisitor v);
	
	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public Proyecto getProyecto() {
		return Proyecto;
	}

	public String getID() {
		return ID;
	}

	public Tarea getTipo() {
		return tipo;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public Usuario getResponsable() {
		return Responsable;
	}

	public String getComplejidad() {
		return Complejidad;
	}

	public String getFecha() {
		return fecha;
	}
	
	
	
	
}
