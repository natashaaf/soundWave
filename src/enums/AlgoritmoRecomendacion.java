package enums;

public enum AlgoritmoRecomendacion {
    COLABORATIVO,
    CONTENIDO,
    HIBRIDO;

    private String descripcion;

    //Construtor en Enum hay que ser vacio
    AlgoritmoRecomendacion() {
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








