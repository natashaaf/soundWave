package excepciones.recomendacion;

public class ModeloNoEntrenadoException extends Exception{
    public ModeloNoEntrenadoException(){
        super("Modelo no entrenado.");
    }
    public ModeloNoEntrenadoException(String mensaje){
        super(mensaje);
    }
}
