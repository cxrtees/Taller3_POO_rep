package Taller;

public class CambiarEstadoVisitor implements TareaVisitor {

	@Override
	public void visitBug(Bug bug) {
		bug.setEstado("En progreso"); //bugs pasan a "En progreso"
	}

	@Override
	public void visitFeature(Feature feature) {
		feature.setEstado("Pendiente revisión"); //features quedan en "Pendiente revisión"
	}

	@Override
	public void visitDocumentacion(Documentacion doc) {
		doc.setEstado("Completada"); // documentación se marca como "Completada"		
	}

}
