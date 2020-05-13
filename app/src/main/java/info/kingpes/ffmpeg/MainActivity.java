package info.kingpes.ffmpeg;

import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btnGoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(MainActivity.this, Main2.class));

                finish();
            }
        });
        final TextView textView = findViewById(R.id.text);
        final Handler handler = new Handler();

        int time = 1000 * 60 * 5;
        final CountDownTimer count = new CountDownTimer(time, 1000) {
            @Override
            public void onTick(final long l) {
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        textView.setText((l / 1000) + "");
                    }
                };
                handler.post(runnable);
            }

            @Override
            public void onFinish() {

            }
        };


        final FFmpeg ffmpeg = FFmpeg.getInstance(this);
        try {
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onSuccess() {
                    // FFmpeg is supported by device
                    Toast.makeText(MainActivity.this, "OK", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (FFmpegNotSupportedException e) {
            // Handle if FFmpeg is not supported by device
            Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
        }


        final File nhac = Utils.copyFileToExternalStorage(R.raw.nhac, "nhac.mp3", getApplicationContext());
        File audio = Utils.copyFileToExternalStorage(R.raw.audio, "audio.mp3", getApplicationContext());
        final File audio1 = Utils.copyFileToExternalStorage(R.raw.audio1, "audio1.mp3", getApplicationContext());
        final File audio2 = Utils.copyFileToExternalStorage(R.raw.audio2, "audio2.mp3", getApplicationContext());
        File audio3 = Utils.copyFileToExternalStorage(R.raw.audio3, "audio3.mp3", getApplicationContext());
        final File video = Utils.copyFileToExternalStorage(R.raw.video, "video.mp4", getApplicationContext());
        final File video2 = Utils.copyFileToExternalStorage(R.raw.video2, "video2.mp4", getApplicationContext());
        final File videoSmall1 = Utils.copyFileToExternalStorage(R.raw.video_small_1, "video_small_1.mp4", getApplicationContext());
        final File buonkhongem = Utils.copyFileToExternalStorage(R.raw.buon_khong_em, "buon_khong_em.mp4", getApplicationContext());
        final File videobase = Utils.copyFileToExternalStorage(R.raw.video_base, "video_base.mp4", getApplicationContext());
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

                count.cancel();
                count.start();

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
                            public void onFailure(final Exception error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "onFailure" + error, Toast.LENGTH_SHORT).show();
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
                                        count.cancel();
                                    }
                                });
                            }
                        })
                        .convert();

            }
        });

        findViewById(R.id.merge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count.cancel();
                count.start();

                MovieMaker.with(getBaseContext())
                        .setVideo(videobase)
                        .setAudio(nhac)
                        .setAudio1(audio1)
                        .setOutputPath(Utils.getOutputPath() + "video")
                        .setOutputFileName("movie_" + System.currentTimeMillis() + ".mp4")
                        .setCallback(new FFMpegCallback() {
                            @Override
                            public void onProgress(String progress) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(MainActivity.this, "onProgress", Toast.LENGTH_SHORT).show();
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
                            public void onFailure(final Exception error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "onFailure" + error, Toast.LENGTH_SHORT).show();
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
                                        count.cancel();
                                    }
                                });
                            }
                        })
                        .merger();
            }
        });

        findViewById(R.id.mergeAudio).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                count.cancel();
                count.start();

                MovieMaker.with(getBaseContext())
                        .setAudio(nhac)
                        .setAudio1(audio1)
                        .setOutputPath(Utils.getOutputPath() + "video")
                        .setOutputFileName("audio_" + System.currentTimeMillis() + ".mp3")
                        .setCallback(new FFMpegCallback() {
                            @Override
                            public void onProgress(String progress) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        //Toast.makeText(MainActivity.this, "onProgress", Toast.LENGTH_SHORT).show();
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
                            public void onFailure(final Exception error) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "onFailure" + error, Toast.LENGTH_SHORT).show();
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
                                        count.cancel();
                                    }
                                });
                            }
                        })
                        .mergerAudio();
            }
        });

    }
}
