package excepciones.playlist;

public class CancionNoEncontradaException extends Exception{
    public CancionNoEncontradaException(){
        super("Canción no encontrada.");
    }
    public CancionNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
