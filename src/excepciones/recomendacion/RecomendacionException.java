package excepciones.recomendacion;

public class RecomendacionException extends Exception{
    public RecomendacionException(){
        super("Error recomendación.");
    }
    public RecomendacionException(String mensaje){
        super(mensaje);
    }
}
