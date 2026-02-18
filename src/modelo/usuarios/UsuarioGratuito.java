package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Anuncio;

import java.util.Date;

/**
 * Esta clase es para los usuarios gratuitos.
 * Tienen límites de canciones por día y tienen que escuchar anuncios cada cierto tiempo.
 */
public class UsuarioGratuito extends Usuario {

    // Atributos privados
    private int anunciosEscuchados;
    private Date ultimoAnuncio;
    private int reproduccionesHoy;
    private int limiteReproducciones;
    private int cancionesSinAnuncio;
    private Date fechaUltimaReproduccion;

    // Constantes
    private static final int LIMITE_DIARIO = 50;
    private static final int CANCIONES_ENTRE_ANUNCIOS = 3;

    // Constructores

    public UsuarioGratuito(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        // Llamamos al constructor del padre y le decimos que la suscripción es gratuita
        super(nombre, email, password, TipoSuscripcion.GRATUITO);

        this.limiteReproducciones = LIMITE_DIARIO;
        this.reproduccionesHoy = 0;
        this.cancionesSinAnuncio = 0;
        this.anunciosEscuchados = 0;
    }

    // Overrides de lógica

    /**
     * Intenta reproducir una canción o podcast revisando si el usuario todavía
     * tiene permitido escuchar música hoy o si le toca publicidad.
     */
    @Override
    public void reproducir(Contenido contenido)
            throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {

        // Primero revisamos si el contenido existe y está disponible
        if (contenido == null || !contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible.");
        }

        // Si ya escuchó las 50 canciones del día, no puede seguir
        if (!puedeReproducir()) {
            throw new LimiteDiarioAlcanzadoException("Has alcanzado tu límite de 50 canciones por hoy.");
        }

        // Si ya pasaron 3 canciones sin publicidad, tiene que ver un anuncio
        if (debeVerAnuncio()) {
            throw new AnuncioRequeridoException("Es necesario ver un anuncio para continuar.");
        }

        // Si todo está bien, sumamos la reproducción y lo guardamos en el historial
        System.out.println("Reproduciendo: " + contenido.getTitulo());
        this.reproduccionesHoy++;
        this.cancionesSinAnuncio++;
        this.fechaUltimaReproduccion = new Date();
        this.agregarAlHistorial(contenido);
    }

    // Métodos propios

    /**
     * Simula que el usuario ve un anuncio y pone a cero el contador de canciones.
     */
    public void verAnuncio() {
        System.out.println("Reproduciendo anuncio aleatorio...");
        this.anunciosEscuchados++;
        this.ultimoAnuncio = new Date();
        this.cancionesSinAnuncio = 0; // Se reinicia para que pueda escuchar otras 3 canciones
    }

    public void verAnuncio(Anuncio anuncio) {
        if (anuncio == null) {
            verAnuncio();
        } else {
            System.out.println("Reproduciendo anuncio: ");
            this.anunciosEscuchados++;
            this.ultimoAnuncio = new Date();
            this.cancionesSinAnuncio = 0;
        }
    }

    /**
     * Comprueba si el usuario todavía no ha llegado al máximo de 50 canciones.
     */
    public boolean puedeReproducir() {
        return this.reproduccionesHoy < this.limiteReproducciones;
    }

    /**
     * Revisa si el contador de canciones sin publicidad llegó al límite (3).
     */
    public boolean debeVerAnuncio() {
        return this.cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS;
    }

    /**
     * Pone en cero el contador de reproducciones diarias.
     */
    public void reiniciarContadorDiario() {
        this.reproduccionesHoy = 0;
    }

    /**
     * Nos dice cuántas canciones le quedan antes de que se bloquee la reproducción por hoy.
     */
    public int getReproduccionesRestantes() {
        return Math.max(0, limiteReproducciones - reproduccionesHoy);
    }

    public int getCancionesHastaAnuncio() {
        return Math.max(0, CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio);
    }

    // Getters and setters

    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    /**
     * Indica cuántas canciones lleva escuchadas en el día de hoy.
     */
    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    /**
     * Indica cuántas canciones lleva escuchadas desde el último anuncio.
     */
    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    // Overrides

    @Override
    public String toString() {
        return super.toString() + " [Gratuito - Restantes: " + getReproduccionesRestantes() + "]";
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UsuarioGratuito usuarioGratuito = (UsuarioGratuito) obj;
        return id.equals(usuarioGratuito.id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();
    }
}