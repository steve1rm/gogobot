package me.androidbox.gogobot;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Element;
import android.renderscript.Allocation;
import android.renderscript.ScriptIntrinsicBlur;
import android.renderscript.RenderScript;
import android.util.Log;

/**
 * Created by steve on 1/8/16.
 */
public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();
    private static final float BLUR_RADIUS = 20f;
    private static final float BITMAP_SCALE = 0.4f;

    /**
     * Take an bitmap image and create a blur effect
     * @param bitmapImage
     * @return
     */
    public static Bitmap blurImage(final Context context, final Bitmap bitmapImage) {
        if(bitmapImage == null) {
            Log.e(TAG, "Bitmap is null");
        }

        final int width = Math.round(bitmapImage.getWidth() * BITMAP_SCALE);
        final int height = Math.round(bitmapImage.getHeight() * BITMAP_SCALE);

        final Bitmap inputBitmap = Bitmap.createScaledBitmap(bitmapImage, width, height, false);
        final Bitmap outputBitmap = Bitmap.createBitmap(inputBitmap);

        final RenderScript renderScript = RenderScript.create(context);
        /* Intrinsic Gausian blur filter */
        ScriptIntrinsicBlur intrinsicBlur =
                ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));

        final Allocation tmpIn = Allocation.createFromBitmap(renderScript, bitmapImage);
        final Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        intrinsicBlur.setRadius(BLUR_RADIUS);
        intrinsicBlur.setInput(tmpIn);
        intrinsicBlur.forEach(tmpOut);

        tmpOut.copyTo(outputBitmap);

        return outputBitmap;
    }

    public static Bitmap blur(final Context context, final Bitmap image) {
        if (null == image) return null;

        Bitmap outputBitmap = Bitmap.createBitmap(image);
        final RenderScript renderScript = RenderScript.create(context);
        Allocation tmpIn = Allocation.createFromBitmap(renderScript, image);
        Allocation tmpOut = Allocation.createFromBitmap(renderScript, outputBitmap);

        //Intrinsic Gausian blur filter
        ScriptIntrinsicBlur theIntrinsic = ScriptIntrinsicBlur.create(renderScript, Element.U8_4(renderScript));
        theIntrinsic.setRadius(BLUR_RADIUS);
        theIntrinsic.setInput(tmpIn);
        theIntrinsic.forEach(tmpOut);
        tmpOut.copyTo(outputBitmap);
        return outputBitmap;
    }



}
