package modelo.artistas;

import enums.CategoriaPodcast;
import modelo.contenido.Podcast;
import utilidades.EstadisticasCreador;

import java.util.HashMap;
import java.util.List;

public class Creador {

    // Atributos
    private String id;
    private String nombreCanal;
    private String nombre;
    private List<Podcast> episodiosList;
    private int suscriptores;
    private String descripcion;
    private HashMap<String, String> redesociales;
    private List<CategoriaPodcast>categoriaPrincipales;

    // Constructores
    public Creador(String id, String nombreCanal, List<Podcast> episodiosList, String nombre, int suscriptores, String descripcion, HashMap<String, String> redesociales, List<CategoriaPodcast> categoriaPrincipales) {
        this.id = id;
        this.nombreCanal = nombreCanal;
        this.episodiosList = episodiosList;
        this.nombre = nombre;
        this.suscriptores = suscriptores;
        this.descripcion = descripcion;
        this.redesociales = redesociales;
        this.categoriaPrincipales = categoriaPrincipales;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreCanal() {
        return nombreCanal;
    }

    public void setNombreCanal(String nombreCanal) {
        this.nombreCanal = nombreCanal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Podcast> getEpisodiosList() {
        return episodiosList;
    }

    public void setEpisodiosList(List<Podcast> episodiosList) {
        this.episodiosList = episodiosList;
    }

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

    public HashMap<String, String> getRedesociales() {
        return redesociales;
    }

    public void setRedesociales(HashMap<String, String> redesociales) {
        this.redesociales = redesociales;
    }

    public List<CategoriaPodcast> getCategoriaPrincipales() {
        return categoriaPrincipales;
    }

    public void setCategoriaPrincipales(List<CategoriaPodcast> categoriaPrincipales) {
        this.categoriaPrincipales = categoriaPrincipales;
    }

    // Metodos
    public void publicarPodcast(Podcast episodio){}
    public EstadisticasCreador obtenerEstadisticas(){
        //TODO
        return null;
    }
    public void agregarRedSocial(String red, String usuario){}
    public double calcularPromedioReproducciones(){
        //TODO
        return 0;
    }
    public void eliminarEpisodio(String idEpisodio){}
}
