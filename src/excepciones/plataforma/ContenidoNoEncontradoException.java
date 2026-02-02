package excepciones.plataforma;

public class ContenidoNoEncontradoException extends Exception{
    public ContenidoNoEncontradoException(){
        super("Contenido no encontrado.");
    }
    public ContenidoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
