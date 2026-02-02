package excepciones.artista;

public class AlbumYaExisteException extends Exception{
    public AlbumYaExisteException(){
        super("Album ya existe.");
    }
    public AlbumYaExisteException(String mensaje){
        super(mensaje);
    }
}
