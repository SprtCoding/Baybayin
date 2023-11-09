package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Developer extends AppCompatActivity {
    private ImageView lhea, antoni, babas, mechelle;
    private ImageButton backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);

        init();

        Picasso.get().load(R.drawable.lhea).fit().into(lhea);
        Picasso.get().load(R.drawable.antoni).fit().into(antoni);
        Picasso.get().load(R.drawable.mechelle).fit().into(mechelle);
        Picasso.get().load(R.drawable.babas).fit().into(babas);

        backBtn.setOnClickListener(view -> {
            finish();
        });
    }

    private void init() {
        lhea = findViewById(R.id.lhea);
        antoni = findViewById(R.id.antoni);
        babas = findViewById(R.id.babas);
        mechelle = findViewById(R.id.mechelle);
        backBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}