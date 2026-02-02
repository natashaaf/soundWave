package excepciones.playlist;

public class PlaylistLlenaException extends Exception{
    public PlaylistLlenaException(){
        super("Playlist llena.");
    }
    public PlaylistLlenaException(String mensaje){
        super(mensaje);
    }
}
