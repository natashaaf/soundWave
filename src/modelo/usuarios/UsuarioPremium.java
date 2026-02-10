package modelo.usuarios;
import enums.TipoSuscripcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UsuarioPremium extends Usuario {
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;


    // Construtor
    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion, String calidadAudio, ArrayList<Contenido> descargados, int maxDescargas, boolean descargasOffline) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.calidadAudio = calidadAudio;
        this.descargados = descargados;
        this.maxDescargas = maxDescargas;
        this.descargasOffline = descargasOffline;
    }

    //metodos
    @Override
    public void reproducir(Contenido contenido) {
        //TODO
    }

    public void descargar (Contenido contenido){
        //TODO
    }
    public void eliminarDescarga(Contenido contenido){
        //TODO
    }
    public boolean verificarEspacioDescarga(){
        //TODO
        return false;
    }

}
