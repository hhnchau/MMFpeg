package info.kingpes.ffmpeg;

import android.content.Context;
import android.os.Environment;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Chau Huynh on 11/28/2018.
 */

public class MovieMaker {
    private Context context;
    private ArrayList<File> images;
    private File audio;
    private String outputPath;
    private String outputFileName;
    private FFMpegCallback callback;

    private MovieMaker(Context context){
        this.context = context;
    }

    public static MovieMaker with(Context context) {
        return new MovieMaker(context);
    }

    private MovieMaker setFile(ArrayList<File> originalFiles){
        this.images = originalFiles;
        return this;
    }

    public MovieMaker setAudio(File originalFile){
        this.audio = originalFile;
        return this;
    }

    public MovieMaker setCallback(FFMpegCallback callback){
        this.callback = callback;
        return this;
    }

    public MovieMaker setOutputPath(String output){
        this.outputPath = output;
        return this;
    }

    public MovieMaker setOutputFileName(String output){
        this.outputFileName = output;
        return this;
    }


    public void convert(){

        final File outputLocation = Utils.getConvertedFile(outputPath, outputFileName);

        String[] cmd = new String[21];
        cmd[0] = "-analyzeduration";
        cmd[1] = "1M";
        cmd[2] = "-probesize";
        cmd[3] = "1M";
        cmd[4] ="-y";
        cmd[5] ="-framerate";
        cmd[6] ="1/3.79";
        cmd[7] ="-i";
        cmd[8] = Utils.getOutputPath() + "image%d.png";
        cmd[9] ="-i";
        cmd[10] = audio.getPath();
        cmd[11] ="-c:v";
        cmd[12] ="libx264";
        cmd[13] ="-r";
        cmd[14] ="5";
        cmd[15] ="-pix_fmt";
        cmd[16] ="yuv420p";
        cmd[17] ="-c:a";
        cmd[18] ="aac";
        cmd[19] ="-shortest";
        cmd[20] = outputLocation.getPath();

        try {
            FFmpeg.getInstance(context).execute(cmd, new ExecuteBinaryResponseHandler(){
                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onProgress(String message) {
                    super.onProgress(message);
                    callback.onProgress(message);
                }

                @Override
                public void onSuccess(String message) {
                    super.onSuccess(message);
                    Utils.refreshGallery(outputLocation.getPath(), context);
                    callback.onSuccess(outputLocation, "video");
                }

                @Override
                public void onFailure(String message) {
                    super.onFailure(message);
                    if (outputLocation.exists()) {
                        outputLocation.delete();
                    }
                    callback.onFailure(new IOException(message));
                }

                @Override
                public void onFinish() {
                    super.onFinish();
                    callback.onFinish();
                }
            });
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
            callback.onFailure(e);
        }
    }
}