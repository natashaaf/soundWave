package modelo.artistas;

import modelo.contenido.Cancion;

import java.time.LocalDateTime;
import java.util.List;

public class Artista {
    // Atributos
    private String id;
    private String nombreArtistico;
    private String nombreReal;
    private String paisOrigen;
    private List<Cancion> discografia;
    private List<Album> albumes;
    private int oyentesMensuales;
    private boolean verificado;
    private String biografia;

    // Constructores
    public Artista(String id, String nombreArtistico, String nombreReal, String paisOrigen, List<Cancion> discografia, List<Album> albumes, int oyentesMensuales, boolean verificado, String bipgrafia) {
        this.id = id;
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.discografia = discografia;
        this.albumes = albumes;
        this.oyentesMensuales = oyentesMensuales;
        this.verificado = verificado;
        this.biografia = biografia;
    }
    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public List<Cancion> getDiscografia() {
        return discografia;
    }

    public void setDiscografia(List<Cancion> discografia) {
        this.discografia = discografia;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    // Metodos
    public void publicarCancion(Cancion cancion){}
    public void crearAlbum(String titulo, LocalDateTime fecha){}
    public int obtenerTopCanciones(int cantidad){
        //TODO
        return cantidad;
    }
    public double calcularPromedioReproducciones(){
        return 100/10;
    }
    public boolean esVerificado(){
        return false;
        //TODO
    }

}
