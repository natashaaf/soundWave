package modelo.plataforma;

import enums.CategoriaPodcast;
import enums.GeneroMusical;
import enums.TipoSuscripcion;
import excepciones.artista.AlbumCompletoException;
import excepciones.artista.AlbumYaExisteException;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.artista.LimiteEpisodiosException;
import excepciones.contenido.DuracionInvalidaException;
import excepciones.plataforma.ArtistaNoEncontradoException;
import excepciones.plataforma.ContenidoNoEncontradoException;
import excepciones.plataforma.UsuarioYaExisteException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.PasswordDebilException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;
import utilidades.RecomendadorIA;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Plataforma {

    // Atributos (private)
    private static Plataforma instancia;
    private String nombre;
    private HashMap<String, Usuario> usuarios;
    private HashMap<String, Usuario> usuariosPorEmail;
    private ArrayList<Contenido> catalogo;
    private ArrayList<Playlist> playlistsPublicas;
    private HashMap<String, Artista> artistas;
    private HashMap<String, Creador> creadores;
    private ArrayList<Album> albumes;
    private ArrayList<Anuncio> anuncios;
    private RecomendadorIA recomendador;
    private int totalAnunciosReproducidos;

    // Constructor (private)
    private Plataforma(String nombre) {
        // TODO: Inicializa colecciones y anuncios, y configura el recomendador.
    }

    // Métodos Singleton
    public static synchronized Plataforma getInstancia(String nombre) {
        return null;
    }

    public static synchronized Plataforma getInstancia() {
        return null;
    }

    public static synchronized void reiniciarInstancia() {
        // TODO: Reinicia la instancia (útil para pruebas).
    }

    // Gestión de usuarios
    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password, TipoSuscripcion tipo)
            throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public UsuarioPremium registrarUsuarioPremium(String nombre, String email, String password)
            throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public UsuarioGratuito registrarUsuarioGratuito(String nombre, String email, String password)
            throws UsuarioYaExisteException, EmailInvalidoException, PasswordDebilException {
        return null;
    }

    public ArrayList<UsuarioPremium> getUsuariosPremium() {
        return null;
    }

    public ArrayList<UsuarioGratuito> getUsuariosGratuitos() {
        return null;
    }

    public ArrayList<Usuario> getTodosLosUsuarios() {
        return null;
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return null;
    }

    // Gestión de artistas
    public Artista registrarArtista(String nombreArtistico, String nombreReal, String paisOrigen, boolean verificado) {
        return null;
    }

    public void registrarArtista(Artista artista) {
        // TODO: Registra artista existente.
    }

    public ArrayList<Artista> getArtistasVerificados() {
        return null;
    }

    public ArrayList<Artista> getArtistasNoVerificados() {
        return null;
    }

    public Artista buscarArtista(String nombre) throws ArtistaNoEncontradoException {
        return null;
    }

    // Gestión de álbumes
    public Album crearAlbum(Artista artista, String titulo, Date fecha)
            throws ArtistaNoVerificadoException, AlbumYaExisteException {
        return null;
    }

    public ArrayList<Album> getAlbumes() {
        return null;
    }

    // Gestión de canciones
    public Cancion crearCancion(String titulo, int duracion, Artista artista, GeneroMusical genero)
            throws DuracionInvalidaException {
        return null;
    }

    public Cancion crearCancionEnAlbum(String titulo, int duracion, Artista artista, GeneroMusical genero, Album album)
            throws DuracionInvalidaException, AlbumCompletoException {
        return null;
    }

    public void agregarContenidoCatalogo(Contenido contenido) {
        // TODO: Agrega contenido al catálogo.
    }

    public ArrayList<Cancion> getCanciones() {
        return null;
    }

    // Gestión de creadores/podcasts
    public Creador registrarCreador(String nombreCanal, String nombre, String descripcion) {
        return null;
    }

    public void registrarCreador(Creador creador) {
        // TODO: Registra creador existente.
    }

    public Podcast crearPodcast(String titulo, int duracion, Creador creador, int numEpisodio, int temporada,
            CategoriaPodcast categoria) throws DuracionInvalidaException, LimiteEpisodiosException {
        return null;
    }

    public ArrayList<Podcast> getPodcasts() {
        return null;
    }

    public ArrayList<Creador> getTodosLosCreadores() {
        return null;
    }

    // Gestión de playlists públicas
    public Playlist crearPlaylistPublica(String nombre, Usuario creador) {
        return null;
    }

    public ArrayList<Playlist> getPlaylistsPublicas() {
        return null;
    }

    // Búsquedas
    public ArrayList<Contenido> buscarContenido(String termino) throws ContenidoNoEncontradoException {
        return null;
    }

    public ArrayList<Cancion> buscarPorGenero(GeneroMusical genero) throws ContenidoNoEncontradoException {
        return null;
    }

    public ArrayList<Podcast> buscarPorCategoria(CategoriaPodcast categoria) throws ContenidoNoEncontradoException {
        return null;
    }

    public ArrayList<Contenido> obtenerTopContenidos(int cantidad) {
        return null;
    }

    // Anuncios
    public Anuncio obtenerAnuncioAleatorio() {
        return null;
    }

    public void incrementarAnunciosReproducidos() {
        // TODO: Incrementa el contador de anuncios reproducidos.
    }

    // Estadísticas
    public String obtenerEstadisticasGenerales() {
        return null;
    }

    // Getters básicos
    public String getNombre() {
        return null;
    }

    public ArrayList<Contenido> getCatalogo() {
        return null;
    }

    public HashMap<String, Artista> getArtistas() {
        return null;
    }

    public HashMap<String, Creador> getCreadores() {
        return null;
    }

    public ArrayList<Anuncio> getAnuncios() {
        return null;
    }

    public RecomendadorIA getRecomendador() {
        return null;
    }

    public int getTotalUsuarios() {
        return 0;
    }

    public int getTotalContenido() {
        return 0;
    }

    public int getTotalAnunciosReproducidos() {
        return 0;
    }

    // Overrides
    @Override
    public String toString() {
        return null;
    }
}
