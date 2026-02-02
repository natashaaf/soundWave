package excepciones.artista;

public class AlbumCompletoException extends Exception{
    public AlbumCompletoException(){
        super("Album ya existe.");
    }
    public AlbumCompletoException(String mensaje){
        super(mensaje);
    }
}
