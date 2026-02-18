package utilidades;

import enums.AlgoritmoRecomendacion;
import excepciones.recomendacion.HistorialVacioException;
import excepciones.recomendacion.ModeloNoEntrenadoException;
import excepciones.recomendacion.RecomendacionException;
import interfaces.Recomendador;
import modelo.usuarios.Usuario;
import modelo.contenido.Contenido;

import java.util.*;

/**
 * Esta clase Analiza los gustos de los usuarios para recomendar música o podcasts nuevos.
 */
public class RecomendadorIA implements Recomendador {

    // Atributos privados
    private HashMap<String, ArrayList<String>> matrizPreferencias;
    private HashMap<String, ArrayList<Contenido>> historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;
    private ArrayList<Contenido> catalogoReferencia;

    // Constante
    private static final double UMBRAL_DEFAULT = 0.6;

    // Constructores

    public RecomendadorIA() {
        this(AlgoritmoRecomendacion.CONTENIDO);
    }

    public RecomendadorIA(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
        this.umbralSimilitud = UMBRAL_DEFAULT;
        this.matrizPreferencias = new HashMap<>();
        this.historialCompleto = new HashMap<>();
        this.catalogoReferencia = new ArrayList<>();
        this.modeloEntrenado = false;
    }

    // Getters and Setters

    public AlgoritmoRecomendacion getAlgoritmo() {
        return algoritmo;
    }
    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) {
        this.algoritmo = algoritmo;
    }

    public double getUmbralSimilitud() {
        return umbralSimilitud;
    }
    public void setUmbralSimilitud(
            double umbralSimilitud) {
        this.umbralSimilitud = umbralSimilitud;
    }

    /**
     * Informa si la IA ya procesó los datos y está lista para recomendar.
     * */
    public boolean isModeloEntrenado() {
        return modeloEntrenado;
    }

    /**
     *  Entrega una copia de los gustos almacenados de todos los usuarios.
     *  */
    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        return new HashMap<>(this.matrizPreferencias);
    }

    public void setCatalogoReferencia(ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
    }

    // Implementación de interfaz

    /**
     * Busca en el catálogo cosas que le gusten al usuario pero que no haya escuchado aún.
     */
    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        // Primero revisamos si la IA aprendió y si el usuario tiene historial
        if (!modeloEntrenado) {
            throw new ModeloNoEntrenadoException ("El modelo de IA no ha sido entrenado.");
        }
        if (usuario.getHistorial().isEmpty()) {
            throw new HistorialVacioException("El usuario no tiene historial suficiente para recomendar.");
        }

        ArrayList<Contenido> recomendaciones = new ArrayList<>();
        ArrayList<String> preferencias = matrizPreferencias.get(usuario.getId());

        if (preferencias != null) {
            for (Contenido contenido : catalogoReferencia) {
                // Solo recomendamos cosas que el usuario NO haya escuchado antes
                if (!usuario.getHistorial().contains(contenido)) {
                    if (calcularSimilitudContenido(contenido, preferencias) >= umbralSimilitud) {
                        recomendaciones.add(contenido);
                    }
                }
            }
        }
        return recomendaciones;
    }

    /**
     * Crea una lista de contenidos que tengan el mismo género que el original.
     */
    @Override
    public ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException {
        ArrayList<Contenido> similares = new ArrayList<>();
        String generoReferencia = contenido.getGenero().toString();

        for (Contenido c : catalogoReferencia) {
            // Buscamos coincidencias de género sin incluir el mismo archivo
            if (!c.equals(contenido) && c.getGenero().toString().equals(generoReferencia)) {
                similares.add(c);
            }
        }
        return similares;
    }

    // Lógica de entrenamiento

    /**
     * Hace que la IA aprenda analizando qué géneros escucha cada usuario.
     */
    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
        for (Usuario u : usuarios) {
            actualizarPreferencias(u);
            historialCompleto.put(u.getId(), u.getHistorial());
        }
        this.modeloEntrenado = true;
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios) {
        entrenarModelo(usuarios, this.catalogoReferencia);
    }

    // Métodos de análisis

    /**
     * Compara los gustos de dos usuarios para ver qué tanto se parecen.
     */
    public double calcularSimilitud(Usuario u1, Usuario u2) {
        ArrayList<String> p1 = matrizPreferencias.get(u1.getId());
        ArrayList<String> p2 = matrizPreferencias.get(u2.getId());

        if (p1 == null || p2 == null) return 0.0;

        // Calcula cuántos géneros tienen en común respecto al total
        long coincidencias = p1.stream().filter(p2::contains).count();
        return (double) coincidencias / Math.max(p1.size(), p2.size());
    }

    /**
     * Revisa el historial de un usuario para saber qué géneros prefiere.
     */
    public void actualizarPreferencias(Usuario usuario) {
        ArrayList<String> generosVistos = new ArrayList<>();
        for (Contenido c : usuario.getHistorial()) {
            String g = c.getGenero().toString();
            if (!generosVistos.contains(g)) {
                generosVistos.add(g);
            }
        }
        matrizPreferencias.put(usuario.getId(), generosVistos);
    }

    /**
     * Identifica cuáles son los géneros más escuchados por toda la comunidad.
     */
    public HashMap<String, Integer> obtenerGenerosPopulares() {
        HashMap<String, Integer> populares = new HashMap<>();
        for (ArrayList<String> prefs : matrizPreferencias.values()) {
            for (String g : prefs) {
                populares.put(g, populares.getOrDefault(g, 0) + 1);
            }
        }
        return populares;
    }

    // Métodos internos

    /**
     * Verifica si el género de una canción encaja con los gustos del usuario.
     */
    private double calcularSimilitudContenido(Contenido contenido, ArrayList<String> preferencias) {
        return preferencias.contains(contenido.getGenero().toString()) ? 1.0 : 0.0;
    }
}