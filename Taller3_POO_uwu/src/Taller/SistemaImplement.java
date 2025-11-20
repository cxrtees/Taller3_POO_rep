package Taller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaImplement implements sistema {

	private ArrayList<Usuario> usuarios = new ArrayList<>();
	private ArrayList<Proyecto> proyectos = new ArrayList<>();
	private EstrategiaPrioridad estrategia = new PrioridadPorFecha();

	public void cargarUsuarios() throws FileNotFoundException {
		Scanner s = new Scanner(new File("usuarios.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().trim().split("\\|");
			if (p.length < 3)
				continue;
			String user = p[0], pass = p[1], rol = p[2];
			Usuario u = "Administrador".equalsIgnoreCase(rol) ? new Administrador(user, pass)
					: new Colaborador(user, pass);
			usuarios.add(u);
		}
		s.close();
		// System.out.println("Usuarios cargados: "+ usuarios.size());
	}

	private void cargarProyectos() throws FileNotFoundException {
		Scanner s = new Scanner(new File("proyectos.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().trim().split("\\|");
			if (p.length < 3)
				continue;
			String id = p[0], nombre = p[1], usernameResp = p[2];
			Usuario responsable = buscarUsuario(usernameResp);
			Proyecto pr = new Proyecto(id, nombre, responsable);
			proyectos.add(pr);
		}
		s.close();
		System.out.println("proyectos cargados: " + proyectos.size());
	}

	public void cargarTareas() throws FileNotFoundException {
		Scanner s = new Scanner(new File("tareas.txt"));
		while (s.hasNextLine()) {
			String[] p = s.nextLine().trim().split("\\|");
			if (p.length < 8)
				continue;

			String idProyecto = p[0];
			String id = p[1];
			String tipo = p[2];
			String desc = p[3];
			String estado = p[4];
			String usernameResp = p[5];
			String compl = p[6];
			String fecha = p[7];

			Proyecto pr = buscarProyecto(idProyecto);
			Usuario resp = buscarUsuario(usernameResp);
			if (pr == null || resp == null)
				continue;

			Tarea t = switch (tipo.toLowerCase()) {
			case "bug" -> new Bug(pr, id, desc, estado, resp, compl, fecha);
			case "feature" -> new Feature(pr, id, desc, estado, resp, compl, fecha);
			case "documentacion" -> new Documentacion(pr, id, desc, estado, resp, compl, fecha);
			default -> null;
			};

			s.close();
}
	}
	//Busquedas
	private Proyecto buscarProyecto(String id) {
		for (Proyecto pr : proyectos )
			if (pr.getID().equals(id))
				return pr;
		return null;
	}

	private Usuario buscarUsuario(String username) {
		for (Usuario u : usuarios)
			if (u.getUsername().equals(username))
				return u;
		return null;
	}
	
	private ArrayList<Tarea> tareasDe(Colaborador c) {
		ArrayList<Tarea> r = new ArrayList<>();
		for (Proyecto p: proyectos)
			for (Tarea t: p.getTareas())
				if (t.getResponsable()== c) r.add(t);
		return r;
	}
	
	
	//Menus 
	@Override
	public void MenuAdministradror(Administrador administrador) {
		Scanner s = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n--- MENÚ ADMIN ---");
			System.out.println("1) Ver proyectos y tareas");
			System.out.println("2) Agregar proyecto");
			System.out.println("3) Eliminar proyecto");
			System.out.println("4) Agregar tarea");
			System.out.println("5) Eliminar tarea");
			System.out.println("6) Asignar prioridad");
			System.out.println("7) Generar reporte.txt");
			System.out.println("0) Salir");
			System.out.print("Opcion: ");
			opcion = leerEntero(s);
			
			switch (opcion) {
			case 1 -> listarProyectosYTareasOrdenadas();
			case 2 -> agregarProyecto(s);
			case 3 -> eliminarProyecto(s);
			case 4 -> agregarTarea(s);
			case 5 -> eliminarTarea(s);
			case 6 -> elegirEsttrategia(s);
			case 7 -> generarReporte();
  			}
		}while (opcion != 0);
	}

	

	@Override
	public void MenuColaborador(Colaborador c) {
		Scanner s = new Scanner(System.in);
		int opcion;
		do {
			System.out.println("\n--- MENÚ COLABORADOR ---");
			System.out.println("1) Ver proyectos disponibles");
			System.out.println("2) Ver mis tareas");
			System.out.println("3) Cambiar estado de una tarea mía");
			System.out.println("4) Aplicar Visitor sobre mis tareas");	
			System.out.println("0) Salir");
			System.out.print("Opcion: ");
			opcion = leerEntero(s);
			
			switch (opcion) {
			case 1 -> listarProyectoBasico();
			case 2 -> verMisTareasOrdenadas(c);
			case 3 -> CambiarEstadoManual(c, s);
			case 4 -> {
				System.out.println("Aplicando CambiarEstadoVisitor...");
				c.aplicarVisitor(tareasDe(c), new CambiarEstadoVisitor());
				System.out.println("Listo.");
			}
			
  			}
		}while (opcion != 0);
	}
	
	
	private int leerEntero(Scanner s) {
		try {return Integer.parseInt(s.nextLine().trim());}
		catch (Exception e) {return -1;}
	}
}
