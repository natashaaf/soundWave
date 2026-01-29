package modelo.plataforma;
import enums.CriterioOrden;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import java.time.LocalDateTime;
import java.util.List;

public class Playlist {

    // Atributos
    private String id;
    private String nombre;
    private Usuario creador;
    private List<Contenido> contenidosList;
    private boolean esPublica;
    private int seguidores;
    private String descripcion;
    private String portadaURL;
    private LocalDateTime fechaCreacion;
    private int maxContenidos;

    // Constructores
    public Playlist(String id, String nombre, Usuario creador, List<Contenido> contenidosList, boolean esPublica, int seguidores, String descripcion, String portadaURL, LocalDateTime fechaCreacion, int maxContenidos) {
        this.id = id;
        this.nombre = nombre;
        this.creador = creador;
        this.contenidosList = contenidosList;
        this.esPublica = esPublica;
        this.seguidores = seguidores;
        this.descripcion = descripcion;
        this.portadaURL = portadaURL;
        this.fechaCreacion = fechaCreacion;
        this.maxContenidos = maxContenidos;
    }
    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public List<Contenido> getContenidosList() {
        return contenidosList;
    }

    public void setContenidosList(List<Contenido> contenidosList) {
        this.contenidosList = contenidosList;
    }

    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

    public void setMaxContenidos(int maxContenidos) {
        this.maxContenidos = maxContenidos;
    }
    public int getDuracionTotal(){
        this.getDuracionTotal();
        return 0;
        //TODO
    }

    // Metodos
    public void agregarContenido (Contenido contenido){}
    public boolean eliminarContenido(String idContenido){
        //TODO
        return false;
    }
    public void ordernarPor(CriterioOrden criterio){}
    public void shuffle(){}
    public List<Contenido> buscarContenido (String termino){
        return contenidosList;
        //TODO
    }
    public void hacerPublica(){}
    public void hacerPrivada(){}

}
