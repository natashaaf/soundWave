package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Creador {

    // Atributos
    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String, String> redeSociales;
    private ArrayList<CategoriaPodcast>categoriaPrincipales;
    private static final int Max_Episodios = 500;

    // Constructores
    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Creador (String nombreCanal, String nombre){
        this (nombreCanal, nombre, "");
    }

    // Getters and setters


    public String getId() {
        return id;}

    public void setId(String id) {
        this.id = id;}

    public String getNombreCanal() {
        return nombreCanal;}

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;}

    public String getNombre() {
        return nombre;}

    public void setNombre(String nombre) {
        this.nombre = nombre;}

    public ArrayList<Podcast> getEpisodios() {
        return episodios;}

    public void setEpisodios(ArrayList<Podcast> episodios) {
        this.episodios = episodios;}

    public int getSuscriptores() {
        return suscriptores;}

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;}

    public String getDescripcion() {
        return descripcion;}

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;}

    public HashMap<String, String> getRedeSociales() {
        return redeSociales;}

    public void setRedeSociales(HashMap<String, String> redeSociales) {
        this.redeSociales = redeSociales;}

    public ArrayList<CategoriaPodcast> getCategoriaPrincipales() {
        return categoriaPrincipales;}

    public void setCategoriaPrincipales(ArrayList<CategoriaPodcast> categoriaPrincipales) {
        this.categoriaPrincipales = categoriaPrincipales;}

    public int getNumEpisodios(){
        return this.episodios.size();
    }


    // Métodos
    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if (this.episodios.size() >= Max_Episodios){
            throw  new LimiteEpisodiosException("El creador ha alcanzado el limite máximo de" + Max_Episodios + " episodios.");
        }
        // para episodio conocer a su Creador (este objeto)
        episodio.setCreador(this);

        this.episodios.add(episodio);
        System.out.println("Episodio " + episodio.getTitulo() + " publicado con éxito. ");
    }
    public EstadisticasCreador obtenerEstadisticas(){
        // Retornamos una nueva instancia de la clase de utilidades pasando este creador (this)
        return new EstadisticasCreador(this);
    }
    public void agregarRedSocial(String red, String usuario){
        // El HashMap se encarga de añadir o actualizar si la red ya existía
        this.redeSociales.put(red, usuario);

    }
    public double calcularPromedioReproducciones() {
        if (episodios.isEmpty()) {
            return 0.0;
        }
        double sumaTotal = 0;
        for (Podcast p : episodios) {
            sumaTotal += p.getReproducciones();
        }

        return sumaTotal / episodios.size();
    }
    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException{
        // Usamos un iterador o removeIf para evitar problemas de concurrencia al borrar
        boolean eliminado = episodios.removeIf(p -> p.getId().equals(idEpisodio));

        if (!eliminado) {
            throw new EpisodioNoEncontradoException("No se encontró el episodio con ID: " + idEpisodio);
        }
    }

    public int getTotalReproducciones(){
        int total = 0;
        for (Podcast episodio : episodios) {
            // Para acceder al contador de reproducciones heredado de Contenido
            total += episodio.getReproducciones();
        }
        return total;
    }
    public void incrementarSuscriptores(){
        this.suscriptores++;

    }
    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad){
        // Copia de seguridad
        ArrayList<Podcast> copiaEpisodios = new ArrayList<>(this.episodios);

        // Ordenación de mayor a menor según reproducciones
        copiaEpisodios.sort((p1, p2) -> Integer.compare(p2.getReproducciones(), p1.getReproducciones()));

        // Determinamos cuántos elementos devolver (evitando pedir más de los que existen)
        int limite = Math.min(cantidad, copiaEpisodios.size());

        // Devolvemos la sublista
        return new ArrayList<>(copiaEpisodios.subList(0, limite));

    }
    public int getUltimaTemporada(){
        // Si no hay episodios, la temporada es 0
        if (episodios.isEmpty()) {
            return 0;
        }

        int maxTemporada = 0;
        for (Podcast episodio : episodios) {
            // Comparamos la temporada de cada episodio
            if (episodio.getTemporada() > maxTemporada) {
                maxTemporada = episodio.getTemporada();
            }
        }
        return maxTemporada;
    }

    // Overrides


    @Override
    public String toString() {
        return "Creador{" +
                "id='" + id + '\'' +
                ", nombreCanal='" + nombreCanal + '\'' +
                ", nombre='" + nombre + '\'' +
                ", episodios=" + episodios +
                ", suscriptores=" + suscriptores +
                ", descripcion='" + descripcion + '\'' +
                ", redeSociales=" + redeSociales +
                ", categoriaPrincipales=" + categoriaPrincipales +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creador creador = (Creador) obj;
        return id.equals(creador.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }

}
