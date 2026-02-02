package excepciones.contenido;

public class EpisodioNoEncontradoException extends Exception{
    public EpisodioNoEncontradoException(){
        super("Episodio no encontrado.");
    }
    public EpisodioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
