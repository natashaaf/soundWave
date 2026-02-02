package excepciones.contenido;

public class TranscripcionNoDisponibleException extends Exception{
    public TranscripcionNoDisponibleException(){
        super("Transcripcion no disponible.");
    }
    public TranscripcionNoDisponibleException(String mensaje){
        super(mensaje);
    }
}
