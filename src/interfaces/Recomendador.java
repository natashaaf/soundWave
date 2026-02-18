package interfaces;

import excepciones.recomendacion.ModeloNoEntrenadoException;
import excepciones.recomendacion.RecomendacionException;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import java.util.List;

public interface Recomendador {
    List<Contenido> recomendar (Usuario usuario) throws RecomendacionException, ModeloNoEntrenadoException;
    List<Contenido> obtenerSimilares (Contenido contenido) throws RecomendacionException;
}
