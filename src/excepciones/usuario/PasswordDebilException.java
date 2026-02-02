package excepciones.usuario;

public class PasswordDebilException extends Exception{

    public PasswordDebilException(){
        super("Contraseña debil");
    }
    public PasswordDebilException(String mensaje){
        super(mensaje);
    }
}
