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

/**
 * Clase Cancion en la plataforma.
 * Implementa las interfaces para que pueda ser reproducida y descargada.
 */
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

    /**
     * Constructor básico para una canción con valores por defecto.
     */
    public Cancion(String titulo, int duracionSegundos, Artista artista, GeneroMusical genero) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.artista = artista;
        this.genero = genero;

        this.ISRC = generarISRC();
        this.letra = null;
        this.audioURL = "https://audioPorDefecto/track.mp3";
        this.explicit = false;

        this.reproduciendo = false;
        this.pausado = false;
        this.descargado = false;
    }

    /**
     * Constructor para canciones que incluyen letra y marca de contenido explícito.
     */
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

    /** Obtiene la letra de la canción. */
    public String getLetra() {
        return letra;
    }

    /** Permite asignar o cambiar la letra de la canción.*/
    public void setLetra(String letra) {
        this.letra = letra;
    }

    /** Devuelve el objeto Artista que compuso la canción. */
    public Artista getArtista() { return artista; }

    /** Asigna un artista a la canción.*/
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    /** Obtiene el álbum al que pertenece esta canción.*/
    public Album getAlbum() {
        return album;
    }

    /** Asigna la canción a un álbum específico. */
    public void setAlbum(Album album) {
        this.album = album;
    }

    /**
     * Devuelve el género musical de la canción.
     */
    @Override
    public GeneroMusical getGenero() {
        return this.genero;
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

    /** Indica si la canción contiene lenguaje explícito.*/
    public boolean isExplicit() {
        return explicit;
    }

    /** Define si la canción debe marcarse como explícita o no. */
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

    // Overrides de Contenido

    /**
     * Inicia la reproducción básica si el archivo está disponible en la plataforma.
     */
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if(isDisponible()){
            this.reproduciendo = true;
            this.reproducciones++;
        } else{
            throw new ContenidoNoDisponibleException("Contenido no disponible.");
        }
    }

    // Implementación interfaz reproducible

    /**
     * Empieza a sonar la música y actualiza el estado visual del reproductor.
     */
    @Override
    public void play() {
        this.reproduciendo = true;
        this.pausado = false;
        System.out.println("Reproduciendo canción: " + getTitulo() + " - " + artista.getNombreArtistico());
        System.out.println("Estado: [PLAY]");
    }

    /**
     * Detiene temporalmente la música si estaba reproduciendo.
     */
    @Override
    public void pause() {
        if (this.reproduciendo) {
            this.pausado = true;
            System.out.println("Canción pausada: " + getTitulo());
        } else {
            System.out.println("No se puede pausar.");
        }
    }

    /**
     * Detiene la canción por completo y resetea los estados de reproducción.
     */
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

    /**
     * Guarda la canción para uso offline. Lanza error si ya se bajó antes.
     */
    @Override
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (this.descargado) {
            throw new ContenidoYaDescargadoException("La canción ya fue descargada.");
        }
        this.descargado = true;
        System.out.println("Canción " + getTitulo() + " descargada con éxito.");
        return true;
    }

    /**
     * Elimina el archivo local de la canción descargada.
     */
    @Override
    public boolean eliminarDescarga() {
        if (this.descargado) {
            this.descargado = false;
            System.out.println("Descarga eliminada");
            return true;
        }
        return false;
    }

    @Override
    public int espacioRequerido() {
        return 0;
    }

    // Métodos de lógica

    /**
     * Intenta obtener la letra; si no existe o está vacía, avisa con un error.
     */
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

    /**
     * Genera un código de identificación musical único basado en UUID.
     */
    private String generarISRC() {
        return "ISRC: " + UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }

    @Override
    public String toString() {
        return "Cancion: " + getTitulo() + " - " + (artista != null ? artista.getNombreArtistico() : "Desconocido");
    }
}