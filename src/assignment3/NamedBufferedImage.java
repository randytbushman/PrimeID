package assignment3;

import java.awt.image.BufferedImage;

/**
 * This class serves as a wrapper for the BufferedImage class. It adds a name that we can use to specify the file location
 * where the image will be saved.
 *
 * @author Randolph Bushman
 * @version 12.14.2021
 */
public class NamedBufferedImage
{
    private final BufferedImage image;    // The image object we wrap
    private final String name;            // The name of the image

    public NamedBufferedImage(BufferedImage image, String name) {
        this.image = image;
        this.name = name;
    }

    /**
     * Returns the BufferedImage object.
     * @return the BufferedImage object
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * Returns the name we assign to the image.
     * @return the name we assign to the image
     */
    public String getName() {
        return name;
    }
}
