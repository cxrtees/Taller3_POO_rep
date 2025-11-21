Catalina rojas @Lncata

Benjamin Cortes @

Descripción del Proyecto

TaskForge es una herramienta de gestión de proyectos que permite administrar tareas, responsables y prioridades.
El sistema soporta dos tipos de usuario: Administrador y Colaborador, cada uno con funciones específicas.
El proyecto implementa conceptos de POO como herencia, interfaces y polimorfismo, además de los patrones Singleton, Factory, Strategy y Visitor.

Persistencia en Archivos:

El sistema utiliza los siguientes archivos de entrada:

usuarios.txt:

username|password|rol

proyectos.txt:

ID|Nombre|Responsable

tareas.txt:

IDProyecto|IDTarea|Tipo|Descripcion|Estado|Responsable|Complejidad|Fecha


Además, el sistema genera:

reporte.txt

Archivo creado por el Administrador con un resumen detallado de proyectos y tareas.

Funcionalidades:

Menú Administrador:

-Ver lista completa de proyectos y tareas

-Agregar proyecto

-Eliminar proyecto

-Agregar tarea

-Eliminar tarea

-Asignar prioridad (por fecha, tipo o complejidad)

-Generar reporte en archivo

-Menú Colaborador

-Ver proyectos disponibles

-Ver tareas asignadas

-Cambiar estado de una tarea

-Aplicar Visitor según tipo de tarea

Patrones de Diseño Implementados:

-Singleton

-Factory

-Strategy

-Visitor

Ejecución del Programa:

Abrir el proyecto en Eclipse.

Verificar que los archivos usuarios.txt, proyectos.txt y tareas.txt estén en la raíz del proyecto.

Ejecutar App.java.

Iniciar sesión con un usuario válido.

Reglas Especiales:

Un usuario no puede tener dos tareas asignadas para la misma fecha.

Las tareas se ordenan según la estrategia seleccionada.

El sistema aplica Visitor de manera diferenciada según el tipo de tarea.











