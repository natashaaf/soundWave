package modelo.contenido;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.contenido.DuracionInvalidaException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public abstract class Contenido {

    //Atributos
    private String id;
    private String titulo;
    private int reproducciones;
    private int likes;
    private int duracionSegundos;
    private List<String> tags;
    private boolean disponible;
    private Date fechaPublicacion;

    // Constructores
    public Contenido(String titulo, int duracionSegundos) throws DuracionInvalidaException {
        this.id = UUID.randomUUID().toString();
        this.titulo = titulo;
        this.reproducciones = 0;
        this.likes = 0;
        this.duracionSegundos = duracionSegundos;
        this.tags = new ArrayList<>();
        this.disponible = true;
        this.fechaPublicacion = new Date();

        // Validación
        if (duracionSegundos > 0){
            throw new DuracionInvalidaException("La duración debe ser mayor a 0.");
        }

    }

    public Contenido() {

    }

    //Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getReproducciones() {
        return reproducciones;
    }

    public void setReproducciones(int reproducciones) {
        this.reproducciones = reproducciones;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public boolean isDisponible() {
        return disponible;
    }

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
    public abstract void reproducir() throws ContenidoNoDisponibleException;

    public void aumentarReproducciones(){
        this.reproducciones++;}

    public void agregarLike(){
        this.likes++;}

    public boolean esPopular(){
        return this.reproducciones > 100000;
    }

    public void validarDuracion(int duracionSegundos) throws DuracionInvalidaException{
        if(this.duracionSegundos <= 0){
            throw new DuracionInvalidaException("La duración es invalida");
        }
    }
    public void agregarTag(String tag){
        if (tag != null && !tieneTag(tag)){
            this.tags.add(tag);
        }
    }
    public boolean tieneTag(String tag){
        return this.tags.contains(tag);
    }
    public void marcarDisponible(){
        this.disponible = true;
    }
    public void marcarNoDisponible(){
        this.disponible = false;
    }
    public String getDuracionFormateada(){
        int minutos = this.duracionSegundos;
        int segundos = this.duracionSegundos;
        return String.format("Duración: ", minutos, segundos);
    }

    //Overrides
    @Override
    public String toString() {
        return "Contenido{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", reproducciones=" + reproducciones +
                ", likes=" + likes +
                ", duracionSegundos=" + duracionSegundos +
                ", tags=" + tags +
                ", disponible=" + disponible +
                ", fechaPublicacion=" + fechaPublicacion +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Contenido contenido = (Contenido) obj;
        return id.equals(contenido.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

}
