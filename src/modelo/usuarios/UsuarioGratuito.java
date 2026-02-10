package modelo.usuarios;

import enums.TipoSuscripcion;
import excepciones.contenido.ContenidoNoDisponibleException;
import excepciones.usuario.AnuncioRequeridoException;
import excepciones.usuario.EmailInvalidoException;
import excepciones.usuario.LimiteDiarioAlcanzadoException;
import excepciones.usuario.PasswordDebilException;
import modelo.contenido.Contenido;
import modelo.plataforma.Anuncio; // Asumiendo que existe esta clase

import java.util.Date;

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

    // --- CONSTRUCTOR ---
    public UsuarioGratuito(String nombre, String email, String password)
            throws EmailInvalidoException, PasswordDebilException {
        // Llamada al constructor padre con el tipo FREE/GRATUITO
        super(nombre, email, password, TipoSuscripcion.GRATUITO);

        this.limiteReproducciones = LIMITE_DIARIO;
        this.reproduccionesHoy = 0;
        this.cancionesSinAnuncio = 0;
        this.anunciosEscuchados = 0;
    }

    // --- OVERRIDES DE LÓGICA ---

    @Override
    public void reproducir(Contenido contenido)
            throws ContenidoNoDisponibleException, LimiteDiarioAlcanzadoException, AnuncioRequeridoException {

        // 1. Validar disponibilidad básica
        if (contenido == null || !contenido.isDisponible()) {
            throw new ContenidoNoDisponibleException("El contenido no está disponible.");
        }

        // 2. Validar límite diario
        if (!puedeReproducir()) {
            throw new LimiteDiarioAlcanzadoException("Has alcanzado tu límite de 50 canciones por hoy.");
        }

        // 3. Validar si toca anuncio
        if (debeVerAnuncio()) {
            throw new AnuncioRequeridoException("Es necesario ver un anuncio para continuar.");
        }

        // Si pasa las validaciones, se "reproduce"
        System.out.println("Reproduciendo: " + contenido.getTitulo());
        this.reproduccionesHoy++;
        this.cancionesSinAnuncio++;
        this.fechaUltimaReproduccion = new Date();
        this.agregarAlHistorial(contenido);
    }

    // --- MÉTODOS PROPIOS ---

    public void verAnuncio() {
        System.out.println("Reproduciendo anuncio aleatorio...");
        this.anunciosEscuchados++;
        this.ultimoAnuncio = new Date();
        this.cancionesSinAnuncio = 0; // Reinicia el contador de canciones entre anuncios
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

    public boolean puedeReproducir() {
        return this.reproduccionesHoy < this.limiteReproducciones;
    }

    public boolean debeVerAnuncio() {
        return this.cancionesSinAnuncio >= CANCIONES_ENTRE_ANUNCIOS;
    }

    public void reiniciarContadorDiario() {
        this.reproduccionesHoy = 0;
    }

    public int getReproduccionesRestantes() {
        return Math.max(0, limiteReproducciones - reproduccionesHoy);
    }

    public int getCancionesHastaAnuncio() {
        return Math.max(0, CANCIONES_ENTRE_ANUNCIOS - cancionesSinAnuncio);
    }

    // --- GETTERS Y SETTERS ---

    public int getAnunciosEscuchados() {
        return anunciosEscuchados;
    }

    public Date getUltimoAnuncio() {
        return ultimoAnuncio;
    }

    public int getReproduccionesHoy() {
        return reproduccionesHoy;
    }

    public void setReproduccionesHoy(int reproduccionesHoy) {
        this.reproduccionesHoy = reproduccionesHoy;
    }

    public int getLimiteReproducciones() {
        return limiteReproducciones;
    }

    public int getCancionesSinAnuncio() {
        return cancionesSinAnuncio;
    }

    public void setCancionesSinAnuncio(int cancionesSinAnuncio) {
        this.cancionesSinAnuncio = cancionesSinAnuncio;
    }

    // --- OVERRIDES ---

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