package enums;

public enum AlgoritmoRecomendacion {
    COLABORATIVO,
    CONTENIDO,
    HIBRIDO;

    private String descripcion;

    // Constructores
    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }
    //getter and setter
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodos
    public String getDescripcion() {
        return descripcion;
    }

}








