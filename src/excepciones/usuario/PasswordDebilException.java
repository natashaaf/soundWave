package excepciones.usuario;

public class PasswordDebilException extends Exception{
    public PasswordDebilException(){
        super("Contraseña débil.");
    }
    public PasswordDebilException(String mensaje){
        super(mensaje);
    }
}
