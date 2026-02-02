package excepciones.playlist;

public class ContenidoDuplicadoException extends Exception{
    public ContenidoDuplicadoException(){
        super("Contenido duplicado.");
    }
    public ContenidoDuplicadoException(String mensaje){
        super(mensaje);
    }
}
