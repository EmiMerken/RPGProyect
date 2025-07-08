package Swing.Fight;

import Swing.Fight.Exceptions.NullImageException;
import Swing.Fight.Exceptions.ValueOutOfAcceptedLimits;
import Swing.Fight.Interfaces.Filterable;
import Swing.Fight.Interfaces.Transformable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * "<code>FBackground</code>"
 *  Es la "Pared" que siempre esta al fondo dentro de la batalla
 *  <div> Se le puede asignar una imagen con <code>setImage(String path)</code> </div>
 *  <div> Se puede crear un mosaico que cubra toda la pantalla con <code>setImageRepeat(boolean b);</code></div>
 *  <div> Y si no hay imagen, se le puede asignar colores... "<code>setColor(Color color)</code>"</div>
 */
public class FBackground extends JPanel implements Filterable, Transformable {

    private BufferedImage image;
    private Color color;
    private boolean isMosaic;

    public FBackground(){
        super(new BorderLayout());
        setOpaque(true);
        isMosaic = false;
    }

    public void setColor(Color color){
        this.color = color;
        setBackground(color);
    }

    public void removeColor(){
        this.color = null;
        setBackground(Color.white);
    }

    public Color getColor() {
        return color;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setImage(String path) throws IOException {
        this.image = ImageIO.read(new File(path));
    }

    public void removeImage(){
        this.image = null;
    }

    public void setMosaic(boolean b){
        this.isMosaic = b;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //VUELVE LA IMAGEN EN UN MOSAICO QUE SE REPITE POR TÓDO EL PANEL SI EL PROGRAMADOR LO DESEA
        if (this.image != null && isMosaic){
            Rectangle rect = new Rectangle(0, 0, this.image.getWidth(), this.image.getHeight());
            TexturePaint texturePaint = new TexturePaint(this.image, rect);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setPaint(texturePaint);
            g2d.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    @Override
    public void changeColor(Color color) {
        checkForImage();
        //Obtiene todos los pixeles, verifica cuales son completamente opacos y los colorea
        int [] RGB = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());
        RGB = Arrays.stream(RGB).map(i -> {
            int alpha = i>>24 & 255; //Obtiene los bits de "alpha" y obtiene la opacidad
            return alpha > 0 ? color.getRGB() : i;
        }).toArray();
        image.setRGB(0, 0, image.getWidth(), image.getHeight(), RGB, 0, image.getWidth());
    }

    @Override
    public void monoColor(Color color, int intensity) {
        checkForImage();
        BufferedImage original = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        BufferedImage resultado = new BufferedImage(original.getWidth(),original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = original.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        changeColor(color);
        setAlpha(intensity);
        g2d = resultado.createGraphics();
        g2d.drawImage(original, 0, 0, null);
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        image = resultado;
    }

    @Override
    public void setBrightness(float contrast, float offset) {
        checkForImage();
        if (contrast >= 0 && contrast <= 100){
            contrast = contrast / 2; //Convertirmos unidades para que sea entre 0 y 100
            RescaleOp applyBrightness = new RescaleOp(contrast, offset, null);
            image = applyBrightness.filter(image,null);
        } else {
            throw new ValueOutOfAcceptedLimits("'contrast' debe estar entre 0 y 100... Tu contraste: " + contrast );
        }
    }

    @Override
    public void setAlpha(int opacity) {
        checkForImage();
        if (0 <= opacity && opacity <= 100){
            image = compositeByGraphics(opacity);
        } else {
            throw new ValueOutOfAcceptedLimits("'opacity' debe estar entre 0 y 100... Tu opacidad: " + opacity);
        }
    }

    @Override
    public void pos(int x, int y) {
        throw new UnsupportedOperationException("Este metodo en la clase " + this.getClass().getSimpleName() + " no debe ser utilizado.");
    }

    @Override
    public void rotate(float degrees) {
        throw new UnsupportedOperationException("Este metodo en la clase " + this.getClass().getSimpleName() + " no debe ser utilizado.");
    }

    @Override
    public void scalate(float sizeX, float sizeY) {
        checkForImage();
        int newWidth = (int) (image.getWidth()*sizeX);
        int newHeight = (int) (image.getHeight()*sizeY);
        image = scaleByGraphics(sizeX, sizeY, newWidth,  newHeight);
    }

    /************************************************************
     * - METODOS INTERNOS: SOLO DE USO PARA ESTA CLASE
     ************************************************************/

    private BufferedImage scaleByGraphics(float sizeX, float sizeY, int newWidth,int newHeight){
        BufferedImage newImage = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.scale(sizeX, sizeY);
        g2d.drawImage(image, 0,0,null);
        g2d.dispose();
        return newImage;
    }

    private BufferedImage compositeByGraphics(int opacity){
        BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 100f));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        return newImage;
    }

    private void checkForImage(){
        if (image==null){
            throw new NullImageException("No has asignado una imagen a la clase: " + this.getClass().getName());
        }
    }

}
