package com.example.ashwin.sharebitmapimage;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

/**
 * Created by ashwin on 22/9/17.
 */

public class ImageUtils {

    public static void shareImageBitmap(Context context, Bitmap bitmap) {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            intent.putExtra(Intent.EXTRA_TEXT, "");
            context.startActivity(Intent.createChooser(intent, "Share with"));
        } catch (Exception e) {
            Log.e("Error on sharing", e + " ");
            Toast.makeText(context, "App not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void shareImageBitmap(Context context, Bitmap bitmap, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, "Title", null);
            Uri imageUri = Uri.parse(path);

            @SuppressWarnings("unused")
            PackageInfo info = pm.getPackageInfo(packageName, PackageManager.GET_META_DATA);

            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            //intent.setPackage(packageName);
            intent.putExtra(android.content.Intent.EXTRA_STREAM, imageUri);
            intent.putExtra(Intent.EXTRA_TEXT, packageName);
            context.startActivity(Intent.createChooser(intent, "Share with"));
        } catch (Exception e) {
            Log.e("Error on sharing", e + " ");
            Toast.makeText(context, "App not Installed", Toast.LENGTH_SHORT).show();
        }
    }

}
