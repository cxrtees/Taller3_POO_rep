package Taller;

public abstract class Tarea {
	private Proyecto Proyecto;
	private String ID;
	private String Descripcion;
	private String Estado;
	private Usuario Responsable;
	private String Complejidad;
	private String fecha;

	public Tarea(Proyecto proyecto, String iD, String descripcion, String estado,
			Usuario responsable, String complejidad, String fecha) {
		Proyecto = proyecto;
		ID = iD;
		Descripcion = descripcion;
		Estado = estado;
		Responsable = responsable;
		Complejidad = complejidad;
		this.fecha = fecha;
	}
	
	public abstract void accept(TareaVisitor v);
	
	public Proyecto getProyecto() {
		return Proyecto;
	}
	public String getID() {
		return ID;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public String getEstado() {
		return Estado;
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

	public void setEstado(String estado) {
		Estado = estado;
	}
	//asdasdadsa

}
