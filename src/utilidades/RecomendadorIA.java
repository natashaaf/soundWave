package utilidades;

import enums.AlgoritmoRecomendacion;
import excepciones.recomendacion.RecomendacionException;
import interfaces.Recomendador;
import modelo.usuarios.Usuario;
import modelo.contenido.Contenido;

import java.util.*;

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

    // --- CONSTRUCTORES ---

    public RecomendadorIA() {
        // Usa 'CONTENIDO' que es el nombre real en tu Enum AlgoritmoRecomendacion
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

    // --- IMPLEMENTACIÓN DE RECOMENDADOR (Overrides) ---

    @Override
    public ArrayList<Contenido> recomendar(Usuario usuario) throws RecomendacionException {
        if (!modeloEntrenado) {
            throw new RecomendacionException("El modelo de IA no ha sido entrenado.");
        }
        if (usuario.getHistorial().isEmpty()) {
            throw new RecomendacionException("El usuario no tiene historial suficiente para recomendar.");
        }

        ArrayList<Contenido> recomendaciones = new ArrayList<>();
        ArrayList<String> preferencias = matrizPreferencias.get(usuario.getId());

        if (preferencias != null) {
            for (Contenido contenido : catalogoReferencia) {
                // No recomendar algo que ya está en su historial
                if (!usuario.getHistorial().contains(contenido)) {
                    if (calcularSimilitudContenido(contenido, preferencias) >= umbralSimilitud) {
                        recomendaciones.add(contenido);
                    }
                }
            }
        }
        return recomendaciones;
    }

    @Override
    public ArrayList<Contenido> obtenerSimilares(Contenido contenido) throws RecomendacionException {
        ArrayList<Contenido> similares = new ArrayList<>();
        // El método getGenero() ahora es visible porque está en la clase Contenido
        String generoReferencia = contenido.getGenero();

        for (Contenido c : catalogoReferencia) {
            if (!c.equals(contenido) && c.getGenero().equalsIgnoreCase(generoReferencia)) {
                similares.add(c);
            }
        }
        return similares;
    }

    // --- MÉTODOS PROPIOS ---

    public void entrenarModelo(ArrayList<Usuario> usuarios) {
        entrenarModelo(usuarios, this.catalogoReferencia);
    }

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
        for (Usuario u : usuarios) {
            actualizarPreferencias(u);
            historialCompleto.put(u.getId(), u.getHistorial());
        }
        this.modeloEntrenado = true;
    }

    public double calcularSimilitud(Usuario u1, Usuario u2) {
        ArrayList<String> p1 = matrizPreferencias.get(u1.getId());
        ArrayList<String> p2 = matrizPreferencias.get(u2.getId());

        if (p1 == null || p2 == null) return 0.0;

        long coincidencias = p1.stream().filter(p2::contains).count();
        return (double) coincidencias / Math.max(p1.size(), p2.size());
    }

    public void actualizarPreferencias(Usuario usuario) {
        ArrayList<String> generosVistos = new ArrayList<>();
        for (Contenido c : usuario.getHistorial()) {
            String g = c.getGenero();
            if (!generosVistos.contains(g)) {
                generosVistos.add(g);
            }
        }
        matrizPreferencias.put(usuario.getId(), generosVistos);
    }

    public HashMap<String, Integer> obtenerGenerosPopulares() {
        HashMap<String, Integer> populares = new HashMap<>();
        for (ArrayList<String> prefs : matrizPreferencias.values()) {
            for (String g : prefs) {
                populares.put(g, populares.getOrDefault(g, 0) + 1);
            }
        }
        return populares;
    }

    // --- MÉTODO PRIVADO ---

    private double calcularSimilitudContenido(Contenido contenido, ArrayList<String> preferencias) {
        // Si el género del contenido está en la lista de gustos, similitud máxima (1.0)
        return preferencias.contains(contenido.getGenero()) ? 1.0 : 0.0;
    }

    // --- GETTERS Y SETTERS ---

    public AlgoritmoRecomendacion getAlgoritmo() { return algoritmo; }
    public void setAlgoritmo(AlgoritmoRecomendacion algoritmo) { this.algoritmo = algoritmo; }

    public double getUmbralSimilitud() { return umbralSimilitud; }
    public void setUmbralSimilitud(double umbralSimilitud) { this.umbralSimilitud = umbralSimilitud; }

    public boolean isModeloEntrenado() { return modeloEntrenado; }

    public HashMap<String, ArrayList<String>> getMatrizPreferencias() {
        // Copia defensiva
        return new HashMap<>(this.matrizPreferencias);
    }

    public void setCatalogoReferencia(ArrayList<Contenido> catalogo) {
        this.catalogoReferencia = new ArrayList<>(catalogo);
    }
}