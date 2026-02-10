package utilidades;

import enums.AlgoritmoRecomendacion;
import modelo.contenido.Cancion;
import modelo.contenido.Contenido;
import modelo.usuarios.Usuario;
import modelo.usuarios.UsuarioPremium;

import java.util.ArrayList;
import java.util.HashMap;

public class RecomendadorIA {
    private HashMap matrizPreferencias;
    private HashMap historialCompleto;
    private AlgoritmoRecomendacion algoritmo;
    private double umbralSimilitud;
    private boolean modeloEntrenado;

    public void entrenarModelo(ArrayList<Usuario> usuarios, ArrayList<Contenido> catalogo) {
    }

    public boolean isModeloEntrenado() {
        return  true;
    }

    public ArrayList<Contenido> recomendar(UsuarioPremium userConHistorial) {
        return new ArrayList<>();
    }

    public ArrayList<Contenido> obtenerSimilares(Cancion cancion) {
    }

    //construtor

}
