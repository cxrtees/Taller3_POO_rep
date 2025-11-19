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

	@Override
	public void MenuAdministradror(Administrador administrador) {
		// TODO Auto-generated method stub

	}

	@Override
	public void MenuColaborador(Colaborador colaborador) {
		// TODO Auto-generated method stub

	}

}
