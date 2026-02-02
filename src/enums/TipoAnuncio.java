package enums;

public enum TipoAnuncio {
    AUDIO(15, 0.05),
    BANNER(0, 0.02),
    VIDEO(30, 0.10);

    private int duracionSegundos;
    private double costoPorImpresion;


    // Constructores

    TipoAnuncio(int duracionSegundos, double costoPorImpresion) {
        this.duracionSegundos = duracionSegundos;
        this.costoPorImpresion = costoPorImpresion;

    }

    //Setters
    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
    public void setCostoPorImpresion(double costoPorImpresion) {
        this.costoPorImpresion = costoPorImpresion;
    }

    //Métodos
    public int getDuracionSegundos(){
        return duracionSegundos;
    }
    public double getCostoPorImpresion() {
        return costoPorImpresion;
    }

    @Override
    public String toString() {
        return "TipoAnuncio{" +
                ", duracionSegundos=" + duracionSegundos +
                ", costoPorImpresion=" + costoPorImpresion +
                '}';
    }
}
