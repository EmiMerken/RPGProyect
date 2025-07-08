package Swing.Fight;

import Swing.Fight.Exceptions.NullImageException;
import Swing.Fight.Exceptions.ValueOutOfAcceptedLimits;
import Swing.Fight.Interfaces.Filterable;
import Swing.Fight.Interfaces.Transformable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

/**
 * "FEnemy"
 * Representa a un enemigo con sus respectivos <code>sprites</code> dentro de un juego RPG
 */
public class FEnemy extends FCharacter implements Filterable, Transformable {


    private ImageIcon[] sprites;
    private int actualSprite = 0;
    private ImageIcon imageIcon;

    private int x;
    private int y;

    public FEnemy(String name, int attackDamage, int hp){
        super(name,attackDamage,hp);
        setHorizontalAlignment(CENTER);
        textCenter();
    }

    public FEnemy(String name, int attackDamage, int hp, ImageIcon[] sprites) {
        super(name,attackDamage,hp);
        this.sprites = sprites;
        setActualSprite(0);
        setHorizontalAlignment(CENTER);
        textCenter();
    }


    public void setSprites(String directoryPath){
        File directory = new File(directoryPath);
        File[] spriteList = Objects.requireNonNull(directory.listFiles());
        if (directory.isDirectory() && directory.exists()) {
            //Transforma los archivos de una carpeta a un stream que se convierte en una lista de ImageIcon's
            sprites = Arrays.stream(spriteList).map(file -> new ImageIcon(file.getAbsolutePath())).toArray(ImageIcon[]::new);
        }
    }

    /**
     * se asigna un sprite al enemigo
     * @param actualSprite indice del arreglo de sprites
     */
    public void setActualSprite(int actualSprite){
        if (sprites !=null){
            this.actualSprite = actualSprite;
            this.imageIcon = sprites[actualSprite];
            setIcon(sprites[actualSprite]);
        } else {
            throw new NullPointerException("Atributo 'sprites' es null");
        }

    }

    /**
     * Permite centrar el texto y que quede por encima del sprite del enemigo
     */
    private void textCenter(){
        setText(name + " HP: " + HP);
        setHorizontalTextPosition(CENTER);
        setVerticalTextPosition(TOP);
    }

    public void setEnemyText(String text){
        setText(text);
    }

    public void setEnemyTextColor(Color color ) {
        setForeground(color);
    }

    public void setEnemyTextFont(Font font){
        setFont(font);
    }

    @Override
    public void setHP(int hp) {

    }

    @Override
    public void changeColor(Color color) {
        checkForImageIcon();
        imageIcon = new ImageIcon(paintByPixel(imageToBufferedImage(imageIcon.getImage()), color));
        setIcon(imageIcon);
    }

    @Override
    public void monoColor(Color color, int intensity) {
        checkForImageIcon();
        BufferedImage original = imageToBufferedImage(imageIcon.getImage());
        changeColor(color);
        setAlpha(intensity);
        BufferedImage resultado = new BufferedImage(original.getWidth(),original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resultado.createGraphics();
        g2d.drawImage(original, 0,0,null);
        g2d.drawImage(imageIcon.getImage(), 0,0,null);
        g2d.dispose();
        imageIcon = new ImageIcon(resultado);
        setIcon(imageIcon);
    }

    @Override
    public void setBrightness(float contrast, float offset) {
        checkForImageIcon();
        if (contrast >= 0 && contrast <= 100){
            RescaleOp applyBrightness = new RescaleOp(contrast, offset, null);
            imageIcon = new ImageIcon(applyBrightness.filter(imageToBufferedImage(imageIcon.getImage()),null));
            setIcon(imageIcon);
        } else {
            throw new ValueOutOfAcceptedLimits("'contrast' debe estar entre 0 y 100... Tu contraste: " + contrast);
        }
    }

    @Override
    public void setAlpha(int opacity) {
        checkForImageIcon();
        if (0 <= opacity && opacity <= 100){
            compositeByGraphics(opacity);
        } else {
            throw new ValueOutOfAcceptedLimits("'opacity' debe estar entre 0 y 100... Tu opacidad: " + opacity);
        }
    }

    @Override
    public void pos(int x, int y) {
        this.x = x;
        this.y = y;
        setBorder(new EmptyBorder(0,x,y,0));
    }

    @Override
    public void rotate(float degrees) {
        Image originalImage = imageIcon.getImage();
        BufferedImage newImage = new BufferedImage(originalImage.getWidth(null),originalImage.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d =  newImage.createGraphics();

        //Toma en cuenta el desplazamiento
        double rotationCenterX = originalImage.getWidth(null) / 2.0;
        double rotationCenterY = originalImage.getHeight(null) / 2.0;
        g2d.rotate(Math.toRadians(degrees), rotationCenterX, rotationCenterY);
        g2d.drawImage(originalImage,0,0,null);
        g2d.dispose();
        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
    }

    @Override
    public void scalate(float sizeX, float sizeY) {
        Image originalImage = imageIcon.getImage();

        int newWidth = (int) (originalImage.getWidth(null)*sizeX);
        int newHeight = (int) (originalImage.getHeight(null)*sizeY);

        BufferedImage newImage = new BufferedImage(newWidth,newHeight,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d =  newImage.createGraphics();
        g2d.scale(sizeX, sizeY);
        g2d.drawImage(originalImage, 0,0,null);
        g2d.dispose();
        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
    }

    public int getPosX(){
        return this.x;
    }

    public int getPosY(){
        return this.y;
    }

    /// //////////////////////////

    private BufferedImage paintByPixel(BufferedImage newImage, Color color){
        //Obtiene todos los pixeles, verifica cuales son completamente opacos y los colorea
        int [] RGB = newImage.getRGB(0, 0, newImage.getWidth(), newImage.getHeight(), null, 0, newImage.getWidth());
        RGB = Arrays.stream(RGB).map(i -> {
            int alpha = i>>24 & 255; //Obtiene los bits de "alpha" y obtiene la opacidad
            return alpha > 0 ? color.getRGB() : i;
        }).toArray();
        newImage.setRGB(0, 0, newImage.getWidth(), newImage.getHeight(), RGB, 0, newImage.getWidth());
        return newImage;
    }

    private void compositeByGraphics(int opacity){
        Image image = imageIcon.getImage();
        BufferedImage newImage = new BufferedImage(image.getWidth(null),image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity / 100f));
        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();
        imageIcon = new ImageIcon(newImage);
        setIcon(imageIcon);
    }

    private BufferedImage imageToBufferedImage(Image image){
        BufferedImage newImage = new BufferedImage(image.getWidth(null),image.getWidth(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();
        g2d.drawImage(image,getWidth(),getHeight(),null);
        g2d.dispose();
        return newImage;
    }

    private void checkForImageIcon(){
        if (imageIcon==null){
            throw new NullImageException("El sprite actual de la clase: " + this.getClass().getName() + " no tiene asignada una imagen");
        }
    }
}

