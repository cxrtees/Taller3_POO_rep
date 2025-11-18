package Taller;

public interface TareaVisitor {
	void visitBug(Bug bug);
    void visitFeature(Feature feature);
    void visitDocumentacion(Documentacion doc);
}
