package excepciones.contenido;

public class ArchivoAudioNoEncontradoException extends Exception{
    public ArchivoAudioNoEncontradoException(){
        super("Archivo no encontrado.");
    }
    public ArchivoAudioNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
