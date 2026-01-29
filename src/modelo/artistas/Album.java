package modelo.artistas;
import enums.GeneroMusical;
import modelo.contenido.Cancion;
import java.time.LocalDateTime;
import java.util.List;

public class Album {

    // Atributos
    private String id;
    private String titulo;
    private Artista artista;
    private LocalDateTime fechaLanzamiento;
    private List<Cancion> cancionList;
    private String portadaURL;
    private String discografica;
    private String tipoAlbum;


    // Constructores
    public Album(String id, String titulo, LocalDateTime fechaLanzamiento, Artista artista, List<Cancion> cancionList, String portadaURL, String discografica, String tipoAlbum) {
        this.id = id;
        this.titulo = titulo;
        this.fechaLanzamiento = fechaLanzamiento;
        this.artista = artista;
        this.cancionList = cancionList;
        this.portadaURL = portadaURL;
        this.discografica = discografica;
        this.tipoAlbum = tipoAlbum;
    }

    // Getters and setters
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

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    public LocalDateTime getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDateTime fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public List<Cancion> getCancionList() {
        return cancionList;
    }

    public void setCancionList(List<Cancion> cancionList) {
        this.cancionList = cancionList;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public String getDiscografica() {
        return discografica;
    }

    public void setDiscografica(String discografica) {
        this.discografica = discografica;
    }

    public String getTipoAlbum() {
        return tipoAlbum;
    }

    public void setTipoAlbum(String tipoAlbum) {
        this.tipoAlbum = tipoAlbum;
    }
    public int getDuracionTotal(){
        this.getDuracionTotal();
        return 0;
        //TODO
    }
    public int getNumCanciones(Cancion cancion){
        return 0;
        //TODO
    }

    // Metodos
    public Album crearCancion (String titulo, int duracion, GeneroMusical genero){
        //TODO
        return null;
    }
    public Cancion agregarCancion(Cancion cancion){
        //TODO
        return cancion;
    }
    public Cancion eliminarCancion(int posicion){
        //TODO
        return null;
    }
    public void ordenarPorPopularidad(){}

}

