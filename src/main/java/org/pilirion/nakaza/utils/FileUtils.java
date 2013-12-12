package org.pilirion.nakaza.utils;

import com.mortennobel.imagescaling.DimensionConstrain;
import com.mortennobel.imagescaling.ResampleOp;
import org.apache.wicket.Application;
import org.apache.wicket.markup.html.form.upload.FileUpload;
import org.apache.wicket.util.file.Files;
import org.apache.wicket.util.lang.Packages;
import org.pilirion.nakaza.Nakaza;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Base class responsible for handling work with files. Mainly saving files, anywhere it happens in the application.
 */
public class FileUtils {
    /**
     * It takes image file, changes its size to max width and max height and the save it on the server.
     *
     * @param upload File to resize and save.
     * @param name Basic name of the file. It is further changed in the process of saving.
     * @param maxHeight Maximum height of the image.
     * @param maxWidth Maximum width of the image
     * @return relative path to the image.
     */
    public static String saveImageFileAndReturnPath(FileUpload upload, String name, int maxHeight, int maxWidth){
        ServletContext context = ((Nakaza) Application.get()).getServletContext();
        String realPath = context.getRealPath("/WEB-INF/classes/" + Packages.absolutePath(Nakaza.class, Nakaza.getBaseContext()));;
        File baseFile = new File(realPath);

        String fileType = FileUtils.getFileType(upload.getClientFileName());
        String fileName = Pwd.getMD5(upload.getClientFileName() + name) + "." + fileType;
        // Create a new file
        try {

            File newFile = new File(baseFile, fileName);
            ResampleOp resampleOp = new ResampleOp(DimensionConstrain.createMaxDimension(maxWidth, maxHeight));
            BufferedImage imageGame = ImageIO.read(upload.getInputStream());
            BufferedImage imageGameSized = resampleOp.filter(imageGame, null);

            // Check new file, delete if it already existed
            FileUtils.cleanFileIfExists(newFile);
            baseFile.mkdirs();
            // Save to new file
            if(!newFile.createNewFile()){
                throw new IllegalStateException("Unable to write file " + newFile.getAbsolutePath());
            }
            ImageIO.write(imageGameSized, fileType, newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Nakaza.getBaseContext() + fileName;
    }

    /**
     * It returns last part of the file. Usually this says something about the type of the image.
     *
     * @param fileName Name of the file to parse
     * @return type of the file.
     */
    public static String getFileType(String fileName){
        String[] fileParts = fileName.trim().split("\\.");
        if(fileParts.length > 0){
            return fileParts[fileParts.length - 1];
        } else {
            return "";
        }
    }

    /**
     * It cleans space of the file given as parameter, if anything with the same name already existed.
     *
     * @param newFile
     */
    public static void cleanFileIfExists(File newFile)
    {
        if (newFile.exists())
        {
            // Try to delete the file
            if (!Files.remove(newFile))
            {
                throw new IllegalStateException("Unable to overwrite " + newFile.getAbsolutePath());
            }
        }
    }
}
