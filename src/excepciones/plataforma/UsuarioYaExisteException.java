package excepciones.plataforma;

public class UsuarioYaExisteException extends Exception{
    public UsuarioYaExisteException(){
        super("Usuario ya existe.");
    }
    public UsuarioYaExisteException(String mensaje){
        super(mensaje);
    }
}
