package excepciones.usuario;

public class AnuncioRequeridoException extends Exception {

    public AnuncioRequeridoException(){
        super("Anuncio requerido");
    }
    public AnuncioRequeridoException(String mensaje){
        super(mensaje);
    }
}
