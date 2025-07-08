package Swing.Fight.Interfaces;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface Filterable extends VFX{
    void changeColor(Color color);
    void monoColor(Color color, int intensity);
    void setBrightness(float contrast, float offset);
    void setAlpha(int opacity);

}
