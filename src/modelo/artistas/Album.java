package modelo.artistas;

import enums.GeneroMusical;
import excepciones.artista.AlbumCompletoException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.playlist.CancionNoEncontradaException;
import modelo.contenido.Cancion;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Date;

/**
 * Representa un álbum musical que agrupa varias canciones.
 * Controla el límite de pistas, la duración total y la relación con el artista.
 */
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

    /** Crea un álbum con toda la información técnica y comercial. */
    public Album(String titulo, Artista artista, Date fechaLanzamiento, String discografica, String tipoAlbum) {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
        this.canciones = new ArrayList<>();
    }

    /** Constructor simplificado para lanzamientos rápidos. */
    public Album(String titulo, Artista artista, Date fechaLanzamiento) {
        this(titulo, artista, fechaLanzamiento, null, null);
    }

    // Getters and setters

    /** Obtiene el ID del álbum. */
    public String getId() {
        return id; }

    /** * Asigna un ID al álbum. */
    public void setId(String id) {
        this.id = id;
    }

    /** Devuelve el título del álbum. */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** Obtiene el objeto Artista dueño de este álbum. */
    public Artista getArtista() {
        return artista;
    }

    /** Vincula un artista con el álbum. */
    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento; }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento; }

    public ArrayList<Cancion> getCanciones() {
        return canciones; }

    /** Reemplaza todas las canciones del álbum por una nueva lista.*/
    public void setCanciones(ArrayList<Cancion> canciones) {
        this.canciones = canciones;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public String getDiscografica() {
        return discografica; }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }

    /** Devuelve el límite máximo de canciones permitidas por álbum. */
    public static int getMaxCanciones() {
        return MAX_CANCIONES;
    }

    // Métodos

    /**
     * Crea una nueva canción y la añade directamente al álbum (Composición).
     * Verifica que no se superen las 20 canciones permitidas.
     */
    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero) throws AlbumCompletoException, DuracionInvalidaException {
        return crearCancion(titulo, duracionSegundos, genero, null, false);
    }

    public Cancion crearCancion(String titulo, int duracionSegundos, GeneroMusical genero, String letra, boolean explicit) throws AlbumCompletoException, DuracionInvalidaException {
        if (this.canciones.size() >= MAX_CANCIONES) {
            throw new AlbumCompletoException("Límite alcanzado: El álbum ya tiene " + MAX_CANCIONES + " canciones.");
        }

        Cancion nuevaCancion = new Cancion(titulo, duracionSegundos, this.artista, genero, letra, explicit);
        nuevaCancion.setAlbum(this); // Vinculación bidireccional
        this.canciones.add(nuevaCancion);
        return nuevaCancion;
    }

    /**
     * Borra una canción usando su posición en el tracklist (empezando en 1).
     */
    public void eliminarCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("La posición " + posicion + " no existe.");
        }
        this.canciones.remove(posicion - 1);
    }

    public void eliminarCancion(Cancion cancion) throws CancionNoEncontradaException {}

    /**
     * Suma la duración de todas las canciones en segundos.
     **/
    public int getDuracionTotal() {
        int total = 0;
        for (Cancion c : canciones) {
            total += c.getDuracion();
        }
        return total;
    }

    /**
     * Devuelve la duración total formateada (HH:MM:SS o MM:SS).
     */
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

    /** Indica cuántas canciones hay registradas en el álbum actualmente. */
    public int getNumCanciones() {
        return canciones.size();
    }

    /**
     * Reordena la lista de canciones poniendo las más escuchadas primero.
     */
    public void ordenarPorPopularidad() {
        this.canciones.sort((c1, c2) -> Integer.compare(c2.getReproducciones(), c1.getReproducciones()));
    }

    /**
     * Obtiene una canción específica mediante su índice en el álbum (1-based).
     */
    public Cancion getCancion(int posicion) throws CancionNoEncontradaException {
        if (posicion < 1 || posicion > canciones.size()) {
            throw new CancionNoEncontradaException("Índice inválido.");
        }
        return canciones.get(posicion - 1);
    }

    /**
     * Suma el contador de reproducciones de todas las pistas del álbum.
     */
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
        return "Álbum: " + titulo + " (" + (artista != null ? artista.getNombreArtistico() : "Varios") + ")";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Album album = (Album) obj;
        return id != null && id.equals(album.id);
    }

    @Override
    public int hashCode(){
        return id != null ? id.hashCode() : 0;
    }
}