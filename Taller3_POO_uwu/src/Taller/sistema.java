package Taller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public interface sistema {
	//menus
	void MenuAdministrador(Administrador administrador);
	void MenuColaborador(Colaborador colaborador);
	
	 // Búsquedas
    Proyecto buscarProyecto(String id);
    Usuario buscarUsuario(String username);
    ArrayList<Tarea> tareasDe(Colaborador c);
    
    // Carga inicial
    void cargarUsuarios() throws FileNotFoundException;
    void cargarProyectos() throws FileNotFoundException;
    void cargarTareas() throws FileNotFoundException;



}
