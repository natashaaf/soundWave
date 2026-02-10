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

    // --- CONSTRUCTOR ---
    public Usuario(String nombre, String email, String password, TipoSuscripcion suscripcion)
            throws EmailInvalidoException, PasswordDebilException {

        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.suscripcion = suscripcion;
        this.fechaRegistro = new Date();

        // Validaciones iniciales
        validarEmail();
        validarPassword();

        // Inicialización de listas
        this.misPlaylists = new ArrayList<>();
        this.historial = new ArrayList<>();
        this.playlistsSeguidas = new ArrayList<>();
        this.contenidosLiked = new ArrayList<>();
    }

    // --- GETTERS AND SETTERS ---

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

    public void setEmail(String email) throws EmailInvalidoException {
        String emailAnterior = this.email;
        this.email = email;
        try {
            validarEmail();
        } catch (EmailInvalidoException e) {
            this.email = emailAnterior; // Revertimos si falla
            throw e;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws PasswordDebilException {
        String passwordAnterior = this.password;
        this.password = password;
        try {
            validarPassword();
        } catch (PasswordDebilException e) {
            this.password = passwordAnterior; // Revertimos si falla
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

    // Getters con Copia Defensiva para proteger las listas
    public ArrayList<Playlist> getMisPlaylists() {
        return new ArrayList<>(this.misPlaylists);
    }

    public ArrayList<Contenido> getHistorial() {
        return new ArrayList<>(this.historial);
    }

    public ArrayList<Playlist> getPlaylistsSeguidas() {
        return new ArrayList<>(this.playlistsSeguidas);
    }

    public ArrayList<Contenido> getContenidosLiked() {
        return new ArrayList<>(this.contenidosLiked);
    }

    // --- MÉTODO ABSTRACTO ---
    public abstract void reproducir(Contenido contenido)
            throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException;

    // --- MÉTODOS CONCRETOS (Lógica) ---

    public Playlist crearPlaylist(String nombrePlaylist) {
        Playlist nueva = new Playlist(nombrePlaylist, this, false, "Creada por " + this.nombre);
        this.misPlaylists.add(nueva);
        return nueva;
    }

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

    public boolean validarEmail() throws EmailInvalidoException {
        if (this.email == null || !this.email.contains("@")) {
            throw new EmailInvalidoException("Email no válido: " + this.email);
        }
        return true;
    }

    public boolean validarPassword() throws PasswordDebilException {
        if (this.password == null || this.password.length() < 8) {
            throw new PasswordDebilException("Password débil (mínimo 8 caracteres).");
        }
        return true;
    }

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
        // Asegúrate de que TipoSuscripcion.FREE exista en tu Enum
        return this.suscripcion != TipoSuscripcion.GRATUITO;
    }

    // --- OVERRIDES ---

    @Override
    public String toString() {
        return "Usuario: " + nombre + " (" + email + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(id, usuario.id) || Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email);
    }
}