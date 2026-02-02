package excepciones.descarga;

public class ContenidoYaDescargadoException extends Exception{

    public ContenidoYaDescargadoException(){
        super("Contenido ya descargado");
    }
    public ContenidoYaDescargadoException(String mensaje){
        super(mensaje);
    }
}
