package modelo.usuarios;

import enums.TipoSuscripcion;

import java.time.LocalDateTime;

public class UsuarioGratuito extends Usuario {
    private int anunciosEscuchados;
    private LocalDateTime ultimoAnuncio;
    private int reproduccionesHoy;
    private int cancionesSinAnuncio;

    //construtor
    public UsuarioGratuito(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, List<Contenido> historial, List<Playlist> misPlaylists, LocalDateTime fechaRegistro, int anunciosEscuchados, LocalDateTime ultimoAnuncio, int reproduccionesHoy, int cancionesSinAnuncio) {
        super(id, nombre, email, password, suscripcion, historial, misPlaylists, fechaRegistro);
        this.anunciosEscuchados = anunciosEscuchados;
        this.ultimoAnuncio = ultimoAnuncio;
        this.reproduccionesHoy = reproduccionesHoy;
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }
    //getter and setter
    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public void setAnunciosEscuchados(int anunciosEscuchados) {
        this.anunciosEscuchados = anunciosEscuchados;
    }

    public LocalDateTime getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public void setUltimoAnuncio(LocalDateTime ultimoAnuncio) {
        this.ultimoAnuncio = ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    //metodos

    public void verAnuncio() {

    }

    public boolean puedeReproducir() {
        // TODO;
        return false;
    }

    public void reiniciarContadorDiario() {

    }


    @Override
    public void reproducir(Contenido contenido) {

    }
}
