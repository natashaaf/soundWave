package modelo.contenido;

import enums.CategoriaPodcast;
import interfaces.Descargable;
import modelo.artistas.Creador;

import java.util.ArrayList;
import java.util.List;

public class Podcast extends Contenido implements Descargable {

    // Atributos
    private Creador creador;
    private int numeroEpisodio;
    private int temporada;
    private String descripcion;
    private CategoriaPodcast categoria;
    private List<String> invitados;
    private String transcripcion;
    private boolean reproduciendo;
    private boolean pausado;
    private boolean descargado;

    // Constructores

    public Podcast(Creador creador, int numeroEpisodio, int temporada, String descripcion, CategoriaPodcast categoria, List<String> invitados, String transcripcion, boolean reproduciendo, boolean pausado, boolean descargado) {
        this.creador = creador;
        this.numeroEpisodio = numeroEpisodio;
        this.temporada = temporada;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.invitados = invitados;
        this.transcripcion = transcripcion;
        this.reproduciendo = reproduciendo;
        this.pausado = pausado;
        this.descargado = descargado;
    }
    // Getters and setters
    public Creador getCreador() {
        return creador;}

    public void setCreador(Creador creador) {
        this.creador = creador;}

    public int getNumeroEpisodio() {
        return numeroEpisodio;}

    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;}

    public int getTemporada() {
        return temporada;}

    public void setTemporada(int temporada) {
        this.temporada = temporada;}

    public String getDescripcion() {
        return descripcion;}

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;}

    public CategoriaPodcast getCategoria() {
        return categoria;}

    public void setCategoria(CategoriaPodcast categoria) {
        this.categoria = categoria;}

    public List<String> getInvitadosList() {
        return invitados;}

    public void setInvitadosList(List<String> invitados) {
        this.invitados = invitados;}

    public String getTranscripcion() {
        return transcripcion;}

    public void setTranscripcion(String transcripcion) {
        this.transcripcion = transcripcion;}

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
