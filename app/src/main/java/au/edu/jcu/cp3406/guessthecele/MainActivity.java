package au.edu.jcu.cp3406.guessthecele;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class MainActivity extends Activity {
    private final static String TAG = "MainActivity";
    private ImageView imageView;
    private ImageView imageViewbyCode;
    private LinearLayout myLayout;
    private AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.image);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);
        assetManager = getAssets();
    }


    public void displayOneImage(View v) {
        if (imageViewbyCode != null) {
            imageViewbyCode.setVisibility(View.GONE);
        }
        imageView.setVisibility(View.VISIBLE);
        try {
            InputStream is = assetManager.open("img/image1.png");
            Bitmap bitmap = BitmapFactory.decodeStream(is);
            imageView.setImageBitmap(bitmap);
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    public void listAllImages(View v) {
        if (imageView != null) {
            imageView.setVisibility(View.GONE);
        }
        try {
            String[] imgPath = assetManager.list("celebs");
            for (int i = 0; i < imgPath.length; i++) {
                InputStream is = assetManager.open("celebs/" + imgPath[i]);
                Log.d(TAG, imgPath[i]);
                Bitmap bitmap = BitmapFactory.decodeStream(is);

                imageViewbyCode = new ImageView(this);
                imageViewbyCode.setImageBitmap(bitmap);
                LinearLayout.LayoutParams params = new LinearLayout
                        .LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                imageViewbyCode.setLayoutParams(params);
                myLayout.addView(imageViewbyCode);
            }
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}