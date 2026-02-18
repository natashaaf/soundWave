package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import excepciones.descarga.LimiteDescargasException;
import excepciones.descarga.ContenidoYaDescargadoException;
import modelo.contenido.Contenido;

import java.util.ArrayList;

/**
 * Esta clase define a los usuarios Premium.
 * A diferencia de los gratuitos, estos pueden descargar música y no tienen anuncios.
 */
public class UsuarioPremium extends Usuario {

    // Atributos privados
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    // Constante
    private static final int MAX_DESCARGAS_DEFAULT = 100;

    // Constructores

    public UsuarioPremium(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        this(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion)
            throws EmailInvalidoException, PasswordDebilException {
        // Llamamos al padre y configuramos las opciones por defecto del plan Premium
        super(nombre, email, password, suscripcion);
        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "Alta (320kbps)";
    }

    // Getters and setters

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    public ArrayList<Contenido> getDescargados() {
        return new ArrayList<>(this.descargados);
    }

    /** Indica cuántos elementos hay guardados actualmente en la lista de descargas.*/
    public int getNumDescargados() {
        return this.descargados.size();
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }

    // Métodos

    /**
     * Intenta bajar una canción.
     * Revisa que no se pase del límite de 100 y que la canción no esté ya descargada.
     */
    public void descargar(Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException {
        // Primero mira si queda espacio en el límite de descargas
        if (!verificarEspacioDescarga()) {
            throw new LimiteDescargasException("Has alcanzado el límite máximo de " + maxDescargas + " descargas.");
        }
        // Luego mira si el contenido ya estaba en la lista de descargados
        if (descargados.contains(contenido)) {
            throw new ContenidoYaDescargadoException("Este contenido ya se encuentra disponible offline.");
        }

        // Si todo está bien, lo añade a la lista
        this.descargados.add(contenido);
        System.out.println("Contenido descargado exitosamente: " + contenido.getTitulo());
    }

    public boolean eliminarDescarga(Contenido contenido) {
        return this.descargados.remove(contenido);
    }

    /**
     * Compara si la cantidad de canciones bajadas es menor al límite permitido.
     */
    public boolean verificarEspacioDescarga() {
        return this.descargados.size() < this.maxDescargas;
    }

    public int getDescargasRestantes() {
        return Math.max(0, maxDescargas - descargados.size());
    }

    public void cambiarCalidadAudio(String calidad) {}

    public void limpiarDescargas() {
        this.descargados.clear();
    }

    // Overrides

    /**
     * Reproduce música en alta calidad.
     * Como es Premium, no necesita anuncios ni tiene límites de tiempo.
     */
    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException {
        // Mira si el contenido está activo en la plataforma
        if (contenido == null || !contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible para reproducción.");
        }

        // Suena la música directamente y se guarda en el historial
        System.out.println("Reproduciendo en alta calidad (" + calidadAudio + "): " + contenido.getTitulo());
        this.agregarAlHistorial(contenido);
    }

    @Override
    public String toString() {
        return super.toString() + " [PREMIUM - Calidad: " + calidadAudio + " - Descargas: " + getNumDescargados() + "]";
    }
}