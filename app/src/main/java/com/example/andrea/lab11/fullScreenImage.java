package com.example.andrea.lab11;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class fullScreenImage extends AppCompatActivity {

    private Bitmap bmp;
    String deBugTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        deBugTag = this.getClass().getName();

        setContentView(R.layout.activity_full_screen_image);
        ImageView image = findViewById(R.id.full_screen_image);
        String path = getIntent().getStringExtra("path");

        if(path!=null){
            bmp = BitmapFactory.decodeFile(path);
        }else{
            try {
                byte[] byteArray = getIntent().getByteArrayExtra("image");
                bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            }
            catch (Exception e){
                Log.d(deBugTag, e.getMessage());
                onBackPressed();
            }
        }

        image.setImageBitmap(bmp);
    }
}
