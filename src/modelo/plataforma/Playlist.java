package modelo.plataforma;

import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import java.util.UUID;
import java.util.*;

/**
 * Esta clase sirve para gestionar listas de canciones o podcasts.
 * Permite añadir contenido, ordenarlo y controlar quién la sigue.
 */
public class Playlist {

    // Atributos
    private String id;
    private String nombre;
    private Usuario creador;
    private ArrayList<Contenido> contenidos;
    private boolean esPublica;
    private int seguidores;
    private String descripcion;
    private String portadaURL;
    private Date fechaCreacion;
    private int maxContenidos;
    private static final int MAX_CONTENIDOS_DEFAULT = 500;

    // Constructores

    public Playlist(String nombre, Usuario creador, boolean esPublica, String descripcion) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
        this.contenidos = new ArrayList<>();
        this.seguidores = 0;
        this.fechaCreacion = new Date();
        this.maxContenidos = MAX_CONTENIDOS_DEFAULT;
    }

    public Playlist(String nombre, Usuario creador){
        this(nombre, creador, true, " ");
    }

    // Métodos

    /**
     * Añade un tema a la lista si hay sitio y si no está ya repetido.
     */
    public void agregarContenido(Contenido contenido) throws PlaylistLlenaException, ContenidoDuplicadoException {
        // Verifica que no pasemos del límite de 500 canciones
        if (this.contenidos.size() >= MAX_CONTENIDOS_DEFAULT) {
            throw new PlaylistLlenaException("Playlist llena: " + this.nombre);
        }
        // Verifica que la canción no esté ya en la lista
        if (this.contenidos.contains(contenido)) {
            throw new ContenidoDuplicadoException("El contenido " + contenido.getTitulo() + " ya existe.");
        }
        this.contenidos.add(contenido);
    }

    public boolean eliminarContenido(String idContenido) {
        return this.contenidos.removeIf(c -> c.getId().equals(idContenido));
    }

    public boolean eliminarContenido(Contenido contenido) {
        return this.contenidos.remove(contenido);
    }

    /**
     * Cambia el orden de la lista según lo que prefiera el usuario.
     */
    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException {
        if (estaVacia()) throw new PlaylistVaciaException("No se puede ordenar una playlist vacía.");

        switch (criterio) {
            case DURACION -> contenidos.sort(Comparator.comparingInt(Contenido::getDuracionSegundos));
            case POPULARIDAD -> contenidos.sort(Comparator.comparingInt(Contenido::getReproducciones).reversed());
        }
    }

    /**
     * Suma los segundos de todo el contenido para saber cuánto dura la playlist completa.
     */
    public int getDuracionTotal() {
        int sumaDuracion = 0;
        for (Contenido c : contenidos) {
            sumaDuracion += c.getDuracionSegundos();
        }
        return sumaDuracion;
    }

    public String getDuracionTotalFormateada() {
        return "";
    }

    /**
     * Mezcla los contenidos de forma aleatoria.
     */
    public void shuffle() {
        Collections.shuffle(this.contenidos);
    }

    public ArrayList<Contenido> buscarContenido(String palabra) {
        return new ArrayList<>();
    }

    public void hacerPublica() {
        this.esPublica = true;
    }

    public void hacerPrivada() {
        this.esPublica = false;
    }

    /**
     * Suma un nuevo seguidor al contador de la playlist.
     */
    public void incrementarSeguidores() {
        this.seguidores++;
    }

    /**
     * Resta un seguidor, asegurándose de no bajar de cero.
     */
    public void decrementarSeguidores() {
        if (this.seguidores > 0) this.seguidores--;
    }

    /**
     * Indica cuántos elementos hay guardados en total.
     */
    public int getNumContenidos() {
        return this.contenidos.size();
    }

    public boolean estaVacia() {
        return this.contenidos.isEmpty();
    }

    /**
     * Obtiene el contenido que esté en una posición específica de la lista.
     */
    public Contenido getContenido(int posicion) {
        if (posicion < 0 || posicion >= contenidos.size()) return null;
        return contenidos.get(posicion);
    }

    // Getters and Setters

    public ArrayList<Contenido> getContenidos() {
        return new ArrayList<>(this.contenidos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el usuario que creó la lista.
     */
    public Usuario getCreador() {
        return creador;
    }

    /**
     * Indica si la playlist es visible para otros usuarios.
     */
    public boolean isEsPublica() {
        return esPublica;
    }

    public void setEsPublica(boolean esPublica) {
        this.esPublica = esPublica;
    }

    /**
     * Devuelve el número de personas que siguen esta lista.
     */
    public int getSeguidores() {
        return seguidores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPortadaURL() {
        return portadaURL;
    }

    public void setPortadaURL(String portadaURL) {
        this.portadaURL = portadaURL;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public int getMaxContenidos() {
        return maxContenidos;
    }

}