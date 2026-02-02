package excepciones.plataforma;

public class ArtistaNoEncontradoException extends Exception{
    public ArtistaNoEncontradoException(){
        super("Artista no encontrado.");
    }
    public ArtistaNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
