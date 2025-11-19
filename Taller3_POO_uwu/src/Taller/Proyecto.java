package Taller;

import java.util.ArrayList;

public class Proyecto {
	
	private String ID;
	private String Nombre;
	private Usuario Responsable;
	private ArrayList<Tarea> Tareas;
	
	public Proyecto(String iD, String nombre, Usuario responsable) {
		super();
		this.ID = iD;
		this.Nombre = nombre; 
		this.Responsable = responsable;
		this.Tareas = new ArrayList<>();
	}

	public String getID() {
		return ID;
	}

	public String getNombre() {
		return Nombre;
	}

	public Usuario getResponsable() {
		return Responsable;
	}

	public ArrayList<Tarea> getTareas() {
		return Tareas;
	}
	public void añadirTarea(Tarea t) {
		Tareas.add(t);
	}
	
	
}
