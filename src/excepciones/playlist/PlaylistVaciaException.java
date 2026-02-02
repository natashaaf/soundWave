package excepciones.playlist;

public class PlaylistVaciaException extends Exception{
    public PlaylistVaciaException(){
        super("Playlist vacía.");
    }
    public PlaylistVaciaException(String mensaje){
        super(mensaje);
    }
}
