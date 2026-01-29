package interfaces;

import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.List;

public interface Recomendador {
    List<Contenido> recomendar (Usuario usuario);
    List<Contenido> obtenerSimilares (Contenido contenido);
}
