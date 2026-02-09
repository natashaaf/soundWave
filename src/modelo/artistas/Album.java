package modelo.artistas;
import enums.GeneroMusical;
import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;

import java.util.ArrayList;
import java.util.Date;

public class Album {

    // Atributos
    private String id;
    private String titulo;
    private Artista artista;
    private Date fechaLanzamiento;
    private ArrayList<Cancion> canciones;
    private String portadaURL;
    private String discografica;
    private String tipoAlbum;

    // Constante
    private static final int MAX_CANCIONES = 20;

    // Constructores
    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;

    }

    public Album(String titulo, Artista artista, Date fechaLanzamiento) {
        this(titulo, artista, fechaLanzamiento, null, null);
    }

    // Getters and setters

    public String getId() {
        return id;}

    public void setId(String id) {
        this.id = id;}

    public String getTitulo() {
        return titulo;}

    public void setTitulo(String titulo) {
        this.titulo = titulo;}

    public Artista getArtista() {
        return artista;}

    public void setArtista(Artista artista) {
        this.artista = artista;}

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;}

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;}

    public ArrayList<Cancion> getCanciones() {
        return canciones;}

    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;}

    public String getPortadaURL() {
        return portadaURL;}

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;}

    public String getDiscografica() {
        return discografica;}

    public void setDiscografica(String discografica) {
        this.discografica = discografica;}

    public String getTipoAlbum() {
        return tipoAlbum;}

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;}

    public static int getMaxCanciones() {
        return MAX_CANCIONES;}

    // Métodos

    // Métodos (composición / creación)
    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero) throws AlbumCompletoException, DuracionInvalidaException {
        return crearCancion(titulo, duracionSegundos, genero);
    }

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero, String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException {
        // Validar si el álbum tiene espacio (Composición)
        if (this.canciones.size() >= MAX_CANCIONES) {
            throw new AlbumCompletoException("El álbum " + this.titulo + "ya alcanzó el límite de " + MAX_CANCIONES + "canciones.");
        }

        // Crea la instancia de Cancion
        Cancion nuevaCancion = new Cancion(titulo, duracionSegundos, this.artista, genero, letra, explicit);
        nuevaCancion.setAlbum(this);

        this.canciones.add(nuevaCancion);
        return nuevaCancion;
    }

    // Métodos (gestión)

    //Eliminar por posición
    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {

        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("Canción no encontrada.");
        }
        this.canciones.remove(posicion - 1);
    }

    // Eliminar por referencia de objeto
    public void eliminarCancion(Cancion cancion) throws CancionNoEncontradaException {
        if (!this.canciones.remove(cancion)) {
            throw new CancionNoEncontradaException("La canción no existe en este álbum.");
        }
        cancion.setAlbum(null); // Limpiamos la relación
    }

    // Suma de duraciones en segundos
    public int getDuracionTotal() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getDuracion();
        }
        return total;
    }

    public String getDuracionTotalFormateada() {
        int segundosTotales = getDuracionTotal();
        int horas = segundosTotales / 3600;
        int minutos = (segundosTotales % 3600) / 60;
        int segundos = segundosTotales % 60;

        if (horas > 0) {
            return String.format("%02d:%02d:%02d", horas, minutos, segundos);
        } else {
            return String.format("%02d:%02d", minutos, segundos);
        }
    }

    // Tamaño de la lista
    public int getNumCanciones() {
        return canciones.size();
    }

    // Ordena por popularidad
    public void ordenarPorPopularidad() {
        // (c2, c1) en lugar de (c1, c2) hace que el orden sea DESCENDENTE
        this.canciones.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));
    }

    // Obtener por posición (1-based)
    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("Posición inválida: " + posicion);
        }
        return canciones.get(posicion - 1);
    }

    // Suma de todas las reproducciones
    public int getTotalReproducciones() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getReproducciones();
        }
        return total;
    }

    // Overrides

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", artista=" + artista +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", canciones=" + canciones +
                ", portadaURL='" + portadaURL + '\'' +
                ", discografica='" + discografica + '\'' +
                ", tipoAlbum='" + tipoAlbum + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return id.equals(album.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}







