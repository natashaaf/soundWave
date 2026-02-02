package excepciones.contenido;

public class DuracionInvalidaException extends Exception{
    public DuracionInvalidaException(){
        super("Duración invalida.");
    }
    public DuracionInvalidaException(String mensaje){
        super(mensaje);
    }
}
