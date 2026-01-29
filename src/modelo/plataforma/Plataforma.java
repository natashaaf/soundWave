package modelo.plataforma;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.contenido.Podcast;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;
import utilidades.RecomendadorIA;

import java.util.HashMap;
import java.util.List;

public class Plataforma {

    // Atributos
    private static Plataforma instancia;
    private String nombre;
    private RecomendadorIA recomendador;

    private List<UsuarioPremium> usuarioPremiumList;
    private List<UsuarioGratuito> usuarioGratuitoList;
    private List<Contenido> catalogoList;
    private List<Cancion> cancionesList;
    private List<Podcast> podcastsList;
    private List<Playlist> playlistsPublicasList;
    private List<Album> albumesList;
    private List<Anuncio> anuncioList;

    private HashMap<Artista, String> artistaHashMap;
    private HashMap<Creador, String> creadorHashMap;

    // Contructores
    public Plataforma(String nombre, RecomendadorIA recomendador, List<UsuarioPremium> usuarioPremiumList, List<UsuarioGratuito> usuarioGratuitoList, List<Contenido> catalogoList, List<Cancion> cancionesList, List<Podcast> podcastsList, List<Playlist> playlistsPublicasList, List<Album> albumesList, List<Anuncio> anuncioList, HashMap<Artista, String> artistaHashMap, HashMap<Creador, String> creadorHashMap) {
        this.nombre = nombre;
        this.recomendador = recomendador;
        this.usuarioPremiumList = usuarioPremiumList;
        this.usuarioGratuitoList = usuarioGratuitoList;
        this.catalogoList = catalogoList;
        this.cancionesList = cancionesList;
        this.podcastsList = podcastsList;
        this.playlistsPublicasList = playlistsPublicasList;
        this.albumesList = albumesList;
        this.anuncioList = anuncioList;
        this.artistaHashMap = artistaHashMap;
        this.creadorHashMap = creadorHashMap;
    }

    // Getters and setters
    public List<Contenido> getCatalogoList() {
        return catalogoList;
    }

    public void setCatalogoList(List<Contenido> catalogoList) {
        this.catalogoList = catalogoList;
    }

    public static Plataforma getInstancia(String nombre) {
        return instancia;
    }

    public static void setInstancia(Plataforma instancia) {
        Plataforma.instancia = instancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RecomendadorIA getRecomendador() {
        return recomendador;
    }

    public void setRecomendador(RecomendadorIA recomendador) {
        this.recomendador = recomendador;
    }

    public List<UsuarioPremium> getUsuarioPremiumList() {
        return usuarioPremiumList;
    }

    public void setUsuarioPremiumList(List<UsuarioPremium> usuarioPremiumList) {
        this.usuarioPremiumList = usuarioPremiumList;
    }

    public List<UsuarioGratuito> getUsuarioGratuitoList() {
        return usuarioGratuitoList;
    }

    public void setUsuarioGratuitoList(List<UsuarioGratuito> usuarioGratuitoList) {
        this.usuarioGratuitoList = usuarioGratuitoList;
    }

    public List<Cancion> getCancionesList() {
        return cancionesList;
    }

    public void setCancionesList(List<Cancion> cancionesList) {
        this.cancionesList = cancionesList;
    }

    public List<Podcast> getPodcastsList() {
        return podcastsList;
    }

    public void setPodcastsList(List<Podcast> podcastsList) {
        this.podcastsList = podcastsList;
    }

    public List<Playlist> getPlaylistsPublicasList() {
        return playlistsPublicasList;
    }

    public void setPlaylistsPublicasList(List<Playlist> playlistsPublicasList) {
        this.playlistsPublicasList = playlistsPublicasList;
    }

    public List<Album> getAlbumesList() {
        return albumesList;
    }

    public void setAlbumesList(List<Album> albumesList) {
        this.albumesList = albumesList;
    }

    public List<Anuncio> getAnuncioList() {
        return anuncioList;
    }

    public void setAnuncioList(List<Anuncio> anuncioList) {
        this.anuncioList = anuncioList;
    }

    public HashMap<Artista, String> getArtistaHashMap() {
        return artistaHashMap;
    }

    public void setArtistaHashMap(HashMap<Artista, String> artistaHashMap) {
        this.artistaHashMap = artistaHashMap;
    }

    public HashMap<Creador, String> getCreadorHashMap() {
        return creadorHashMap;
    }

    public void setCreadorHashMap(HashMap<Creador, String> creadorHashMap) {
        this.creadorHashMap = creadorHashMap;
    }

    public String getIntancia(String nombre) {
        return nombre;
    }

    ;

    // Metodo del patron Singleton
    public static Plataforma getInstancia(String nombre) {
        if (instancia == null) {
        }
        return instancia;
    }

    public String getInstancia() {
        return "SoundWave";
    }

    public void reiniciaInstancia() {}

    // Metodo de registro de usuarios
    // Metodo de getion de artistas
    // Metodo de gestion de contenido
    // Metodo de busqueda
    // Metodo de playlists
    // Metodo de anuncios
    // Metodo de estadísticas y utilidades
    // Excepciones a lanzar
    // Caracteristicas del patron Singleton implementado
}
