package enums;

public enum AlgoritmoRecomendacion {
    COLABORATIVO,
    CONTENIDO,
    HIBRIDO;

    private String descripcion;

    //Construtor
    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }
    //getter and setter
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    //metodo
    public String getDescripcion() {
        return descripcion;
    }

}








