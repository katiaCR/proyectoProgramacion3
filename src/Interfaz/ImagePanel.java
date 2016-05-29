package Interfaz;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JComponent;

/**
 * Esta clase se encarga de añadir una imagen de fondo a una ventana
 * @author katia abigail
 * @version 16/05/2016
 */
class ImagePanel extends JComponent {
    private Image image;
    public ImagePanel(Image image) {
        this.image = image;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
    
}