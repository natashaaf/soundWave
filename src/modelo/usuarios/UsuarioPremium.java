package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import excepciones.descarga.LimiteDescargasException;
import excepciones.descarga.ContenidoYaDescargadoException;
import modelo.contenido.Contenido;

import java.util.ArrayList;

public class UsuarioPremium extends Usuario {

    // Atributos privados
    private boolean descargasOffline;
    private int maxDescargas;
    private ArrayList<Contenido> descargados;
    private String calidadAudio;

    // Constante
    private static final int MAX_DESCARGAS_DEFAULT = 100;

    // --- CONSTRUCTORES ---

    public UsuarioPremium(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        this(nombre, email, password, TipoSuscripcion.PREMIUM);
    }

    public UsuarioPremium(String nombre, String email, String password, TipoSuscripcion suscripcion)
            throws EmailInvalidoException, PasswordDebilException {
        super(nombre, email, password, suscripcion);
        this.descargasOffline = true;
        this.maxDescargas = MAX_DESCARGAS_DEFAULT;
        this.descargados = new ArrayList<>();
        this.calidadAudio = "Alta (320kbps)";
    }

    // --- GETTERS Y SETTERS ---

    public boolean isDescargasOffline() {
        return descargasOffline;
    }

    public void setDescargasOffline(boolean descargasOffline) {
        this.descargasOffline = descargasOffline;
    }

    public int getMaxDescargas() {
        return maxDescargas;
    }

    // --- MÉTODOS PROPIOS ---

    public void descargar(Contenido contenido) throws LimiteDescargasException, ContenidoYaDescargadoException {
        if (!verificarEspacioDescarga()) {
            throw new LimiteDescargasException("Has alcanzado el límite máximo de " + maxDescargas + " descargas.");
        }
        if (descargados.contains(contenido)) {
            throw new ContenidoYaDescargadoException("Este contenido ya se encuentra disponible offline.");
        }

        this.descargados.add(contenido);
        System.out.println("Contenido descargado exitosamente: " + contenido.getTitulo());
    }

    public boolean eliminarDescarga(Contenido contenido) {
        return this.descargados.remove(contenido);
    }

    public boolean verificarEspacioDescarga() {
        return this.descargados.size() < this.maxDescargas;
    }

    public int getDescargasRestantes() {
        return Math.max(0, maxDescargas - descargados.size());
    }

    public void cambiarCalidadAudio(String calidad) {
        // Lógica de validación de calidad (ejemplo simple)
        if (calidad != null && !calidad.isBlank()) {
            this.calidadAudio = calidad;
            System.out.println("Calidad de audio actualizada a: " + calidad);
        }
    }

    public void limpiarDescargas() {
        this.descargados.clear();
    }

    // Copia defensiva de la lista de descargas
    public ArrayList<Contenido> getDescargados() {
        return new ArrayList<>(this.descargados);
    }

    public int getNumDescargados() {
        return this.descargados.size();
    }

    public String getCalidadAudio() {
        return calidadAudio;
    }

    public void setCalidadAudio(String calidadAudio) {
        this.calidadAudio = calidadAudio;
    }
    // --- OVERRIDES DE LÓGICA ---

    @Override
    public void reproducir(Contenido contenido) throws ContenidoNoDisponibleException {
        // Validación básica de disponibilidad
        if (contenido == null || !contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible para reproducción.");
        }

        // Lógica Premium: Sin anuncios y sin límites diarios
        System.out.println("Reproduciendo en alta calidad (" + calidadAudio + "): " + contenido.getTitulo());
        this.agregarAlHistorial(contenido);
        // Nota: No lanza LimiteDiarioAlcanzadoException ni AnuncioRequeridoException
    }

    // --- OVERRIDES ---

    @Override
    public String toString() {
        return super.toString() + " [PREMIUM - Calidad: " + calidadAudio + " - Descargas: " + getNumDescargados() + "]";
    }
}