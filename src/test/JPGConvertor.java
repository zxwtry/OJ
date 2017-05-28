package test;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class JPGConvertor {
    public static void main(String[] args) throws IOException {
        System.out.println("AAA");
        BufferedImage bufferedImage = convert("F:/c.bmp");
        System.out.println(bufferedImage.getWidth());
        System.out.println(bufferedImage.getHeight());
        ImageIO.write(bufferedImage, "jpg", new File("F:/c.jpg"));
    }
    public static BufferedImage convert(BufferedImage bufferedImage) {
        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
                bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
        return newBufferedImage;
    }
    public static BufferedImage convert(String fileName) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(new File(fileName));
        return convert(bufferedImage);
    }
    public static BufferedImage convert(InputStream inputStream) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(inputStream);
        return convert(bufferedImage);
    }
}


//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.io.File;
//import java.io.IOException;
//import java.awt.Color;
//import java.awt.image.BufferedImage;
//import javax.imageio.ImageIO;
//import java.util.List;
//import java.util.ArrayList;
//import javax.activation.MimetypesFileTypeMap;
//import javax.imageio.ImageIO;
//import java.awt.image.BufferedImage;
//
//public class converttojpg {
//    public static void main(String args[]) {
//        File curFile = new File(System.getProperty("user.dir"));
//        BufferedImage img = null;
//        StringBuffer strbuff;
//        try {
//            for (String strings : files()) {
//                if (strings.substring(strings.length() - 3, strings.length()).equals("png") ||
//                    strings.substring(strings.length() - 3, strings.length()).equals("PNG") ||
//                    strings.substring(strings.length() - 3, strings.length()).equals("bmp") ||
//                    strings.substring(strings.length() - 3, strings.length()).equals("BMP") ||
//                    strings.substring(strings.length() - 3, strings.length()).equals("gif") ||
//                    strings.substring(strings.length() - 3, strings.length()).equals("GIF")) {
//                    strbuff = new StringBuffer(strings);
//                    strbuff.replace(strings.length() - 3, strings.length(), "jpg");
//                    System.out.println("Converting " + strings);
//                    tojpg(strings, strbuff.toString());
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println("\nDone.\n");
//    }
//    public static String[] files() throws IOException {
//        ArrayList<String> readfiles = new ArrayList<>();
//        Files.walk(Paths.get(System.getProperty("user.dir"))).forEach(filePath -> {
//                if (Files.isRegularFile(filePath)) {
//                    readfiles.add(filePath.getFileName().toString());
//                }
//            });
//        String[] dirfiles = new String[readfiles.size()];
//        return readfiles.toArray(dirfiles);
//    }
//    public static void tojpg(String src, String dst) throws IOException {
//        BufferedImage bufferedImage;
//        File delete;
//
//        //read image file
//        bufferedImage = ImageIO.read(new File(src));
// 
//        // create a blank, RGB, same width and height, and a white background
//        BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(),
//                                                           bufferedImage.getHeight(), BufferedImage.TYPE_INT_RGB);
//        newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
// 
//        // write to jpeg file and delete old file
//        delete = new File(src);
//        delete.delete();
//        System.out.println("Deleted " + delete);
//        ImageIO.write(newBufferedImage, "jpg", new File(dst));
//        System.out.println("Created " + dst + "\n");
//    }
//}