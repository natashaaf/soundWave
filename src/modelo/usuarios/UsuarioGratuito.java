package modelo.usuarios;

import enums.TipoSuscripcion;

import java.time.LocalDateTime;

public class UsuarioGratuito extends Usuario {
    private int anunciosEscuchados;
    private LocalDateTime ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;

    //construtor

    public UsuarioGratuito(String id, String nombre, String email, String password, TipoSuscripcion suscripcion, List<Contenido> historial, List<Playlist> misPlaylists, LocalDateTime fechaRegistro, int anunciosEscuchados, LocalDateTime ultimoAnuncio, int reproduccionesHoy, int limiteReproducciones) {
        super(id, nombre, email, password, suscripcion, historial, misPlaylists, fechaRegistro);
        this.anunciosEscuchados = anunciosEscuchados;
        this.ultimoAnuncio = ultimoAnuncio;
        this.reproduccionesHoy = reproduccionesHoy;
        this.limiteReproducciones = limiteReproducciones;

    }


    //metodo
    public void reproducir(Contenido contenido) {
        //TODO
    }

    //
    public void verAnuncio() {
        //TODO
    }

    //public puedeReproducir() {
        //TODO
    }
    public abstract reiniciarContadorDiario();


    //expepciones
    private LimiteDiarioAlcanzadoException;

    private AnuncioRequeridoException;




}
