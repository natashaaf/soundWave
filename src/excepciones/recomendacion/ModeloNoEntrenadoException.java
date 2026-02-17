package excepciones.recomendacion;

public class ModeloNoEntrenadoException extends RecomendacionException{
    public ModeloNoEntrenadoException(){
        super("Modelo no entrenado.");
    }
    public ModeloNoEntrenadoException(String mensaje){
        super(mensaje);
    }
}
