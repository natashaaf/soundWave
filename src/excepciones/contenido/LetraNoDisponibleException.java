package excepciones.contenido;


public class LetraNoDisponibleException extends Exception {
    public LetraNoDisponibleException(){
        super("Letra no disponible.");
    }
    public LetraNoDisponibleException(String mensaje){
        super(mensaje);
    }

}
