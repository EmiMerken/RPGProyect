package Sonido;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class SonidoTest {
    private Sonido sonido;

    @BeforeEach
    void setUp() {
        sonido = new Sonido();
    }

    @Test
    @DisplayName("Test cargar sonidos desde directorio existente")
    void testCargarSonidosDirectorioExistente() {
        String directorioValido = "src/main/resources/Sonido";

        Assertions.assertDoesNotThrow(() -> {
            sonido.cargarSonidos(directorioValido);
        });
    }
    @Test
    @DisplayName("Test cargar sonidos desde directorio inexistente")
    void testCargarSonidosDirectorioInexistente() {
        String directorioInvalido = "src/main/resources/DirectorioInexistente";

        Assertions.assertThrows(NullPointerException.class, () -> {
            sonido.cargarSonidos(directorioInvalido);
        });
    }
    @Test
    @DisplayName("Test cargar sonido con índice inválido")
    void testCargarSonidoIndiceInvalido() {
        int indiceInvalido = 999;

        Assertions.assertThrows(ExcepcionSonido.class, () -> {
            sonido.cargarSonido(indiceInvalido);
        });
    }

    @Test
    @DisplayName("Test cargar sonido con índice negativo")
    void testCargarSonidoIndiceNegativo() {
        int indiceNegativo = -1;

        Assertions.assertThrows(ExcepcionSonido.class, () -> {
            sonido.cargarSonido(indiceNegativo);
        });
    }

    @Test
    @DisplayName("Test métodos de reproducción sin sonido cargado")
    void testMetodosReproduccionSinSonido() {
        Assertions.assertDoesNotThrow(() -> {
            sonido.play();
            sonido.stop();
            sonido.loop();
        });
    }

    @Test
    @DisplayName("Test cargar directorio con archivos no válidos")
    void testCargarDirectorioArchivosNoValidos() {
        String directorioTemp = "src/test/resources/SonidosTest";
        new File(directorioTemp).mkdirs();
        Assertions.assertDoesNotThrow(() -> {
            sonido.cargarSonidos(directorioTemp);
        });
    }



}