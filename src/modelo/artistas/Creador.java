package modelo.artistas;

import enums.CategoriaPodcast;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.EpisodioNoEncontradoException;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;
import java.util.UUID;
import java.util.*;

/**
 * Representa al autor de los podcasts.
 * Gestiona su canal, la lista de episodios publicados y sus estadísticas de audiencia.
 */
public class Creador {

    // Atributos
    private String id;
    private String nombreCanal;
    private String nombre;
    private ArrayList<Podcast> episodios;
    private int suscriptores;
    private String descripcion;
    private HashMap<String, String> redesSociales;
    private ArrayList<CategoriaPodcast> categoriaPrincipales;
    private static final int Max_Episodios = 500;

    // Constructores

    public Creador(String nombreCanal, String nombre, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombreCanal = nombreCanal;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.suscriptores = 0;
        this.episodios = new ArrayList<>();
        this.redesSociales = new HashMap<>();
        this.categoriaPrincipales = new ArrayList<>();
    }

    public Creador (String nombreCanal, String nombre){
        this (nombreCanal, nombre, "");
    }

    // Getters and setters

    /** Obtiene ID del creador.*/
    public String getId() {
        return id;
    }

    /** Asigna un ID al creador.*/
    public void setId(String id) {
        this.id = id;
    }

    /** Devuelve el nombre del canal de podcasts.*/
    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    /** Devuelve el nombre real o artístico de la persona. */
    public String getNombre() {
        return nombre;
    }

    /** Modifica el nombre del creador. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** Obtiene la lista completa de episodios (Podcast) subidos.*/
    public ArrayList<Podcast> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(ArrayList<Podcast> episodios) {
        this.episodios = episodios;
    }

    /** Indica cuántas personas están siguiendo el canal. */
    public int getSuscriptores() {
        return suscriptores;
    }

    /** Actualiza el número de suscriptores del canal. */
    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /** Entrega una copia del mapa de redes sociales para mayor seguridad. */
    public HashMap<String, String> getRedesSociales() {
        return new HashMap<>(this.redesSociales);
    }

    public void setRedesSociales(HashMap<String, String> redesSociales) {
        this.redesSociales = redesSociales;
    }

    public ArrayList<CategoriaPodcast> getCategoriaPrincipales() {
        return categoriaPrincipales;
    }

    public void setCategoriaPrincipales(ArrayList<CategoriaPodcast> categoriaPrincipales) {
        this.categoriaPrincipales = categoriaPrincipales;
    }

    /** Devuelve el total de episodios que tiene el creador actualmente. */
    public int getNumEpisodios(){
        return this.episodios.size();
    }

    // Métodos

    /**
     * Publica un nuevo episodio.
     * Vincula el podcast con este creador y verifica el límite de 500 episodios.
     */
    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if (this.episodios.size() >= Max_Episodios){
            throw new LimiteEpisodiosException("Límite alcanzado: máximo " + Max_Episodios + " episodios.");
        }
        episodio.setCreador(this);
        this.episodios.add(episodio);
        System.out.println("Episodio " + episodio.getTitulo() + " publicado con éxito.");
    }

    /** Llama a la utilidad de estadísticas para procesar los datos de este creador. */
    public EstadisticasCreador obtenerEstadisticas(){
        return new EstadisticasCreador(this);
    }

    /**
     * Inserta una red social (ej: "Instagram", "usuario123") en el perfil. */
    public void agregarRedSocial(String plataforma, String usuario){
        this.redesSociales.put(plataforma.toLowerCase(), usuario);
    }

    /**
     * Calcula la media de escuchas dividiendo el total entre el número de episodios.
     */
    public double calcularPromedioReproducciones() {
        if (episodios.isEmpty()) return 0.0;

        double sumaTotal = 0;
        for (Podcast p : episodios) {
            sumaTotal += p.getReproducciones();
        }
        return sumaTotal / episodios.size();
    }

    /**
     * Borra un episodio de la lista usando su ID único.
     */
    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException{
        boolean eliminado = episodios.removeIf(p -> p.getId().equals(idEpisodio));

        if (!eliminado) {
            throw new EpisodioNoEncontradoException("ID de episodio no encontrado: " + idEpisodio);
        }
    }

    /**
     * Suma las reproducciones acumuladas de todos los podcasts publicados.
     */
    public int getTotalReproducciones(){
        int total = 0;
        for (Podcast episodio : episodios) {
            total += episodio.getReproducciones();
        }
        return total;
    }

    public void incrementarSuscriptores(){
        this.suscriptores++;
    }

    /**
     * Filtra y devuelve los episodios más escuchados según la cantidad solicitada.
     */
    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad){
        ArrayList<Podcast> copiaEpisodios = new ArrayList<>(this.episodios);
        copiaEpisodios.sort((p1, p2) -> Integer.compare(p2.getReproducciones(), p1.getReproducciones()));

        int limite = Math.min(cantidad, copiaEpisodios.size());
        return new ArrayList<>(copiaEpisodios.subList(0, limite));
    }

    /**
     * Recorre los episodios para encontrar el número de temporadas más alto registrado.
     * */
    public int getUltimaTemporada(){
        if (episodios.isEmpty()) return 0;

        int maxTemporada = 0;
        for (Podcast episodio : episodios) {
            if (episodio.getTemporada() > maxTemporada) {
                maxTemporada = episodio.getTemporada();
            }
        }
        return maxTemporada;
    }

    // Overrides

    @Override
    public String toString() {
        return "Canal: " + nombreCanal + " | Autor: " + nombre;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Creador creador = (Creador) obj;
        return Objects.equals(id, creador.id);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }
}