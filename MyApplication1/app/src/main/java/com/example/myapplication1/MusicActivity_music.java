package com.example.myapplication1;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
//import android.view.View;
//import android.media.MediaParser;
//import androidx.core.graphics.Insets;
//import androidx.core.view.ViewCompat;
//import androidx.core.view.WindowInsetsCompat;

public class MusicActivity_music extends AppCompatActivity {

    Button button1,button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_music);

        button1=findViewById(R.id.button_music1);
        button2=findViewById(R.id.button_music2);

        Intent intent=new Intent(MusicActivity_music.this,MusicService.class);
        button1.setOnClickListener(view -> startService(intent));
        button2.setOnClickListener(view -> stopService(intent));
    }
}
