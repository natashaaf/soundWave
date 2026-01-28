package modelo.usuarios;

import enums.TipoSuscripcion;

import java.time.LocalDateTime;

public abstract class Usuario {
    private String id;
    private String nombre;
    private String email;
    private String password;
    private TipoSuscripcion suscripcion;
    private List<Contenido> historial;
    private List<Playlist> misPlaylists;
    private LocalDateTime fechaRegistro;

    //construtor
    public Usuario(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, List<Contenido> historial, List<Playlist> misPlaylists, LocalDateTime fechaRegistro) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
        this.historial = historial;
        this.misPlaylists = misPlaylists;
        this.fechaRegistro = fechaRegistro;
    }

    public abstract void reproducir (Contenido contenido);

    //metodos
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
