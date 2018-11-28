package info.kingpes.ffmpeg;

import java.io.File;

/**
 * Created by Chau Huynh on 11/28/2018.
 */

public interface FFMpegCallback {

    void onProgress(String progress);

    void onSuccess(File convertedFile, String type);

    void onFailure(Exception error);

    void onNotAvailable(Exception error);

    void onFinish();
}
