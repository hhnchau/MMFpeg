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

//    public class MainActivity extends AppCompatActivity {
//
//        @Override
//        protected void onCreate(Bundle savedInstanceState) {
//            super.onCreate(savedInstanceState);
//            setContentView(R.layout.activity_main);
//
////        saveImageFileToSDCard(editBitmap("a", "Kingpesvn"), "abc");
//
//            String urlToShare = "https://www.google.com.vn/?gfe_rd=cr&ei=PyOYWf6NM6ml8wf4xbToDw";
//
////        String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
////        String myDir = externalStorageDirectory + "/DCIM/Camera";
//
////        Uri uri = Uri.parse("file:///" + myDir + "/a.jpg");
////        Uri uri = Uri.parse("file:///storage/emulated/0/DCIM/Camera/a.jpg");
//
//            Uri uri = getUriFromSDCard("a");
////        Bitmap bitmap = null;
////        try {
////            bitmap = convertUriToBitmap(uri);
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
////
////        if (bitmap != null) {
////            bitmap = editBitmap(bitmap, "Huynh Ngoc Chau");
////        }
////
////        if (bitmap != null) {
////            uri = convertBitmapToUri(bitmap);
////        }
//
//            Intent intent = new Intent(Intent.ACTION_SEND);
//            intent.putExtra(Intent.EXTRA_STREAM, uri);
//            intent.putExtra(Intent.EXTRA_TEXT, urlToShare);
//            intent.setType("image/*");
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//            boolean facebookAppFound = false;
//            List<ResolveInfo> matches = getPackageManager().queryIntentActivities(intent, 0);
//            for (ResolveInfo info : matches) {
//                if (info.activityInfo.packageName.toLowerCase().startsWith("com.facebook.katana")) {
//                    intent.setPackage(info.activityInfo.packageName);
//                    facebookAppFound = true;
//                    break;
//                }
//            }
//
//            if (!facebookAppFound) {
//                String sharerUrl = "https://www.facebook.com/sharer/sharer.php?u=" + urlToShare;
//                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(sharerUrl));
//            }
//            startActivity(intent);
//        }
//
//        private void saveImageFileToSDCard(Bitmap bitmap, String filename) {
//            File outputFile = new File(Environment.getExternalStorageDirectory(), filename + ".jpg");
//            try {
//                FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
//                fileOutputStream.flush();
//                fileOutputStream.close();
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        private Uri convertBitmapToUri(Bitmap bitmap) {
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//            String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "Kingpes", null);
//            return Uri.parse(path);
//        }
//
//        private Bitmap convertUriToBitmap(Uri uri) throws IOException {
//            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
//            return bitmap;
//        }
//
//        private Uri getUriFromSDCard(String filename) {
//            String externalStorageDirectory = Environment.getExternalStorageDirectory().toString();
//            String myDir = externalStorageDirectory + "/DCIM/Camera";
//
//            Uri uri = Uri.parse("file:///" + myDir + "/" + filename + ".jpg");
//            return uri;
//        }
//
//        private String getPathImageFileFromSDCard(String filename) {
//            String path = Environment.getExternalStorageDirectory().toString();
//            path += "/DCIM/Camera/" + filename + ".jpg";
//            return path;
//        }
//
//        private Bitmap getImageFileFromSDCard(String filename) {
//            Bitmap bitmap = null;
//            File imageFile = new File(getPathImageFileFromSDCard(filename));
//            try {
//                FileInputStream fis = new FileInputStream(imageFile);
//                bitmap = BitmapFactory.decodeStream(fis);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//            return bitmap;
//        }
//
//        private Bitmap editBitmap(Bitmap bitmap, String txt) {
//
//            Bitmap.Config config = bitmap.getConfig();
//            int width = bitmap.getWidth();
//            int height = bitmap.getHeight();
//
//            Bitmap newImage = Bitmap.createBitmap(width, height, config);
//
//            Canvas canvas = new Canvas(newImage);
//            canvas.drawBitmap(bitmap, 0, 0, null);
//
//            Paint paint = new Paint();
//            paint.setColor(Color.RED);
//            paint.setStyle(Paint.Style.FILL);
//            paint.setTextSize(50);
//            canvas.drawText(txt, height / 2, width / 2, paint);
//
//            return newImage;
//        }
//    }


//    private Bitmap getImageFileFromSDCard(String filename) {
//        Bitmap bitmap = null;
//        File imageFile = new File(Environment.getExternalStorageDirectory() + filename);
//        try {
//            FileInputStream fis = new FileInputStream(imageFile);
//            bitmap = BitmapFactory.decodeStream(fis);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }



//    private void storeCameraPhotoInSDCard(Bitmap bitmap, String currentDate) {
//        File outputFile = new File(Environment.getExternalStorageDirectory(), "photo_" + currentDate + ".jpg");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
//            fileOutputStream.flush();
//            fileOutputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


//    private String getPathImage(Uri uri) {
//        String[] path = {MediaStore.Images.Media.DATA};
//        @SuppressWarnings("deprecation")
//        Cursor cursor = managedQuery(uri, path, null, null, null);
//        int colume_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(colume_index);
//    }
}
