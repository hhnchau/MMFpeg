package info.kingpes.ffmpeg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        File audio = Utils.copyFileToExternalStorage(R.raw.audio, "audio.mp3", getApplicationContext());
        final File audio2 = Utils.copyFileToExternalStorage(R.raw.audio2, "audio2.mp3", getApplicationContext());
        File audio3 = Utils.copyFileToExternalStorage(R.raw.audio3, "audio3.mp3", getApplicationContext());
        File video = Utils.copyFileToExternalStorage(R.raw.video, "video.mp4", getApplicationContext());
        File video2 = Utils.copyFileToExternalStorage(R.raw.video2, "video2.mp4", getApplicationContext());
        File videoSmall1 = Utils.copyFileToExternalStorage(R.raw.video_small_1, "video_small_1.mp4", getApplicationContext());
        File font = Utils.copyFileToExternalStorage(R.font.roboto_black, "myFont.ttf", getApplicationContext());
        File[] images = new File[]{
                Utils.copyFileToExternalStorage(R.drawable.image1, "image1.png", getApplicationContext())
                , Utils.copyFileToExternalStorage(R.drawable.image2, "image2.png", getApplicationContext())
                , Utils.copyFileToExternalStorage(R.drawable.image3, "image3.png", getApplicationContext())
                , Utils.copyFileToExternalStorage(R.drawable.image4, "image4.png", getApplicationContext())
                , Utils.copyFileToExternalStorage(R.drawable.image5, "image5.png", getApplicationContext())};




        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    MovieMaker.with(getBaseContext())
                        .setAudio(audio2)
                            .setOutputPath(Utils.getOutputPath() + "video")
                            .setOutputFileName("movie_" + System.currentTimeMillis() + ".mp4")
                            .setCallback(new FFMpegCallback() {
                                @Override
                                public void onProgress(String progress) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "onProgress", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onSuccess(File convertedFile, String type) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "onSuccess", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Exception error) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onNotAvailable(Exception error) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "onNotAvailable", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                @Override
                                public void onFinish() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "onFinish", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            })
                        .convert();


            }
        });

    }
}
