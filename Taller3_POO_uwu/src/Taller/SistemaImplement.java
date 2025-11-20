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

	public void cargarProyectos() throws FileNotFoundException {
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
	public Proyecto buscarProyecto(String id) {
		for (Proyecto pr : proyectos )
			if (pr.getID().equals(id))
				return pr;
		return null;
	}

	public Usuario buscarUsuario(String username) {
		for (Usuario u : usuarios)
			if (u.getUsername().equals(username))
				return u;
		return null;
	}
	
	public ArrayList<Tarea> tareasDe(Colaborador c) {
		ArrayList<Tarea> r = new ArrayList<>();
		for (Proyecto p: proyectos)
			for (Tarea t: p.getTareas())
				if (t.getResponsable()== c) r.add(t);
		return r;
	}
	
	
	//Menus 
	@Override
	public void MenuAdministrador(Administrador administrador) {
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
			case 6 -> elegirEstrategia(s);
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
	
	public void listarProyectosYTareasOrdenadas() {
		for (Proyecto p: proyectos) {
			ArrayList<Tarea> ts = p.getTareas();
			estrategia.ordenar(ts);
			System.out.println(p.getID() + " - "+ p.getNombre()+ " (tareas: "+ ts.size()+ ")");
			for (Tarea t: ts) 
				System.out.println("  * "+ t.getID()+ "["+ t.getFecha()+"] "+t.getClass().getSimpleName()+ " | "+t.getDescripcion()+ " | "
						+t.getEstado()+ " | "+ t.getResponsable().getUsername()+ " | "+ t.getComplejidad());
		}
	}
	
	public void agregarProyecto(Scanner s){
		System.out.print("ID: "); String id = s.nextLine();
		System.out.print("Nombre: "); String nom = s.nextLine();
		System.out.print("Responsable (username): "); String u = s.nextLine();
		Usuario resp = buscarUsuario(u);
		Proyecto p = new Proyecto(id, nom, resp);
		proyectos.add(p);
		System.out.println("Proyecto agregado.");		
	}
	
	public void eliminarProyecto(Scanner s) {
		System.out.println("ID de proyecto: "); Proyecto p = buscarProyecto(s.nextLine());
		if (p != null) {proyectos.remove(p); 
		System.out.println("Proyecto eliminado.");}
	}
	
	public void agregarTarea(Scanner s) {
		System.out.println("ID proyecto: "); Proyecto p = buscarProyecto(s.nextLine());
		if (p == null) { System.out.println("Proyecto no existe."); return;	}
		System.out.print("Tipo (bug/feature/documentacion): "); String tipo = s.nextLine();
		System.out.print("ID tarea: "); String id = s.nextLine();
		System.out.print("Descripción: "); String desc = s.nextLine();
		System.out.print("Estado inicial:" ); String est = s.nextLine();
		System.out.print("Responsable (username): "); Usuario resp = buscarUsuario(s.nextLine());
		System.out.print("Complejidad (Baja/Media/Alta): "); String comp = s.nextLine();
		System.out.print("Fecha (DD-MM-YYYY): "); String fecha = s.nextLine();
		
		for (Tarea t: p.getTareas()) {
			if (t.getResponsable() == resp && fecha.equals(t.getFecha())) {
				System.out.println("Conflicto: responsable ya tiene tarea ese día.");
				return;
			}
		}
		Tarea t = switch (tipo.toLowerCase()) {
		case "bug" -> new Bug(p, id, desc, est, resp, comp, fecha);
		case "feature" -> new Feature(p, id, desc, est, resp, comp, fecha);
		case "documentacion" -> new Documentacion(p, id, desc, est, resp, comp, fecha);
		default -> null;
		};
		if (t != null) {p.añadirTarea(t); System.out.println("Tarea agregada");}
	}
	
	public void eliminarTarea(Scanner s) {
		System.out.print("ID de proyecto: "); Proyecto p = buscarProyecto(s.nextLine());
		if (p == null) {System.out.println( "El proyecto no existe"); return;}
		System.out.print("ID tarea: "); String id = s.nextLine();
		p.getTareas().removeIf(t-> t.getID().equals(id));
		System.out.println("Tarea eliminada");
	}
	
	public int leerEntero(Scanner s) {
		try {return Integer.parseInt(s.nextLine().trim());}
		catch (Exception e) {return -1;}
	}
	 public void elegirEstrategia(Scanner s){
	        System.out.println("Estrategias: 1) Fecha  2) Tipo  3) Complejidad");
	        System.out.print("Elige: ");
	        int e = leerEntero(s);
	        estrategia = switch (e) {
	            case 2 -> new PrioridadPorTipo();
	            case 3 -> new PrioridadPorComplejidad();
	            default -> new PrioridadPorFecha();
	        };
	        System.out.println("Estrategia aplicada.");
	    }

	    public void generarReporte() {
	        try (java.io.PrintWriter out = new java.io.PrintWriter("reporte.txt")) {
	            for (Proyecto p : proyectos) {
	                out.println("Proyecto " + p.getID() + " - " + p.getNombre());
	                ArrayList<Tarea> ts = p.getTareas();
	                estrategia.ordenar(ts);
	                for (Tarea t : ts) {
	                    out.println(t.getID()+"|"+t.getFecha()+"|"+t.getClass().getSimpleName()
	                            +"|"+t.getDescripcion()+"|"+t.getEstado()+"|"
	                            +t.getResponsable().getUsername()+"|"+t.getComplejidad());
	                }
	                out.println();
	            }
	        } catch (java.io.FileNotFoundException e) {
	            throw new RuntimeException(e); // si prefieres, propágalo
	        }
	        System.out.println("reporte.txt generado.");
	    }

	    public void listarProyectosBasico(){
	        for (Proyecto p : proyectos) {
	            String resp = (p.getResponsable()!=null ? p.getResponsable().getUsername() : "-");
	            System.out.println(p.getID()+" - "+p.getNombre()+" (Resp: "+resp+")");
	        }
	    }

	    public void verMisTareasOrdenadas(Colaborador c){
	        ArrayList<Tarea> mias = tareasDe(c);
	        estrategia.ordenar(mias);
	        for (Tarea t : mias)
	            System.out.println(" - "+t.getID()+" ["+t.getFecha()+"] "
	                    +t.getDescripcion()+" ("+t.getEstado()+")");
	    }

	    public void cambiarEstadoManual(Colaborador c, Scanner s){
	        ArrayList<Tarea> mias = tareasDe(c);
	        for (Tarea t : mias)
	            System.out.println(t.getID()+" - "+t.getDescripcion()+" ("+t.getEstado()+")");
	        System.out.print("ID de tarea: "); String id = s.nextLine();
	        System.out.print("Nuevo estado (Pendiente/En progreso/Completada): "); String ne = s.nextLine();
	        for (Tarea t : mias) if (t.getID().equals(id)) { t.setEstado(ne); System.out.println("OK."); return; }
	        System.out.println("No encontrada.");
	    }
}
