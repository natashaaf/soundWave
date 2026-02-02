package interfaces;

import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;

import java.util.List;

public interface Recomendador {
    List<Contenido> recomendar (Usuario usuario) throws RecomendacionException;
    List<Contenido> obtenerSimilares (Contenido contenido) throws RecomendacionException;
}
