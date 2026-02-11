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
import java.util.List;

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

    // Constructores sin descripcion
    public Podcast(String titulo, int duracionSegundos, Creador creador, int numeroEpisodio, int temporada, CategoriaPodcast categoria) throws DuracionInvalidaException {
        super(titulo, duracionSegundos);
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.categoria = categoria;
        this.invitados = new ArrayList<>();
    }

    // Constructores con descripcion
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
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public CategoriaPodcast getCategoria() {
        return categoria;
    }

    @Override
    public String getGenero() {
        return categoria != null ? categoria.getNombre() : "";
    }

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

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

    //Overrides
    @Override
    public void reproducir() throws ContenidoNoDisponibleException {
        if (isDisponible()) {
            this.reproduciendo = true;
            this.reproducciones++;
        }
    }

    // Implementación interfaz reproducible
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
    public int getDuracion() {
        return getDuracionSegundos();
    }

    // Implementación interfaz descargable
    public boolean descargar() throws ContenidoYaDescargadoException {
        if (descargado) {
            this.descargado = true;
            throw new ContenidoYaDescargadoException("Este podcast ya fue descargado");
        } else {
            System.out.println("Contenido descargado.");
        }
        return false;
    }

    public boolean eliminarDescarga() {
        if (descargado) {
            System.out.println("Contenido eliminado. ");
        }
        return false;
    }

    public int espacioRequerido() {  // No se utiliza en ninguno lado
        return 0;
    }

    // Métodos
    public String obtenerDescripcion() {
        return getDescripcion();
    }

    public void agregarInvitado(String nombre) {
        if (nombre != null && nombre != " ") {
            if (!invitados.contains(nombre)){
                invitados.add(nombre);
                System.out.println("Invitado " + nombre + " añadida con suceso.");
            } else {
                System.out.println("Este invitado ya está en la lista.");
            }
        }
    }

    public boolean esTemporadaNueva() {
        if (temporada == 1) {
            return true;
        } return false;
    }
    public String obtenerTranscripcion() throws TranscripcionNoDisponibleException{
        if (transcripcion != null){
            return this.transcripcion;
        } else{
            throw new TranscripcionNoDisponibleException("Transcripción no disponible.");
        }
    }
    public void validarEpisodio() throws EpisodioNoEncontradoException {
        if (this.numeroEpisodio <= 0 || this.temporada <= 1)
            throw  new EpisodioNoEncontradoException("Episodio invalido.");
    }

    @Override
    public String toString() {
        return "Podcast{" +
                "creador=" + creador +
                ", numeroEpisodio=" + numeroEpisodio +
                ", temporada=" + temporada +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" + categoria +
                ", invitados=" + invitados +
                ", transcripcion='" + transcripcion + '\'' +
                ", reproduciendo=" + reproduciendo +
                ", pausado=" + pausado +
                ", descargado=" + descargado +
                '}';
    }
}



