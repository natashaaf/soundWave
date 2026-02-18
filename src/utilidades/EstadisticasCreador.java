package utilidades;

import modelo.artistas.Creador;
import modelo.contenido.Podcast;
import java.util.HashMap;

/**
 * Esta clase se encarga de procesar y organizar todos los números de un creador.
 * Sirve para saber cuántas reproducciones tiene, qué episodio gusta más y sacar promedios.
 */
public class EstadisticasCreador {

    // Atributos privados
    private Creador creador;
    private int totalEpisodios;
    private int totalReproducciones;
    private double promedioReproducciones;
    private int totalSuscriptores;
    private int totalLikes;
    private int duracionTotalSegundos;
    private Podcast episodioMasPopular;
    private HashMap<Integer, Integer> episodiosPorTemporada;

    // Constructores

    public EstadisticasCreador(Creador creador) {
        this.creador = creador;
        this.episodiosPorTemporada = new HashMap<>();
        // Al crear el objeto, calculamos todo de una vez para tener los datos listos
        calcularEstadisticas();
    }

    // Getters and setters

    /** Entrega el objeto del creador para consultar sus datos.*/
    public Creador getCreador() {
        return creador;
    }

    /** Devuelve la cantidad de episodios que ha subido el creador.*/
    public int getTotalEpisodios() {
        return totalEpisodios;
    }

    /** Devuelve la suma de todas las visitas de todos sus podcasts.*/
    public int getTotalReproducciones() {
        return totalReproducciones;
    }

    public double getPromedioReproducciones() {
        return promedioReproducciones;
    }

    public int getTotalSuscriptores() {
        return totalSuscriptores;
    }

    public int getTotalLikes() {
        return totalLikes;
    }

    public int getDuracionTotalSegundos() {
        return duracionTotalSegundos;
    }

    /** Devuelve el podcast que tiene el número más alto de reproducciones.*/
    public Podcast getEpisodioMasPopular() {
        return episodioMasPopular;
    }

    public HashMap<Integer, Integer> getEpisodiosPorTemporada() {
        return new HashMap<>(episodiosPorTemporada);
    }

    // Métodos privados

    /**
     * Recorre la lista de episodios y suma likes, reproducciones y busca el más popular.
     */
    private void calcularEstadisticas() {
        if (creador == null || creador.getEpisodios() == null) {
            return;
        }

        this.totalEpisodios = creador.getEpisodios().size();
        this.totalSuscriptores = creador.getSuscriptores();
        this.totalReproducciones = 0;
        this.totalLikes = 0;
        this.duracionTotalSegundos = 0;
        this.episodiosPorTemporada = new HashMap<>();

        Podcast popular = null;

        for (Podcast p : creador.getEpisodios()) {
            this.totalReproducciones += p.getReproducciones();
            this.totalLikes += p.getLikes();
            this.duracionTotalSegundos += p.getDuracionSegundos();

            // Guardamos cuántos episodios hay por cada número de temporadas
            int temp = p.getTemporada();
            this.episodiosPorTemporada.put(temp, this.episodiosPorTemporada.getOrDefault(temp, 0) + 1);

            // Si el podcast actual tiene más visitas que el que teníamos guardado, lo reemplazamos
            if (popular == null || p.getReproducciones() > popular.getReproducciones()) {
                popular = p;
            }
        }
        this.episodioMasPopular = popular;

        // Si hay episodios, dividimos el total de visitas entre el número de episodios
        if (this.totalEpisodios > 0) {
            this.promedioReproducciones = (double) this.totalReproducciones / this.totalEpisodios;
        } else {
            this.promedioReproducciones = 0.0;
        }
    }

    /**
     * Pasa los segundos a un formato que se entienda mejor (Horas:Minutos:Segundos).
     */
    private String formatearDuracion(int segundos) {
        int horas = segundos / 3600;
        int minutos = (segundos % 3600) / 60;
        int segs = segundos % 60;
        return String.format("%02d:%02d:%02d", horas, minutos, segs);
    }

    // Métodos públicos

    /**
     * Junta todos los datos calculados en un solo texto para poder mostrarlo por consola.
     */
    public String generarReporte() {
        StringBuilder sb = new StringBuilder();
        sb.append("Estadísticas del Creador: ").append(creador != null ? creador.getNombre() : "N/A").append("\n");
        sb.append("Total Episodios: ").append(totalEpisodios).append("\n");
        sb.append("Total Reproducciones: ").append(totalReproducciones).append("\n");
        sb.append("Promedio Reproducciones: ").append(String.format("%.2f", promedioReproducciones)).append("\n");
        sb.append("Total Suscriptores: ").append(totalSuscriptores).append("\n");
        sb.append("Total Likes: ").append(totalLikes).append("\n");
        sb.append("Duración Total: ").append(formatearDuracion(duracionTotalSegundos)).append("\n");
        sb.append("Episodio Más Popular: ").append(episodioMasPopular != null ? episodioMasPopular.getTitulo() : "N/A").append("\n");
        sb.append("Episodios Por Temporada: ").append(episodiosPorTemporada).append("\n");
        sb.append("Engagement: ").append(String.format("%.2f", calcularEngagement())).append("%\n");
        sb.append("Crecimiento Mensual Estimado: ").append(estimarCrecimientoMensual()).append("\n");
        sb.append("Nombre del canal: ").append(creador.getNombreCanal().toUpperCase());
        return sb.toString();
    }

    /**
     * Calcula qué tan activa es la gente. Es la relación entre los Likes y las Visitas.
     */
    public double calcularEngagement() {
        if (totalReproducciones == 0) return 0.0;
        return ((double) totalLikes / totalReproducciones) * 100;
    }

    /**
     * Hace una estimación de cuántos usuarios nuevos ganará el creador basándose en el 5% actual.
     */
    public int estimarCrecimientoMensual() {
        return (int) (totalSuscriptores * 0.05);
    }

    // Overrides

    @Override
    public String toString() {
        return "EstadisticasCreador{" +
                "creador=" + (creador != null ? creador.getNombre() : "N/A") +
                ", totalEpisodios=" + totalEpisodios +
                ", totalReproducciones=" + totalReproducciones +
                ", promedioRP=" + promedioReproducciones +
                ", totalSuscriptores=" + totalSuscriptores +
                ", totalLikes=" + totalLikes +
                ", duracionTotal=" + duracionTotalSegundos +
                '}';
    }
}