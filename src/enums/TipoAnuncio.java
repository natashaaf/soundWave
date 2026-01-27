package enums;

public enum TipoAnuncio {
    AUDIO,
    BANNER,
    VIDEO;

    private int duracionSegundos;
    private double costoPorImpresion;

    //construtor vacio
    TipoAnuncio(){
    }
    //getter and setter
    public int getDuracionSegundos(){
        return duracionSegundos;
    }

    public void setDuracionSegundos(int duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }

    public double getCostoPorImpresion() {
        return costoPorImpresion;
    }

    public void setCostoPorImpresion(double costoPorImpresion) {
        this.costoPorImpresion = costoPorImpresion;
    }
}
