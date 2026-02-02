package enums;

public enum CriterioOrden {
    FECHA_AGREGADO("Fecha de agregado", "Ordena por fecha en que se agregó"),
    POPULARIDAD("Popularidad", "Ordena por número de reproducciones"),
    DURACION("Duración", "Ordena por duración del contenido"),
    ALFABETICO("Alfabético", "Ordena alfabéticamente por título"),
    ARTISTA("Artista", "Ordena por nombre del artista"),
    ALEATORIO("Aleatorio", "Orden aleatorio");

    //Atributos
    private String nombre;
    private String descripcion;

    //Constructores

    CriterioOrden(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }
    //Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Métodos
    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "CriterioOrden{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
