package modelo.usuarios;

public class UsuarioPremium {
    private boolean descargasOffline;
    private int maxDescargas;
    private List<Contenido> descargados;
    private String calidadAudio;

    //metodos
    public reproducir (Contenido contenido);

    public descargar (Contenido contenido);
    public eliminarDescarga(Contenido contenido);
    public verificarEspacioDescarga();

    public LimiteDescargasException;
}
