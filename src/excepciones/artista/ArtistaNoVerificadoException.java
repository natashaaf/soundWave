package excepciones.artista;

public class ArtistaNoVerificadoException extends Exception{
    public ArtistaNoVerificadoException(){
        super("Artista no verificado.");
    }
    public ArtistaNoVerificadoException(String mensaje){
        super(mensaje);
    }
}
