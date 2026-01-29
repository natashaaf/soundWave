package modelo.contenido;

import enums.GeneroMusical;
import modelo.artistas.Album;
import modelo.artistas.Artista;

public class Cancion extends Contenido {

    // Atributos
    private String letra;
    private Artista artista;
    private Album album;
    private GeneroMusical genero;
    private String audioURL;
    private boolean explicit;
    private String isrc;

    // Constructores
    public Cancion(String letra, Artista artista, Album album, GeneroMusical genero, String audioURL, boolean explicit, String isrc) {
        this.letra = letra;
        this.artista = artista;
        this.album = album;
        this.genero = genero;
        this.audioURL = audioURL;
        this.explicit = explicit;
        this.isrc = isrc;
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

    public String getIsrc() {
        return isrc;
    }

    public void setIsrc(String isrc) {
        this.isrc = isrc;
    }

    // Metodos
    @Override
    public void reproducir() {

    }
    public String obtenerLetra(){
        //TODO;
        return "";
    }
    public boolean esExplicit(){
        return explicit;
    }
    public void cambiarGenero(GeneroMusical genero){
    }
    public void play(){}
    public void pause(){}
    public void stop(){}
    public boolean descargar(){
        //TODO
        return false;
    }
    public boolean eliminarDescargar(){
        //TODO
        return false;
    }
}
