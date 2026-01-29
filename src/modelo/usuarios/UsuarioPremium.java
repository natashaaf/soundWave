package modelo.usuarios;
import enums.TipoSuscripcion;
import java.time.LocalDateTime;

public class UsuarioPremium extends Usuario {
    private boolean descargasOffline;
    private int maxDescargas;
    private List<Contenido> descargados;
    private String calidadAudio;

    //construtor
    public UsuarioPremium(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, List<Contenido> historial, List<Playlist> misPlaylists, LocalDateTime fechaRegistro, boolean descargasOffline, int maxDescargas, List<Contenido> descargados, String calidadAudio) {
        super(id, nombre, email, password, suscripcion, historial, misPlaylists, fechaRegistro);
        this.descargasOffline = descargasOffline;
        this.maxDescargas = maxDescargas;
        this.descargados = descargados;
        this.calidadAudio = calidadAudio;
    }

    //metodos
    @Override
    public void reproducir(Contenido contenido) {
        //TODO
    }

    public void descargar (Contenido contenido){
        //TODO
    }
    public void eliminarDescarga(Contenido contenido){
        //TODO
    }
    public boolean verificarEspacioDescarga(){
        //TODO
        return false;
    }

}
