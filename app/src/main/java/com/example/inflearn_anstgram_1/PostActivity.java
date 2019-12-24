package com.example.inflearn_anstgram_1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidx.appcompat.app.AppCompatActivity;

public class PostActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        imageView = findViewById(R.id.iv_photo);

        Intent intent = getIntent();
        Uri photoUri = intent.getData();
//        imageView.setImageURI(photoUri);

        Glide.with(this)
                .load(photoUri)
                .into(imageView);

        /** Uri content://media/external/images/media/255 */
        Log.d("Uri ", String.valueOf(photoUri));
    }
}
