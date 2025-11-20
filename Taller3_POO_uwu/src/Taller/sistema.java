package Taller;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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

    // Funciones del Admin
    void listarProyectosYTareasOrdenadas();
    void agregarProyecto(Scanner s);
    void eliminarProyecto(Scanner s);
    void agregarTarea(Scanner s);
    void eliminarTarea(Scanner s);
    
    // Utilidad
    int leerEntero(Scanner s);
    void elegirEstrategia(Scanner s);
    void generarReporte();
    
    // Funciones básicas
    void listarProyectosBasico();

    // Funciones del Colaborador
    void verMisTareasOrdenadas(Colaborador c);
    void cambiarEstadoManual(Colaborador c, Scanner s);
}


