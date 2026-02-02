package excepciones.contenido;

public class LetraNoDisponible extends Exception {
    public LetraNoDisponible(){
        super("Letra no disponible.");
    }
    public LetraNoDisponible(String mensaje){
        super(mensaje);
    }

}
