package Sonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sonido {
    private Clip clip;
    private final URL[] soundURL;

    public Sonido() {
        soundURL = new URL[10];
        try {

            soundURL[0] = getClass().getResource("/Sonido/batallaFondo.wav");
            soundURL[1] = getClass().getResource("/Sonido/ataque-espada.wav");
            soundURL[2] = getClass().getResource("/Sonido/golpe.wav");
            soundURL[3] = getClass().getResource("/Sonido/presionarSonido.wav");
            soundURL[4] = getClass().getResource("/Sonido/seleccionSonido.wav");
            soundURL[5] = getClass().getResource("/Sonido/cura.wav");
            soundURL[6] = getClass().getResource("/Sonido/gameOver.wav");
            soundURL[7] = getClass().getResource("/Sonido/victoria.wav");

            for (int i = 0; i <= soundURL.length; i++) {
                if (soundURL[i] == null) {
                    System.err.println("¡Advertencia! No se pudo encontrar el archivo de sonido " + i);
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar los recursos de sonido: " + e.getMessage());
        }
    }

    public void cargarSonido(int sonido) {
        try {
            if (soundURL[sonido] == null) {
                System.err.println("No se puede cargar el sonido " + sonido + ": recurso no encontrado");
                return;
            }

            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[sonido]);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            System.err.println("Error al cargar el sonido " + sonido + ": " + e.getMessage());
        }
    }

    public void play() {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0);
            clip.start();
        }
    }

    public void stop() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }
}
