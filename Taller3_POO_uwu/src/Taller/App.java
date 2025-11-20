package Taller;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        // 1. Crear sistema (si hiciste Singleton, cambia a getInstance())
        sistema sistema = new SistemaImplement();

        // 2. Cargar archivos
        try {
            sistema.cargarUsuarios();
            sistema.cargarProyectos();
            sistema.cargarTareas();
        } catch (FileNotFoundException e) {
            System.out.println("Error al cargar archivos: " + e.getMessage());
            return; // no seguimos si no hay datos
        }

        Scanner s = new Scanner(System.in);

        // 3. LOGIN
        Usuario u = null;
        do {
            System.out.println("---- LOGIN ----");
            System.out.print("Username: ");
            String user = s.nextLine();
            System.out.print("Contraseña: ");
            String pass = s.nextLine();

            u = sistema.buscarUsuario(user);

            if (u == null) {
                System.out.println("Usuario no encontrado.\n");
                continue;
            }

            if (!u.validarPassword(pass)) {
                System.out.println("Contraseña incorrecta.\n");
                u = null; // forzar repetir login
            }

        } while (u == null);

        // 4. Enviar al menú que corresponda (polimorfismo)
        System.out.println("\nBienvenido " + u.getUsername() + " (" + u.getRol() + ")");
        u.mostrarMenu(sistema);

        // 5. Cerrar scanner
        s.close();

        System.out.println("Programa finalizado.");
    }
}

