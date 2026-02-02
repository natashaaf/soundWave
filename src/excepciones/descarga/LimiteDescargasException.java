package excepciones.descarga;

public class LimiteDescargasException extends Exception {

    public LimiteDescargasException(){
        super("Limite de descargas alcanzado.");
    }
    public LimiteDescargasException(String mensaje){
        super(mensaje);
    }
}

