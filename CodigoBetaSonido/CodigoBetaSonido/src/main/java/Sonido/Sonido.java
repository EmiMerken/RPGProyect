package Sonido;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.IllegalFormatException;
import java.util.Objects;

public class Sonido {
    private Clip clip;
    private final URL[] soundURL;

    public Sonido() {
        soundURL = new URL[10];
        cargarSonidos("src/main/resources/Sonido");
    }
    public void cargarSonidos(String directoryPath) {
        File directory = new File(directoryPath);
        File[] listaDeSonidos = Objects.requireNonNull(directory.listFiles());

        if (directory.isDirectory() && directory.exists()) {
            System.out.println("si es carpeta");
        }

        for (int i = 0; i < listaDeSonidos.length; i++) {
            if (listaDeSonidos[i] != null) {
                System.out.println(listaDeSonidos[i].getName());
                soundURL[i] = getClass().getResource(listaDeSonidos[i].getName());
                System.out.println(soundURL[i]);
            } else {
                System.out.println("no archivo");
            }
        }
    }

    public void cargarSonido(int sonido) throws ExcepcionSonido{
        try {
            if (soundURL[sonido] == null) {
                System.err.println("No se puede cargar el sonido " + sonido + ": recurso no encontrado");
            }
            AudioInputStream audio = AudioSystem.getAudioInputStream(soundURL[sonido]);
            clip = AudioSystem.getClip();
            clip.open(audio);
            System.out.println("Sonido: " + sonido + " cargado correctamente");

        } catch (IllegalArgumentException e) {
           throw new ExcepcionSonido(e.getMessage());
        }catch (Exception e){
            throw new ExcepcionSonido("error al cargar sonido: " + e.getMessage());}
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
