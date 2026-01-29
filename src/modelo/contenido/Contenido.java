package modelo.contenido;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Contenido {

    //Atributos
    private String id;
    private String titulo;
    private int reproducciones;
    private int likes;
    private int duracionSegundos;
    private List<String> tags;
    private boolean disponible;
    private LocalDateTime fechaPublicacion;

    // Constructores

    // Metodos
    public abstract void reproducir();

    public void aumentarReproducciones(){
        this.reproducciones++;

    }
    public void agregarLike(){
        this.likes++;

    }
    public boolean esPopular(){
        //TODO
        return reproducciones > 5000;

    }
    public void validarDuracion(){
    }

}
