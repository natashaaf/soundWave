package modelo.contenido;

import enums.CategoriaPodcast;
import modelo.artistas.Creador;

import java.util.List;

public class Podcast extends Contenido{

    // Atributos
    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private List<String> invitadosList;
    private String transcripcion;

    // Constructores
    public Podcast(Creador creador, int numeroEpisodio, int temporada, String descripcion, CategoriaPodcast categoria, List<String> invitadosList, String transcripcion) {
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.invitadosList = invitadosList;
        this.transcripcion = transcripcion;
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

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;
    }

    public List<String> getInvitadosList() {
        return invitadosList;
    }

    public void setInvitadosList(List<String> invitadosList) {
        this.invitadosList = invitadosList;
    }

    public String getTranscripcion() {
        return transcripcion;
    }

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;
    }

    // Metodos
    @Override
    public void reproducir() {
    }

    public String obtenerDescripcion(){
        return getDescripcion();
    }
    public void agregarInvitado(String nombre){
        //TODO
    }
    public boolean esTemporadoraNueva(){
        return false;
    }
    public void play(){}
    public void pause(){}
    public void stop(){}
    public boolean descargar(){
        return false;
        //TODO
    }
    public boolean eliminarDescarga(){
        return false;
        //TODO
    }
}
