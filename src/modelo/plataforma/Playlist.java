package modelo.plataforma;
import enums.CriterioOrden;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistLlenaException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.*;

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
        this.nombre = nombre;
        this.creador = creador;
        this.esPublica = esPublica;
        this.descripcion = descripcion;
    }

    public Playlist(String nombre, Usuario creador){
        this(nombre, creador, true, " ");
    }
    // Getters and setters


    // Métodos
    // --- MÉTODOS SOLICITADOS ---

    public void agregarContenido(Contenido contenido) throws PlaylistLlenaException, ContenidoDuplicadoException {
        if (this.contenidos.size() >= MAX_CONTENIDOS_DEFAULT) {
            throw new PlaylistLlenaException("Playlist llena: " + this.nombre);
        }
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

    public void ordenarPor(CriterioOrden criterio) throws PlaylistVaciaException {
        if (estaVacia()) throw new PlaylistVaciaException("No se puede ordenar una playlist vacía.");

        switch (criterio) {
            // Ordena los contenidos de menor a mayor duración en segundos
            case DURACION -> contenidos.sort(Comparator.comparingInt(Contenido::getDuracionSegundos));
            // Ordena los contenidos basándose en el número de reproducciones acumuladas
            case POPULARIDAD -> contenidos.sort(Comparator.comparingInt(Contenido::getReproducciones));
        }
    }

    public int getDuracionTotal() {
        int sumaDuracion = 0;
        for (Contenido c : contenidos) {
            sumaDuracion += c.getDuracionSegundos();
        }
        return sumaDuracion;
    }

    public String getDuracionTotalFormateada() {
        int total = getDuracionTotal();
        int h = total / 3600;
        int m = (total % 3600) / 60;
        int s = total % 60;
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public void shuffle() {
        Collections.shuffle(this.contenidos);
    }

    public ArrayList<Contenido> buscarContenido(String termino) {
        ArrayList<Contenido> resultados = new ArrayList<>();
        for (Contenido c : contenidos) {
            if (c.getTitulo().toLowerCase().contains(termino.toLowerCase())) {
                resultados.add(c);
            }
        }
        return resultados;
    }

    public void hacerPublica() { this.esPublica = true; }

    public void hacerPrivada() { this.esPublica = false; }

    public void incrementarSeguidores() { this.seguidores++; }

    public void decrementarSeguidores() {
        if (this.seguidores > 0) this.seguidores--;
    }

    public int getNumContenidos() { return this.contenidos.size(); }

    public boolean estaVacia() { return this.contenidos.isEmpty(); }

    public Contenido getContenido(int posicion) {
        if (posicion < 0 || posicion >= contenidos.size()) return null;
        return contenidos.get(posicion);
    }

    // --- GETTERS (Con copia defensiva) ---

    public ArrayList<Contenido> getContenidos() {
        return new ArrayList<>(this.contenidos); // Copia defensiva
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Usuario getCreador() { return creador; }
    public boolean isEsPublica() { return esPublica; }
    public void setEsPublica(boolean esPublica) { this.esPublica = esPublica; }
    public int getSeguidores() { return seguidores; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getPortadaURL() { return portadaURL; }
    public void setPortadaURL(String portadaURL) { this.portadaURL = portadaURL; }
    public Date getFechaCreacion() { return fechaCreacion; }
    public int getMaxContenidos() { return maxContenidos; }

}
