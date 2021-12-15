package assignment3;

import java.awt.image.BufferedImage;

public class NamedBufferImage
{
    private BufferedImage image;
    private String name;

    public NamedBufferImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}
