package info.kingpes.ffmpeg;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Chau Huynh on 11/28/2018.
 */

public class Utils {
    public static File getConvertedFile(String folder, String fileName){
        File f = new File(folder);

        if (!f.exists())
            f.mkdirs();

        return new File(f.getPath() + File.separator + fileName);
    }


    public static String getOutputPath() {
        String path = Environment.getExternalStorageDirectory().toString() + File.separator + "FOLDER_NAME_CHAU" + File.separator;

        File folder = new File(path);
        if (!folder.exists())
            folder.mkdirs();

        return path;
    }


    public static void refreshGallery(String path, Context context) {

        File file = new File(path);
        try {
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(file);
            mediaScanIntent.setData(contentUri);
            context.sendBroadcast(mediaScanIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File copyFileToExternalStorage(int resourceId, String resourceName, Context context) {
        String pathSDCard = getOutputPath() + resourceName;
        try {
            InputStream inputStream = context.getResources().openRawResource(resourceId);
            FileOutputStream outputStream = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read = 0;

            try {
                while ((read = inputStream.read(buff)) > 0) {
                    outputStream.write(buff, 0, read);
                }
            } finally {
                inputStream.close();
                outputStream.close();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }







        return new File(pathSDCard);
    }

}
