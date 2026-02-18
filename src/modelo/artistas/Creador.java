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

    /** * Devuelve el ID único del creador. */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /** * Obtiene el nombre público del canal de podcasts. */
    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    /** * Obtiene el nombre real o artístico del creador. */
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** * Devuelve la lista de todos los podcasts subidos por este creador. */
    public ArrayList<Podcast> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(ArrayList<Podcast> episodios) {
        this.episodios = episodios;
    }

    /** * Indica cuántas personas están suscritas al canal. */
    public int getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(int suscriptores) {
        this.suscriptores = suscriptores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Entrega una copia de los enlaces a redes sociales (Instagram, Twitter, etc.).
     */
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

    /** * Indica la cantidad de episodios publicados actualmente. */
    public int getNumEpisodios(){
        return this.episodios.size();
    }

    // Métodos

    /**
     * Sube un nuevo podcast al canal. Verifica que no se pase del límite de 500 episodios.
     */
    public void publicarPodcast(Podcast episodio) throws LimiteEpisodiosException {
        if (this.episodios.size() >= Max_Episodios){
            throw new LimiteEpisodiosException("El creador ha alcanzado el límite máximo de " + Max_Episodios + " episodios.");
        }
        // Vinculamos el episodio con este creador específico
        episodio.setCreador(this);
        this.episodios.add(episodio);
        System.out.println("Episodio " + episodio.getTitulo() + " publicado con éxito.");
    }

    /** * Crea un objeto de estadísticas procesando todos los datos del creador. */
    public EstadisticasCreador obtenerEstadisticas(){
        return new EstadisticasCreador(this);
    }

    /** * Guarda o actualiza un perfil de red social en el mapa. */
    public void agregarRedSocial(String plataforma, String usuario){
        this.redesSociales.put(plataforma.toLowerCase(), usuario);
    }

    /**
     * Suma todas las visitas y las divide por el número de episodios.
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
     * Busca un episodio por su ID y lo borra de la lista.
     */
    public void eliminarEpisodio(String idEpisodio) throws EpisodioNoEncontradoException{
        boolean eliminado = episodios.removeIf(p -> p.getId().equals(idEpisodio));

        if (!eliminado) {
            throw new EpisodioNoEncontradoException("No se encontró el episodio con ID: " + idEpisodio);
        }
    }

    /**
     * Suma las reproducciones de cada uno de los episodios del canal.
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
     * Ordena los episodios por éxito y devuelve una lista con los mejores.
     */
    public ArrayList<Podcast> obtenerTopEpisodios(int cantidad){
        ArrayList<Podcast> copiaEpisodios = new ArrayList<>(this.episodios);
        // Ordenamos de mayor a menor según reproducciones
        copiaEpisodios.sort((p1, p2) -> Integer.compare(p2.getReproducciones(), p1.getReproducciones()));

        int limite = Math.min(cantidad, copiaEpisodios.size());
        return new ArrayList<>(copiaEpisodios.subList(0, limite));
    }

    /**
     * Busca entre todos los episodios cuál es el número de temporada más alto.
     */
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
        return "Creador: " + nombreCanal + " (Canal de " + nombre + ")";
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