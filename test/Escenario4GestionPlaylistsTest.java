import enums.CategoriaPodcast;
import enums.CriterioOrden;
import enums.GeneroMusical;
import enums.TipoSuscripcion;
import excepciones.artista.ArtistaNoVerificadoException;
import excepciones.playlist.ContenidoDuplicadoException;
import excepciones.playlist.PlaylistVaciaException;
import modelo.artistas.Album;
import modelo.artistas.Artista;
import modelo.artistas.Creador;
import modelo.contenido.Cancion;
import modelo.contenido.Podcast;
import modelo.plataforma.Plataforma;
import modelo.plataforma.Playlist;
import modelo.usuarios.UsuarioGratuito;
import modelo.usuarios.UsuarioPremium;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para el Escenario 4: Gestión de Playlists
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Escenario4GestionPlaylistsTest {

    private static Plataforma plataforma;
    private static UsuarioPremium userPremium;
    private static UsuarioGratuito userGratuito;
    private static ArrayList<Cancion> canciones;
    private static Playlist playlistPrivada;
    private static Playlist playlistPublica;

    @BeforeAll
    static void setUp() throws Exception {
        Plataforma.reiniciarInstancia();
        plataforma = Plataforma.getInstancia("SoundWave Test");

        // Crear usuarios
        userPremium = plataforma.registrarUsuarioPremium("Premium", "premium@test.com", "password123", TipoSuscripcion.PREMIUM);
        userGratuito = plataforma.registrarUsuarioGratuito("Gratuito", "gratuito@test.com", "password123");

        // Crear contenido
        Artista artista = plataforma.registrarArtista("Test Artist", "Test", "Test", true);
        Album album = plataforma.crearAlbum(artista, "Test Album", new Date());

        for (int i = 1; i <= 20; i++) {
            Cancion c = album.crearCancion("Canción " + i, 100 + i * 10, GeneroMusical.POP);
            c.setReproducciones(i * 1000); // Diferentes reproducciones para ordenar
            plataforma.agregarContenidoCatalogo(c);
        }

        canciones = plataforma.getCanciones();
    }

    @AfterAll
    static void tearDown() {
        Plataforma.reiniciarInstancia();
    }

    // ========== TEST 1: Crear playlists privadas ==========
    @Test
    @Order(1)
    @DisplayName("4.1 - Usuario premium crea playlists privadas")
    void testCrearPlaylistsPrivadas() {
        playlistPrivada = userPremium.crearPlaylist("Mis Favoritas");
        Playlist playlist2 = userPremium.crearPlaylist("Para Entrenar");

        assertNotNull(playlistPrivada);
        assertNotNull(playlist2);

        assertEquals("Mis Favoritas", playlistPrivada.getNombre());
        assertFalse(playlistPrivada.isEsPublica());
        assertEquals(userPremium, playlistPrivada.getCreador());

        assertEquals(2, userPremium.getMisPlaylists().size());
    }

    // ========== TEST 2: Crear playlist pública ==========
    @Test
    @Order(2)
    @DisplayName("4.2 - Crear playlist pública a través de la plataforma")
    void testCrearPlaylistPublica() {
        playlistPublica = plataforma.crearPlaylistPublica("Éxitos del Momento", userGratuito);

        assertNotNull(playlistPublica);
        assertTrue(playlistPublica.isEsPublica());
        assertEquals(userGratuito, playlistPublica.getCreador());

        assertTrue(plataforma.getPlaylistsPublicas().contains(playlistPublica));
    }

    // ========== TEST 3: Agregar canciones a playlist ==========
    @Test
    @Order(3)
    @DisplayName("4.3 - Agregar múltiples canciones a una playlist")
    void testAgregarCancionesAPlaylist() throws Exception {
        int numCanciones = 15;

        for (int i = 0; i < numCanciones && i < canciones.size(); i++) {
            playlistPrivada.agregarContenido(canciones.get(i));
        }

        assertEquals(numCanciones, playlistPrivada.getNumContenidos());
        assertFalse(playlistPrivada.estaVacia());
    }

    // ========== TEST 4: Contenido duplicado ==========
    @Test
    @Order(4)
    @DisplayName("4.4 - No se puede agregar contenido duplicado (ContenidoDuplicadoException)")
    void testContenidoDuplicado() {
        assertThrows(ContenidoDuplicadoException.class, () -> {
            playlistPrivada.agregarContenido(canciones.get(0));
        });
    }

    // ========== TEST 5: Ordenar por popularidad ==========
    @Test
    @Order(5)
    @DisplayName("4.5 - Ordenar playlist por popularidad")
    void testOrdenarPorPopularidad() throws PlaylistVaciaException {
        playlistPrivada.ordenarPor(CriterioOrden.POPULARIDAD);

        // La canción con más reproducciones debería estar primero
        int reprosAnterior = playlistPrivada.getContenido(0).getReproducciones();
        for (int i = 1; i < playlistPrivada.getNumContenidos(); i++) {
            int reprosActual = playlistPrivada.getContenido(i).getReproducciones();
            assertTrue(reprosAnterior >= reprosActual,
                "Las canciones deberían estar ordenadas de mayor a menor reproducciones");
            reprosAnterior = reprosActual;
        }
    }

    // ========== TEST 6: Ordenar por duración ==========
    @Test
    @Order(6)
    @DisplayName("4.6 - Ordenar playlist por duración")
    void testOrdenarPorDuracion() throws PlaylistVaciaException {
        playlistPrivada.ordenarPor(CriterioOrden.DURACION);

        // La canción más corta debería estar primero
        int duracionAnterior = playlistPrivada.getContenido(0).getDuracionSegundos();
        for (int i = 1; i < playlistPrivada.getNumContenidos(); i++) {
            int duracionActual = playlistPrivada.getContenido(i).getDuracionSegundos();
            assertTrue(duracionAnterior <= duracionActual,
                "Las canciones deberían estar ordenadas de menor a mayor duración");
            duracionAnterior = duracionActual;
        }
    }

    // ========== TEST 7: Shuffle ==========
    @Test
    @Order(7)
    @DisplayName("4.7 - Shuffle mezcla la playlist")
    void testShuffle() {
        // Guardar orden original
        ArrayList<String> ordenOriginal = new ArrayList<>();
        for (int i = 0; i < playlistPrivada.getNumContenidos(); i++) {
            ordenOriginal.add(playlistPrivada.getContenido(i).getId());
        }

        // Hacer shuffle
        playlistPrivada.shuffle();

        // Verificar que el tamaño es el mismo
        assertEquals(ordenOriginal.size(), playlistPrivada.getNumContenidos());

        // No verificamos que cambió el orden porque shuffle puede resultar igual
        // Solo verificamos que no se perdieron elementos
    }

    // ========== TEST 8: Seguir playlist ==========
    @Test
    @Order(8)
    @DisplayName("4.8 - Usuario puede seguir playlist pública")
    void testSeguirPlaylist() {
        int seguidoresAntes = playlistPublica.getSeguidores();

        userPremium.seguirPlaylist(playlistPublica);

        assertEquals(seguidoresAntes + 1, playlistPublica.getSeguidores());
        assertTrue(userPremium.getPlaylistsSeguidas().contains(playlistPublica));
    }

    // ========== TEST 9: Playlist vacía no se puede ordenar ==========
    @Test
    @Order(9)
    @DisplayName("4.9 - Ordenar playlist vacía lanza PlaylistVaciaException")
    void testOrdenarPlaylistVacia() {
        Playlist playlistVacia = userPremium.crearPlaylist("Vacía");

        assertThrows(PlaylistVaciaException.class, () -> {
            playlistVacia.ordenarPor(CriterioOrden.POPULARIDAD);
        });
    }

    // ========== TEST 10: Duración total ==========
    @Test
    @Order(10)
    @DisplayName("4.10 - Calcular duración total de playlist")
    void testDuracionTotal() {
        int duracionCalculada = 0;
        for (int i = 0; i < playlistPrivada.getNumContenidos(); i++) {
            duracionCalculada += playlistPrivada.getContenido(i).getDuracionSegundos();
        }

        assertEquals(duracionCalculada, playlistPrivada.getDuracionTotal());
    }

    /**
     * Tests para el Escenario 2: Creación de Contenido
     */
    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public static class Escenario2CreacionContenidoTest {

        private static Plataforma plataforma;

        @BeforeAll
        static void setUp() {
            Plataforma.reiniciarInstancia();
            plataforma = Plataforma.getInstancia("SoundWave Test");
        }

        @AfterAll
        static void tearDown() {
            Plataforma.reiniciarInstancia();
        }

        // ========== TEST 1: Crear artistas verificados ==========
        @Test
        @Order(1)
        @DisplayName("2.1 - Crear 3 artistas verificados")
        void testCrearArtistasVerificados() {
            Artista artista1 = plataforma.registrarArtista("Bad Bunny", "Benito Martínez", "Puerto Rico", true);
            Artista artista2 = plataforma.registrarArtista("Shakira", "Shakira Mebarak", "Colombia", true);
            Artista artista3 = plataforma.registrarArtista("Taylor Swift", "Taylor Swift", "USA", true);

            assertNotNull(artista1);
            assertNotNull(artista2);
            assertNotNull(artista3);

            assertTrue(artista1.isVerificado());
            assertTrue(artista2.isVerificado());
            assertTrue(artista3.isVerificado());

            assertEquals(3, plataforma.getArtistasVerificados().size());
        }

        // ========== TEST 2: Crear artistas no verificados ==========
        @Test
        @Order(2)
        @DisplayName("2.2 - Crear 2 artistas no verificados")
        void testCrearArtistasNoVerificados() {
            Artista artista1 = plataforma.registrarArtista("Artista Emergente", "Juan Nuevo", "México", false);
            Artista artista2 = plataforma.registrarArtista("Nuevo Talento", "María Nueva", "España", false);

            assertNotNull(artista1);
            assertNotNull(artista2);

            assertFalse(artista1.isVerificado());
            assertFalse(artista2.isVerificado());

            assertEquals(2, plataforma.getArtistasNoVerificados().size());
            assertEquals(5, plataforma.getArtistas().size());
        }

        // ========== TEST 3: Crear álbumes con canciones (COMPOSICIÓN) ==========
        @Test
        @Order(3)
        @DisplayName("2.3 - Crear álbumes con canciones usando COMPOSICIÓN")
        void testCrearAlbumesConCanciones() throws Exception {
            Artista badBunny = plataforma.getArtistasVerificados().get(0);

            // Crear álbum
            Album album = plataforma.crearAlbum(badBunny, "Un Verano Sin Ti", new Date());
            assertNotNull(album);
            assertEquals("Un Verano Sin Ti", album.getTitulo());
            assertEquals(badBunny, album.getArtista());

            // COMPOSICIÓN: El álbum crea sus canciones
            String[] titulos = {"Moscow Mule", "Después de la Playa", "Me Porto Bonito",
                               "Tití Me Preguntó", "Party", "Aguacero"};

            for (String titulo : titulos) {
                Cancion cancion = album.crearCancion(titulo, 200, GeneroMusical.REGGAETON);
                assertNotNull(cancion);
                assertEquals(album, cancion.getAlbum());
                assertEquals(badBunny, cancion.getArtista());
                plataforma.agregarContenidoCatalogo(cancion);
            }

            assertEquals(6, album.getNumCanciones());
            assertEquals(6, plataforma.getCanciones().size());
        }

        // ========== TEST 4: Artista no verificado no puede crear álbum ==========
        @Test
        @Order(4)
        @DisplayName("2.4 - Artista no verificado no puede crear álbum (ArtistaNoVerificadoException)")
        void testArtistaNoVerificadoNoPuedeCrearAlbum() {
            Artista artistaNoVerificado = plataforma.getArtistasNoVerificados().get(0);

            assertThrows(ArtistaNoVerificadoException.class, () -> {
                plataforma.crearAlbum(artistaNoVerificado, "Mi Primer Álbum", new Date());
            });
        }

        // ========== TEST 5: Crear creadores de podcasts ==========
        @Test
        @Order(5)
        @DisplayName("2.5 - Crear 2 creadores de podcasts")
        void testCrearCreadores() {
            Creador creador1 = plataforma.registrarCreador("The Wild Project", "Jordi Wild", "Podcast de entrevistas");
            creador1.setSuscriptores(2500000);

            Creador creador2 = plataforma.registrarCreador("Leyendas Legendarias", "Bsjf", "Podcast de crímenes");
            creador2.setSuscriptores(1800000);

            assertNotNull(creador1);
            assertNotNull(creador2);

            assertEquals(2500000, creador1.getSuscriptores());
            assertEquals(2, plataforma.getTodosLosCreadores().size());
        }

        // ========== TEST 6: Crear episodios de podcast ==========
        @Test
        @Order(6)
        @DisplayName("2.6 - Crear episodios de podcast para cada creador")
        void testCrearPodcasts() throws Exception {
            Creador creador1 = plataforma.getTodosLosCreadores().get(0);
            Creador creador2 = plataforma.getTodosLosCreadores().get(1);

            // Crear 5 episodios para creador1
            for (int i = 1; i <= 5; i++) {
                Podcast p = plataforma.crearPodcast("Episodio " + i, 3600, creador1, i, 1, CategoriaPodcast.ENTRETENIMIENTO);
                assertNotNull(p);
                assertEquals(creador1, p.getCreador());
            }

            // Crear 5 episodios para creador2
            for (int i = 1; i <= 5; i++) {
                Podcast p = plataforma.crearPodcast("Crimen " + i, 4200, creador2, i, 1, CategoriaPodcast.TRUE_CRIME);
                assertNotNull(p);
                assertEquals(CategoriaPodcast.TRUE_CRIME, p.getCategoria());
            }

            assertEquals(10, plataforma.getPodcasts().size());
            assertEquals(5, creador1.getNumEpisodios());
            assertEquals(5, creador2.getNumEpisodios());
        }

        // ========== TEST 7: Verificar catálogo total ==========
        @Test
        @Order(7)
        @DisplayName("2.7 - Verificar contenido total en catálogo")
        void testVerificarCatalogo() {
            assertTrue(plataforma.getCanciones().size() >= 6);
            assertEquals(10, plataforma.getPodcasts().size());
            assertTrue(plataforma.getCatalogo().size() >= 16);
        }
    }
}
