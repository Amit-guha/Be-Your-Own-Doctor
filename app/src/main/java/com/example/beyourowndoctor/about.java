package com.example.beyourowndoctor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class about extends AppCompatActivity {
    TextView tvabout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().setTitle("About App");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // android:text="@string/aboutapps"
        tvabout=findViewById(R.id.tvabout);
        tvabout.setSelected(true);
    /*    tvabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(about.this, "It's Working", Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}
