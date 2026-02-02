package modelo.contenido;

import enums.GeneroMusical;
import excepciones.contenido.ArchivoAudioNoEncontradoException;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.LetraNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import excepciones.descarga.LimiteDescargasException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Album;
import modelo.artistas.Artista;

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

    public Cancion(String titulo, int duracionSegundos, String letra, Artista artista, GeneroMusical genero, boolean explicit, String audioURL, String ISRC, boolean reproduciendo, boolean pausado, boolean descargado) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.letra = letra;
        this.artista = artista;
        this.genero = genero;
        this.explicit = explicit;
        this.audioURL = audioURL;
        this.ISRC = ISRC;
        this.reproduciendo = reproduciendo;
        this.pausado = pausado;
        this.descargado = descargado;
    }

    public Cancion(String letra, Artista artista, GeneroMusical genero, boolean explicit, String audioURL, String ISRC, boolean reproduciendo, boolean pausado, boolean descargado) {
        this.letra = letra;
        this.artista = artista;
        this.genero = genero;
        this.explicit = explicit;
        this.audioURL = audioURL;
        this.ISRC = ISRC;
        this.reproduciendo = reproduciendo;
        this.pausado = pausado;
        this.descargado = descargado;
    }

    // Constructores basico (cuando hay poca información)
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

    // Constructores avanzados (cuando ya si tiene letra y es explícita)
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
        this.letra = letra;}

    public Artista getArtista() {
        return artista;}

    public void setArtista(Artista artista) {
        this.artista = artista;}

    public Album getAlbum() {
        return album;}

    public void setAlbum(Album album) {
        this.album = album;}

    public GeneroMusical getGenero() {
        return genero;}

    public void setGenero(GeneroMusical genero) {
        this.genero = genero;}

    public String getAudioURL() {
        return audioURL;}

    public void setAudioURL(String audioURL) {
        this.audioURL = audioURL;}

    public boolean isExplicit() {
        return explicit;}

    public void setExplicit(boolean explicit) {
        this.explicit = explicit;}

    public String getISRC() {
        return ISRC;}

    public void setISRC(String ISRC) {
        this.ISRC = ISRC;}

    public boolean isReproduciendo() {
        return reproduciendo;}

    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;}

    public boolean isPausado() {
        return pausado;}

    public void setPausado(boolean pausado) {
        this.pausado = pausado;}

    public boolean isDescargado() {
        return descargado;}

    public void setDescargado(boolean descargado) {
        this.descargado = descargado;}

    // Overrides
    @Override
    public boolean descargar() throws LimiteDescargasException, ContenidoYaDescargadoException {
        return false;}

    @Override
    public boolean eliminarDescarga() {
        return false;}

    @Override
    public int espacioRequerido() {
        return 0;}

    @Override
    public void play() {}

    @Override
    public void pause() {}

    @Override
    public void stop() {}

    @Override
    public int getDuracion() {
        return 0;}

    @Override
    public void reproducir() throws ContenidoNoDisponibleException {}

    // Métodos
    public String obtenerLetra() throws LetraNoDisponibleException {
        return "";
    }
    public boolean isExplicit(){
        return false;
    }
    public void cambiarGenero(GeneroMusical nuevoGenero){}
    public void validarAudioULR() throws ArchivoAudioNoEncontradoException {}
}


