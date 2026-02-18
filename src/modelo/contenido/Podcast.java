package modelo.contenido;

import enums.CategoriaPodcast;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.contenido.EpisodioNoEncontradoException;
import excepciones.contenido.TranscripcionNoDisponibleException;
import excepciones.descarga.ContenidoYaDescargadoException;
import interfaces.Descargable;
import interfaces.Reproducible;
import modelo.artistas.Creador;
import java.util.ArrayList;

/**
 * Gestiona temporadas, invitados y transcripciones de podcast de la Plataforma.
 */
public class Podcast extends Contenido implements Reproducible, Descargable {

    // Atributos
    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private ArrayList<String> invitados;
    private String transcripcion;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    // Constructores

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.invitados = new ArrayList<>();
    }

    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria, String descripcion) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.descripcion = descripcion;
        this.invitados = new ArrayList<>();
    }

    // Getters and setters

    public Creador getCreador() {
        return creador;
    }
    public void setCreador(Creador creador) {
        this.creador = creador;
    }

    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public int getTemporada() {
        return temporada;
    }

    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    public String getDescripcion() {
        return descripcion; }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPodcast getCategoria() {
        return categoria;
    }

    /**
     * Implementación del método de la clase padre para obtener el género.
     * */
    @Override
    public CategoriaPodcast getGenero() {
        return this.categoria;
    }

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

    /** Devuelve una copia de la lista de invitados para proteger la original.*/
    public ArrayList<String> getInvitados() {
        return new ArrayList<>(this.invitados);
    }

    public void setInvitados(ArrayList<String> invitados) {
        this.invitados = invitados;
    }

    public String getTranscripcion() {
        return transcripcion;
    }
    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }
    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;
    }

    public boolean isPausado() {
        return pausado; }

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
     * Activa el estado de reproducción y suma una visita si el podcast está disponible.
     * */
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (isDisponible()) {
            this.reproduciendo = true;
            this.reproducciones++;
        } else {
            throw new ContenidoNoDisponibleException("Contenido no disponible.");
        }
    }

    // Implementación de interfaz Reproducible

    public void play() {
        this.reproduciendo = true;
        this.pausado = false;
        System.out.println("Reproduciendo podcast: " + getTitulo());
    }

    public void pause() {
        if (this.reproduciendo) {
            this.pausado = true;
            System.out.println("Podcast pausado.");
        }
    }

    public void stop() {
        this.reproduciendo = false;
        this.pausado = false;
        System.out.println("Reproducción detenida.");
    }

    @Override
    public int getDuracion() { return getDuracionSegundos(); }

    // Implementación de interfaz Descargable

    /**
     *  Marca el podcast como bajado. Si ya lo estaba, avisa con un error.
     *  */
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (descargado) {
            throw new ContenidoYaDescargadoException("Este podcast ya fue descargado");
        } else {
            this.descargado = true;
            System.out.println("Contenido descargado.");
            return true;
        }
    }

    public boolean eliminarDescarga() {
        if (descargado) {
            this.descargado = false;
            System.out.println("Contenido eliminado.");
            return true;
        }
        return false;
    }

    public int espacioRequerido() {
        return 0;
    }

    // Métodos de lógica

    public String obtenerDescripcion() {
        return getDescripcion();
    }

    /**
     * Añade un nombre a la lista de invitados si no está vacío y no está ya registrado.
     * */
    public void agregarInvitado(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            if (!invitados.contains(nombre)) {
                invitados.add(nombre);
                System.out.println("Invitado " + nombre + " añadido correctamente.");
            } else {
                System.out.println("Este invitado ya está en la lista.");
            }
        }
    }

    public boolean esTemporadaNueva() {
        return temporada == 1;
    }

    /**
     * Devuelve el texto del episodio si existe; si no, lanza un error.
     * */
    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException {
        if (transcripcion != null) {
            return this.transcripcion;
        } else {
            throw new TranscripcionNoDisponibleException("Transcripción no disponible.");
        }
    }

    /**
     * Valida que los números de episodios y temporada sean coherentes.
     * */
    public void validarEpisodio() throws EpisodioNoEncontradoException {
        if (this.numeroEpisodio <= 0 || this.temporada < 1)
            throw new EpisodioNoEncontradoException("Episodio inválido.");
    }

    @Override
    public String toString() {
        return "Podcast: " + getTitulo() + " (Temporada " + temporada + ", Ep. " + numeroEpisodio + ")";
    }
}