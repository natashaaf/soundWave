package excepciones.usuario;

public class EmailInvalidoException extends Exception{

   public EmailInvalidoException(){
        super("Email invalido");
    }

    public EmailInvalidoException(String mensaje) {
        super(mensaje);
    }
}
