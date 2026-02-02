package excepciones.recomendacion;

public class HistorialVacioException extends Exception{
    public HistorialVacioException(){
        super("Historial vacío.");
    }
    public HistorialVacioException(String mensaje){
        super(mensaje);
    }
}
