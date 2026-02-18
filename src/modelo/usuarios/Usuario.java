package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Playlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * Clase abstracta que define la estructura base para los usuarios del sistema.
 * Gestiona el perfil, las bibliotecas personales y el historial de reproducción.
 */
public abstract class Usuario {

    // Atributos protegidos
    protected String id;
    protected String nombre;
    protected String email;
    protected String password;
    protected TipoSuscripcion suscripcion;
    protected ArrayList<Playlist> misPlaylists;
    protected ArrayList<Contenido> historial;
    protected Date fechaRegistro;
    protected ArrayList<Playlist> playlistsSeguidas;
    protected ArrayList<Contenido> contenidosLiked;

    private static final int MAX_HISTORIAL = 100;

    // Constructores

    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion)
            throws EmailInvalidoException, PasswordDebilException {

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
        this.fechaRegistro = new Date();

        // Validación de credenciales al crear la instancia
        validarEmail();
        validarPassword();

        // Inicialización de las listas de datos
        this.misPlaylists = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.playlistsSeguidas = new ArrayList<>();
        this.contenidosLiked = new ArrayList<>();
    }

    // Getters and setters

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    /**
     * Actualiza el correo. Si el nuevo formato es inválido, se restaura el valor anterior.
     */
    public void setEmail(String email) throws EmailInvalidoException {
        String emailAnterior = this.email;
        this.email = email;
        try {
            validarEmail();
        } catch (EmailInvalidoException e) {
            this.email = emailAnterior;
            throw e;
        }
    }

    public String getPassword() {
        return password;
    }

    /**
     * Cambia la contraseña, pero primero revisa que cumpla con los requisitos mínimos.
     */
    public void setPassword(String password) throws PasswordDebilException {
        String passwordAnterior = this.password;
        this.password = password;
        try {
            validarPassword();
        } catch (PasswordDebilException e) {
            this.password = passwordAnterior;
            throw e;
        }
    }

    public TipoSuscripcion getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(TipoSuscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Retorna una copia de las listas de reproducción para evitar modificaciones externas.
     */
    public ArrayList<Playlist> getMisPlaylists() {
        return new ArrayList<>(this.misPlaylists);
    }

    /**
     * Retorna una copia del historial para proteger la integridad de los datos originales.
     */
    public ArrayList<Contenido> getHistorial() {
        return new ArrayList<>(this.historial);
    }

    /**
     * Retorna una copia de las listas de reproducción seguidas.
     */
    public ArrayList<Playlist> getPlaylistsSeguidas() {
        return new ArrayList<>(this.playlistsSeguidas);
    }

    public ArrayList<Contenido> getContenidosLiked() {
        return new ArrayList<>(this.contenidosLiked);
    }

    // Método abstracto

    /**
     * Método que debe ser implementado por las subclases según el tipo de suscripción.
     */
    public abstract void reproducir(Contenido contenido)
            throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;

    // Métodos (lógica)

    /**
     * Crea una nueva lista de reproducción y la añade a la colección del usuario.
     */
    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist nueva = new Playlist(nombrePlaylist, this, false, "Creada por " + this.nombre);
        this.misPlaylists.add(nueva);
        return nueva;
    }

    /**
     * Añade una playlist pública a la lista de seguimiento e incrementa su contador.
     */
    public void seguirPlaylist(Playlist playlist) {
        if (playlist != null && playlist.isEsPublica() && !playlistsSeguidas.contains(playlist)) {
            this.playlistsSeguidas.add(playlist);
            playlist.incrementarSeguidores();
        }
    }

    public void dejarDeSeguirPlaylist(Playlist playlist) {
        if (this.playlistsSeguidas.remove(playlist)) {
            playlist.decrementarSeguidores();
        }
    }

    public void darLike(Contenido contenido) {
        if (contenido != null && !this.contenidosLiked.contains(contenido)) {
            this.contenidosLiked.add(contenido);
        }
    }

    public void quitarLike(Contenido contenido) {
        this.contenidosLiked.remove(contenido);
    }

    /**
     * Verifica que el formato del correo cumpla con los estándares.
     */
    public boolean validarEmail() throws EmailInvalidoException {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (email == null || email.isEmpty()  || !email.matches(regex)) {
            throw new EmailInvalidoException();
        }
        return true;
    }

    /**
     * Valida que la contraseña tenga la longitud mínima requerida por seguridad.
     */
    public boolean validarPassword() throws PasswordDebilException {
        if (this.password == null || this.password.length() < 8) {
            throw new PasswordDebilException("Password débil (mínimo 8 caracteres).");
        }
        return true;
    }

    /**
     * Añade un elemento al historial. Si se alcanza el límite, elimina el registro más antiguo.
     */
    public void agregarAlHistorial(Contenido contenido) {
        if (this.historial.size() >= MAX_HISTORIAL) {
            this.historial.remove(0);
        }
        this.historial.add(contenido);
    }

    public void limpiarHistorial() {
        this.historial.clear();
    }

    public boolean esPremium() {
        return this.suscripcion != TipoSuscripcion.GRATUITO;
    }

    // Overrides

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (" + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}