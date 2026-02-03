package modelo.contenido;

import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Album;
import modelo.artistas.Artista;

import java.util.UUID;

public class Cancion extends Contenido implements Reproducible, Descargable {

    // Atributos
    private String letra;
    private Artista artista;
    private Album album;
    private GeneroMusical genero;
    private String audioURL;
    private boolean explicit;
    private String ISRC;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    // Constructores

    // Constructores basico (URL por defecto y ISRC)
    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;

        // Valores por defecto
        this.ISRC = generarISRC();
        this.letra = null;
        this.audioURL = "https://audioPorDefecto/track.mp3";
        this.explicit = false;

        // Estado por defecto de las canciones
        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;


    }


    // Constructores2 (canción con letra y flag explícito)
    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero, String letra, boolean explicit) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);

        this.artista = artista;
        this.genero = genero;
        this.letra = letra;
        this.explicit = explicit;

        this.audioURL = "https.www.audioPorDefecto/track.mp3";
        this.ISRC = generarISRC();
    }

    // Getters and setters
    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public GeneroMusical getGenero() {
        return genero;
    }

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;
    }

    public String getAudioURL() {
        return audioURL;
    }

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;
    }

    public boolean isExplicit() {
        return explicit;
    }

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;
    }

    public String getISRC() {
        return this.ISRC;
    }

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

    public boolean isDescargado() {
        return descargado;
    }

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;
    }

    // Overrides

    // Override contenido
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if(isDisponible()){
            this.reproduciendo = true;
            this.reproducciones++;
        }
    }

    // Implementación interfaz reproducible
    @Override
    public void play() {
        this.reproduciendo = true;
        this.pausado = false;

        System.out.println("Reproduciendo canción: " + getTitulo() + " - " + artista.getNombreArtistico());
        System.out.println("Estado: [PLAY]");
    }

    @Override
    public void pause() {
        if (this.reproduciendo) {
            this.pausado = true;
            System.out.println("Canción pausada: " + getTitulo());
        } else {
            System.out.println("No se puede pausar.");
        }
    }

    @Override
    public void stop() {
        this.reproduciendo = false;
        this.pausado = false;
        System.out.println("Reproducción detenida y reseteada");
    }

    @Override
    public int getDuracion() {
        return super.getDuracionSegundos();
    }

    // Implementación interfaz Descargable

    @Override
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (this.descargado) {
            throw new ContenidoYaDescargadoException("La canción ya fue descargada.");
        }
        descargado = true;
        System.out.println("Canción " + getTitulo() + " descargada con éxito.");
        return true;
    }

    @Override
    public boolean eliminarDescarga() {
        if (this.descargado) {
            this.descargado = false;
            System.out.println("Descarga eliminada");
            return true;
        }
        return false;
    }

    // No se utiliza en ninguno lado
    @Override
    public int espacioRequerido() {
        return 0;
    }

    // Métodos
    public String obtenerLetra() throws LetraNoDisponibleException {
        if (this.letra == null || this.letra.isEmpty()) {
            throw new LetraNoDisponibleException("No se encontró la letra.");
        } else {
            return this.letra;
        }
    }

    public boolean esExplicit() {
        return this.explicit;
    }

    public void cambiarGenero(GeneroMusical nuevoGenero) {
        this.genero = nuevoGenero;
        System.out.println("El género ha sido actualizado a " + nuevoGenero);
    }

    public void validarAudioULR() throws ArchivoAudioNoEncontradoException {
        if (this.audioURL == null || this.audioURL.isEmpty()) {
            throw new ArchivoAudioNoEncontradoException("La URL de audio tiene un formato inválido.");
        }
    }
    private String generarISRC() {
        return "ISRC: " + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    @Override
    public String toString() {
        return "Cancion{" +
                "artista:" + artista +
                "duración:" + getDuracionSegundos() +
                '}';
    }
}

