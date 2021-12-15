package assignment3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AstronomyGetter
{
    /**
     * This method goes to http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/ and downloads every file. The files are
     * then converted to grey scale, then saved in the DownloadedFiles directory in the assignment3 package.
     * @param threaded if true, download and process the files concurrently and if false, sequentially.
     */
    public static void downloadAndProcessImages(boolean threaded) {
        List<String> filenames = getJPGFilenamesFromAstronomyWebsite();
        Stream<String> stream = filenames.stream();
        stream = threaded ? stream.unordered().parallel() : stream;     // Make stream threaded if user speicifes
        stream.map(fn -> new NamedBufferImage(getImageFromString(fn), fn)).map(ni -> applyGrayScale(ni)).forEach(ni -> saveImage(ni));
        // Creates pipeline: download file, apply grayscale, download to drive.
    }

    /**
     * This method goes to http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/ and retrieves all the jpg filenames.
     * This method assumes that the overall structure of the website tables does not change, and each row only possesses
     * one href in one <a> tag. This method also assumes that the beginning of the filename starts 6 positions to the
     * right of the first occurrence of "href" in a html table row string. Lastly, we assume that each of the images are
     * jpegs and are saved with the .jpg file extension.
     * @return the list of jpg file names
     */
    private static List<String> getJPGFilenamesFromAstronomyWebsite() {
        List<String> filenames = new ArrayList<>();     // List of filenames retrieved from astronomy website
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new URL("http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/").openStream()));

            String inputLine;   // A single line of HTML code retrieved from the astronomy website
            String filename;    // The filename extracted from inputLine
            int hrefIndex;      // The index in the inputLine String where there exists an occurrence of href

            while ((inputLine = in.readLine()) != null) {
                if ((hrefIndex = inputLine.indexOf("href")) != -1 ) {
                    filename = inputLine.substring(hrefIndex + 6, inputLine.indexOf("\"", hrefIndex + 6));
                    // Once we identify where "href" exists in the string, we can locate where the filename substring exists based on observed html structure
                    if (filename.toLowerCase().contains(".jpg"))
                        filenames.add(filename);    // If the file has jpg extension, add it to list
                }
            }
            in.close();
        } catch (MalformedURLException e) {
            System.err.println("URL ERROR - Try retyping url link.");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("IO ERROR - System Problem.");
            e.printStackTrace();
        }
        return filenames;
    }

    /**
     * Given the name of a valid image filename on the website, this method downloads and returns the image object.
     * @param name the valid image filename on the website
     * @return the image object if it is valid, null otherwise
     */
    public static BufferedImage getImageFromString(String name) {
        try  {
            return ImageIO.read(new URL("http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/" + name));
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }

    /**
     * Will apply a grayscale transform to a given namedImage. Method for applying grayscale comes from:
     * https://www.tutorialspoint.com/java_dip/grayscale_conversion.htm
     * @param namedImage the namedImage object
     * @return the transformed namedImage
     */
    public static NamedBufferImage applyGrayScale(NamedBufferImage namedImage) {
        BufferedImage image = namedImage.getImage();
        for(int i = 0; i < image.getHeight(); i++)
            for(int j = 0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                int red = (int)(c.getRed() * 0.299);
                int green = (int)(c.getGreen() * 0.587);
                int blue = (int)(c.getBlue() * 0.114);
                image.setRGB(j, i, new Color(red+green+blue,red+green+blue,red+green+blue).getRGB());
            }
        return namedImage;
    }

    /**
     * Saves the image to the DownloadedFiles directory located in the assignment3 package.
     * @param namedImage the namedImage we want to save
     */
    private static void saveImage(NamedBufferImage namedImage) {
        try {
            ImageIO.write(namedImage.getImage(), "jpg", new File("./src/assignment3/DownloadedFiles/" + namedImage.getName()));
        } catch (IOException e) {
            System.err.println("IO ERROR - File(s) are probably open in another process. Close it/them.");
            e.printStackTrace();
        }
    }
}
