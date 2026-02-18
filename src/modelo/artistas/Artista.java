package modelo.artistas;

import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import modelo.contenido.Cancion;
import java.util.Objects;
import java.util.UUID;
import java.util.*;

/**
 * Representa el artista en la plataforma.
 * Gestiona su información personal, discografía y sus álbumes.
 */
public class Artista {

    // Atributos
    private String id;
    private String nombreArtistico;
    private String nombreReal;
    private String paisOrigen;
    private ArrayList<Cancion> discografia;
    private ArrayList<Album> albumes;
    private int oyentesMensuales;
    private boolean verificado;
    private String biografia;

    // Constructores

    /**
     * Constructor detallado para artistas con biografía y estado de verificación inicial.
     */
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado, String biografia){
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.verificado = verificado;
        this.biografia = biografia;
        this.discografia = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
    }

    /** * Constructor básico para artistas nuevos. */
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen) {
        this(nombreArtistico, nombreReal, paisOrigen, false, null);
    }

    // Getters and setters

    /** Obtiene el ID del artista. */
    public String getId() {
        return id;
    }

    /** Asigna un ID único al artista. */
    public void setId(String id) {
        this.id = id;
    }

    /** Devuelve el nombre artístico del artista. */
    public String getNombreArtistico() {
        return nombreArtistico;
    }

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;
    }

    /** Devuelve el nombre real de la persona. */
    public String getNombreReal() {
        return nombreReal;
    }

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }


    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    /** Obtiene la lista de todas las canciones lanzadas por el artista. */
    public ArrayList<Cancion> getDiscografia() {
        return this.discografia;
    }

    public void setDiscografia(List<Cancion> discografia) {
        this.discografia = new ArrayList<>(discografia);
    }

    /** Devuelve la lista de álbumes publicados. */
    public ArrayList<Album> getAlbumes() {
        return this.albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = new ArrayList<>(albumes);
    }

    public int getOyentesMensuales() {
        return oyentesMensuales;
    }


    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;
    }

    /** Comprueba si el artista es verificado. */
    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado() {
        this.verificado = true;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    // Métodos

    /**
     * Añade una canción individual a la lista de lanzamientos del artista.
     */
    public void publicarCancion(Cancion cancion){
        if (cancion != null) {
            this.discografia.add(cancion);
            System.out.println("La canción '" + cancion.getTitulo() + "' ha sido publicada.");
        } else {
            System.out.println("Error: No se puede publicar una canción nula.");
        }
    }

    /**
     * Crea un nuevo álbum si el artista está verificado y el título no se repite.
     */
    public Album crearAlbum(String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if(!this.verificado){
            throw new ArtistaNoVerificadoException("El artista debe estar verificado para crear álbumes.");
        }
        for (Album a : albumes){
            if(a.getTitulo().equalsIgnoreCase(titulo)){
                throw new AlbumYaExisteException("Ya existe un álbum con el título: " + titulo);
            }
        }
        Album nuevoAlbum = new Album(titulo, this, fecha);
        this.albumes.add(nuevoAlbum);
        return nuevoAlbum;
    }

    /**
     * Filtra las canciones del artista por reproducciones y devuelve un top.
     */
    public ArrayList<Cancion> obtenerTopCanciones(int cantidad){
        ArrayList<Cancion> copia = new ArrayList<>(this.discografia);
        copia.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));

        int limite = Math.min(cantidad, copia.size());
        return new ArrayList<>(copia.subList(0, limite));
    }

    /**
     * Calcula la media de reproducciones por cada canción en la discografía.
     */
    public double calcularPromedioReproducciones(){
        if (discografia.isEmpty()) return 0;
        return (double) getTotalReproducciones() / discografia.size();
    }

    public boolean esVerificado(){
        return this.verificado;
    }

    /**
     * Suma las reproducciones de todos los álbumes del artista.
     */
    public int getTotalReproducciones(){
        int total = 0;
        for (Album album : albumes){
            total += album.getTotalReproducciones();
        }
        return total;
    }

    /** Atribuye el verificado al perfil del artista.*/
    public void verificar(){
        this.verificado = true;
    }

    public void incrementarOyentes(){
        oyentesMensuales++;
    }

    // Overrides

    @Override
    public String toString() {
        return "Artista: " + nombreArtistico + (verificado ? " [Verificado]" : "");
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artista artista = (Artista) obj;
        return Objects.equals(id, artista.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}