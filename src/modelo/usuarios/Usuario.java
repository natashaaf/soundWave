package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Playlist;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String password;
    private TipoSuscripcion suscripcion;
    private ArrayList<Playlist> misPlaylists;
    private ArrayList<Contenido> historial;
    private Date fechaRegistro;
    private ArrayList<Playlist> playlistsSeguidas;
    private ArrayList<Contenido> contenidosLiked;

    // Constructores
    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion) throws EmailInvalidoException, PasswordDebilException {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
    }

    public abstract void reproducir (Contenido contenido);

    // Métodos

    // MétodoAbstracto


    public String crearPlaylist(String nombre){
        return nombre;
    }
    public Playlist seguirPlaylist(Playlist playlist){
        return playlist;
    }
    public  Contenido darLike(Contenido contenido){
        return contenido;
    }
    public void validarEmail(){
        if (email !=  null && email.contains("@") && email.contains("gmail.com"));
           // return "email invalido";
    }

    public Contenido agregarAlHistorial(Contenido contenido){
        return contenido;
    }

}
