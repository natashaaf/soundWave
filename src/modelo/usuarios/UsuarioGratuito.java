package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;

import java.time.LocalDateTime;
import java.util.Date;

public class UsuarioGratuito extends Usuario {
    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int cancionesSinAnuncio;

    //construtor

    public UsuarioGratuito(String nombre, String email, String password, TipoSuscripcion suscripcion, int anunciosEscuchados, Date ultimoAnuncio, int reproduccionesHoy, int cancionesSinAnuncio) throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.anunciosEscuchados = anunciosEscuchados;
        this.ultimoAnuncio = ultimoAnuncio;
        this.reproduccionesHoy = reproduccionesHoy;
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    //getter and setter
    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public void setAnunciosEscuchados(int anunciosEscuchados) {
        this.anunciosEscuchados = anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public void setUltimoAnuncio(Date ultimoAnuncio) {
        this.ultimoAnuncio = ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    //metodos

    public void verAnuncio() {

    }

    public boolean puedeReproducir() {
        // TODO;
        return false;
    }

    public void reiniciarContadorDiario() {

    }

    @Override
    public void reproducir(Contenido contenido) {

    }

}
