package excepciones.recomendacion;

public class HistorialVacioException extends RecomendacionException{
    public HistorialVacioException(){
        super("Historial vacío.");
    }
    public HistorialVacioException(String mensaje){
        super(mensaje);
    }
}
