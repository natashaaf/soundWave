package modelo.artistas;

import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    // Constructores con datos de verificación y biografia.
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado, String biografia){
        this.nombreArtistico = nombreArtistico;
        this.nombreReal = nombreReal;
        this.paisOrigen = paisOrigen;
        this.verificado = verificado;
        this.biografia = biografia;
        this.discografia = new ArrayList<>();
        this.albumes = new ArrayList<>();
    }

    // Constructores básicos
    public Artista(String nombreArtistico, String nombreReal, String paisOrigen) {
        this(nombreArtistico, nombreReal, paisOrigen, false, null);
    }

    // Getters and setters
    public String getId() {
        return id;}

    public void setId(String id) {
        this.id = id;}

    public String getNombreArtistico() {
        return nombreArtistico;}

    public void setNombreArtistico(String nombreArtistico) {
        this.nombreArtistico = nombreArtistico;}

    public String getNombreReal() {
        return nombreReal;}

    public void setNombreReal(String nombreReal) {
        this.nombreReal = nombreReal;}

    public String getPaisOrigen() {
        return paisOrigen;}

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;}

    public ArrayList<Cancion> getDiscografia() {
        return this.discografia;}

    public void setDiscografia(List<Cancion> discografia) {
        this.discografia = new ArrayList<>();}

    public ArrayList<Album> getAlbumes() {
        return this.albumes;}

    public void setAlbumes(List<Album> albumes) {
        this.albumes = new ArrayList<>();}

    public int getOyentesMensuales() {
        return oyentesMensuales;}

    public void setOyentesMensuales(int oyentesMensuales) {
        this.oyentesMensuales = oyentesMensuales;}

    public boolean isVerificado() {
        return verificado;}

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;}

    public String getBiografia() {
        return biografia;}

    public void setBiografia(String biografia) {
        this.biografia = biografia;}

    // Metodos
    public void publicarCancion(Cancion cancion){
        if (cancion != null) {
            this.discografia.add(cancion);
            System.out.println("La canción '" + cancion.getTitulo() + "' ha sido publicada.");
        } else {
            System.out.println("Error: No se puede publicar una canción nula.");
        }
    }
    public Album crearAlbum(String titulo, Date fecha) throws ArtistaNoVerificadoException, AlbumYaExisteException {
        if(!this.verificado){
           throw new ArtistaNoVerificadoException("El artista debe estar verificado.");
        }
        for (Album a : albumes){
            if(a.getTitulo().equalsIgnoreCase(titulo)){
                throw new AlbumYaExisteException("Ya existe un album con el título: " + titulo);
            }
        }
        Album nuevoAlbum = new Album (titulo, this,fecha);
        this.albumes.add(nuevoAlbum);
        return nuevoAlbum;
    }

    public ArrayList<Cancion> obtenerTopCanciones(int cantidad){

            //copia de seguridad
        ArrayList<Cancion> copia = new ArrayList<>(this.discografia);

            // Ordena de mayor a menor según reproducciones
        copia.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));

            // Define el máximo posible para evitar errores de índice
        int limite = Math.min(cantidad, copia.size());

            // Las mejores canciones
        return (ArrayList<Cancion>) copia.subList(0, limite);
    }

    public double calcularPromedioReproducciones(){
        if (discografia.isEmpty()){
            return 0;
        }
        return (double) getTotalReproducciones()/ discografia.size();
    }

    public boolean esVerificado(){
        return this.verificado;
    }
    public int getTotalReproducciones(){
        int total = 0;
        for (Cancion c : discografia){
            total += c.getReproducciones();
        }
        return total;
    }
    public void verificar(){
        this.verificado = true;

    }
    public void incrementarOyentes(){
        oyentesMensuales++;
    }

    // Overrides


    @Override
    public String toString() {
        return "Artista{" +
                "id='" + id + '\'' +
                ", nombreArtistico='" + nombreArtistico + '\'' +
                ", nombreReal='" + nombreReal + '\'' +
                ", paisOrigen='" + paisOrigen + '\'' +
                ", discografia=" + discografia +
                ", albumes=" + albumes +
                ", oyentesMensuales=" + oyentesMensuales +
                ", verificado=" + verificado +
                ", biografia='" + biografia + '\'' +
                '}';
    }
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Artista artista = (Artista) obj;
        return id.equals(artista.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

}
