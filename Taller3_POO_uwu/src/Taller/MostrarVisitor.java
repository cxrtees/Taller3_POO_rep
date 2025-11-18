package Taller;


public class MostrarVisitor implements TareaVisitor {
    @Override
    public void visitBug(Bug bug) {
        System.out.println("BUG: " + bug.getID() + " - " + bug.getDescripcion() + " (estado: " + bug.getEstado() + ")");
    }

    @Override
    public void visitFeature(Feature feature) {
        System.out.println("FEATURE: " + feature.getID() + " - " + feature.getDescripcion() + " (estado: " + feature.getEstado() + ")");
    }

    @Override
    public void visitDocumentacion(Documentacion doc) {
        System.out.println("DOC: " + doc.getID() + " - " + doc.getDescripcion() + " (estado: " + doc.getEstado() + ")");
    }
}


	