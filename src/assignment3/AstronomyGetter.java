package assignment3;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class AstronomyGetter
{
    /**
     * This method goes to http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/ and downloads every one of the files.
     * These files are then converted to grey scale, then saved in the project directory.
     * @param threaded if true, download and process the files concurrently and if false, sequentially.
     */
    public static void downloadAndProcessImages(boolean threaded) {
        String[] filenames = {"EclipseSvalbard_Santikunaporn_960.jpg"
                ,"GS_20150401_SolarHalo_8814_DayNight.jpg"
                ,"HokusaiOblique_2015h800c.jpg","M97_M108_LRGB_60p_APF_ckaltseis2015_1024.jpg"
                ,"Messier46DenisPRIOU1024.jpg","Mooooonwalk_rjn_960.jpg","MysticMountain_HubbleForteza_960.jpg"
                ,"N2903JewelofLeo_hallas_c1024.jpg","Ring0644_hubble_960.jpg","TLE2015_1024x821olsen.jpg"
                ,"TLEGoldenGate_rba_d.jpg","TethysRingShadow_cassini_1080.jpg","VirgoCentral_Subaru_960.jpg"
                ,"VolcanoWay_montufar_960.jpg","barnard344_vanderHoeven_960.jpg","hs-2015-13-a-web_voorwerpjes600h.jpg"
                ,"ngc3293_eso_960.jpg","rNGC-4725-HaL(AOX)RGBpugh1024.jpg","snowtrees_bonfadini_960.jpg"
                ,"tafreshi_MG_3456Pc2s.jpg"};

        Stream<String> stream = Arrays.stream(filenames);
        stream = threaded ? stream.unordered().parallel() : stream;
        stream.map(fn -> new NamedBufferImage(getImageFromString(fn), fn)).map(ni -> applyGrayScale(ni)).forEach(ni -> saveImage(ni));
    }

    public static BufferedImage getImageFromString(String name) {
        //System.out.println("Downloading " + name);
        try  {
            //System.out.println("Downloaded  " + name);
            return ImageIO.read(new URL("http://elvis.rowan.edu/~mckeep82/ccp/fa19/Astronomy/" + name));
        } catch (IOException e) {e.printStackTrace();}
        return null;
    }

    public static NamedBufferImage applyGrayScale(NamedBufferImage namedImage)
    {
        BufferedImage image = namedImage.getImage();
        int red;
        int green;
        int blue;
        for(int i=0; i < image.getHeight(); i++)
            for(int j=0; j < image.getWidth(); j++) {
                Color c = new Color(image.getRGB(j, i));
                red = (int)(c.getRed() * 0.299);
                green = (int)(c.getGreen() * 0.587);
                blue = (int)(c.getBlue() *0.114);
                image.setRGB(j,i,new Color(red+green+blue,red+green+blue,red+green+blue).getRGB());
            }
        return namedImage;
    }

    private static void saveImage(NamedBufferImage namedImage) {
        try {
            ImageIO.write(namedImage.getImage(), "jpg", new File("./src/assignment3/DownloadedFiles/" + namedImage.getName()));
        } catch (Exception e) {e.printStackTrace();}
    }
}
