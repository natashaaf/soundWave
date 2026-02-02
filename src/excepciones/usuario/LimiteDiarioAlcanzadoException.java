package excepciones.usuario;

public class LimiteDiarioAlcanzadoException extends Exception{

    public LimiteDiarioAlcanzadoException(){
        super("Limite diario alcanzado");
    }
    public LimiteDiarioAlcanzadoException(String mensaje){
        super(mensaje);
    }
}
