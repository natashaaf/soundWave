package modelo.contenido;

import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Clase abstracta que para Canción y Podcast.
 * Define los atributos comunes como título, duración y estadísticas de interacción.
 */
public abstract class Contenido {

    // Atributos
    protected String id;
    protected String titulo;
    protected int reproducciones;
    protected int likes;
    protected int duracionSegundos;
    protected ArrayList<String> tags;
    protected boolean disponible;
    protected Date fechaPublicacion;

    // Constructores

    public Contenido(String titulo, int duracionSegundos) throws DuracionInvalidaException {
        // Genera un identificador único automáticamente
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.reproducciones = 0;
        this.likes = 0;
        this.duracionSegundos = duracionSegundos;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();

        // Verificación inicial de seguridad
        if (duracionSegundos <= 0) {
            throw new DuracionInvalidaException("La duración debe ser mayor a 0.");
        }
    }

    // Getters and setters

    /** Obtiene el identificador único del contenido. */
    public String getId() {
        return id;
    }

    /** Asigna un identificador único al contenido. */
    public void setId(String id) {
        this.id = id;
    }

    /** Devuelve el título actual.*/
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** Indica cuántas veces se ha escuchado. */
    public int getReproducciones() {
        return reproducciones;
    }

    /** Actualiza el número total de escuchas. */
    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    /** Devuelve el total de "Me gusta" acumulados. */
    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    /** Entrega la duración total medida en segundos. */
    public int getDuracionSegundos() {
        return this.duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    /** Retorna la lista de tags asociadas al contenido.*/
    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }

    /** Indica si el contenido está habilitado para los usuarios. */
    public boolean isDisponible() {
        return disponible; }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Métodos

    /**
     * Método que cada tipo de contenido implementará para iniciar su reproducción.
     */
    public abstract void reproducir() throws ContenidoNoDisponibleException;

    public void aumentarReproducciones() {
        this.reproducciones++;
    }

    public void agregarLike() {
        this.likes++;
    }

    public boolean esPopular() {
        return true;
    }

    public void validarDuracion(int duracionSegundos) throws DuracionInvalidaException {}

    /**
     * Añade una etiqueta de búsqueda si no existe previamente en la lista.
     **/
    public void agregarTag(String tag) {
        if (tag != null && !tieneTag(tag)) {
            this.tags.add(tag);
        }
    }

    public boolean tieneTag(String tag) {
        return this.tags.contains(tag);
    }
    /** Activa el contenido para que sea visible y reproducible.*/
    public void marcarDisponible() {
        this.disponible = true;
    }
    /** Desactiva el contenido para que nadie pueda acceder a él.*/
    public void marcarNoDisponible() {
        this.disponible = false;
    }

    public String getDuracionFormateada() {
        return "";
    }

    // Método abstracto para obtener género/categoría del contenido
    public abstract Object getGenero();

    // Overrides

    @Override
    public String toString() {
        return "Contenido: " + titulo + " [ID: " + id + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contenido contenido = (Contenido) obj;
        return id != null && id.equals(contenido.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}