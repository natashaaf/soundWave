package enums;

public enum AlgoritmoRecomendacion {

    COLABORATIVO("Basado en usuarios similares"),
    CONTENIDO("Basado en características del contenido"),
    HIBRIDO("Combinación de ambos");

    //Atributos
    private String descripcion;

    // Constructores
    AlgoritmoRecomendacion(String descripcion) {
        this.descripcion = descripcion;
    }

    //Setter
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Metodos
    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "AlgoritmoRecomendacion{" +
                "descripcion='" + descripcion + '\'' +
                '}';
    }
}
