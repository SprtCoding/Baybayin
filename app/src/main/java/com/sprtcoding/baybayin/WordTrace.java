package com.sprtcoding.baybayin;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.sprtcoding.baybayin.Loading.LoadingDialog;

import java.util.Objects;

public class WordTrace extends AppCompatActivity {
    private CardView a_card, e_i_card, o_u_card, ba_card, ca_card, da_card
    ,ga_card, ha_card, la_card, ma_card, na_card, nga_card, pa_card, sa_card
    ,ta_card, wa_card, ya_card;
    private ImageView backBtn;
    private Uri videoURI;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_trace);
        _init();

        loadingDialog = new LoadingDialog(this);

        setClickListener();

        backBtn.setOnClickListener(view -> finish());

    }

    private void setClickListener() {
        a_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.a;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "A");
            };
            handler.postDelayed(runnable, 1500);
        });

        e_i_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.e_i;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "E/I");
            };
            handler.postDelayed(runnable, 1500);
        });

        o_u_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.o_u;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "O/U");
            };
            handler.postDelayed(runnable, 1500);
        });

        ba_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ba;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "BA");
            };
            handler.postDelayed(runnable, 1500);
        });

        da_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.da_ra;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "DA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ga_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ga;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "GA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ca_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ka;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "KA");
            };
            handler.postDelayed(runnable, 1500);
        });

        la_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.la;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "LA");
            };
            handler.postDelayed(runnable, 1500);
        });

        na_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.na;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "NA");
            };
            handler.postDelayed(runnable, 1500);
        });

        pa_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.pa;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "PA");
            };
            handler.postDelayed(runnable, 1500);
        });

        wa_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.wa;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "WA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ha_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ha;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "HA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ma_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ma;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "MA");
            };
            handler.postDelayed(runnable, 1500);
        });

        nga_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.nga;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "NGA");
            };
            handler.postDelayed(runnable, 1500);
        });

        sa_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.sa;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "SA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ta_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ta;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "TA");
            };
            handler.postDelayed(runnable, 1500);
        });

        ya_card.setOnClickListener(view -> {
            loadingDialog.show();
            Handler handler = new Handler();
            Runnable runnable = () -> {
                loadingDialog.dismiss();
                // Set the video file path or URL
                String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.ya;
                videoURI = Uri.parse(videoPath);
                showVideoDialog(videoURI, "YA");
            };
            handler.postDelayed(runnable, 1500);
        });
    }

    private void _init() {
        a_card = findViewById(R.id.a_card);
        e_i_card = findViewById(R.id.e_i_card);
        o_u_card = findViewById(R.id.o_u_card);
        ba_card = findViewById(R.id.ba_card);
        ca_card = findViewById(R.id.ca_card);
        da_card = findViewById(R.id.da_card);
        ga_card = findViewById(R.id.ga_card);
        ha_card = findViewById(R.id.ha_card);
        la_card = findViewById(R.id.la_card);
        ma_card = findViewById(R.id.ma_card);
        na_card = findViewById(R.id.na_card);
        nga_card = findViewById(R.id.nga_card);
        pa_card = findViewById(R.id.pa_card);
        sa_card = findViewById(R.id.sa_card);
        ta_card = findViewById(R.id.ta_card);
        wa_card = findViewById(R.id.wa_card);
        ya_card = findViewById(R.id.ya_card);
        backBtn = findViewById(R.id.backBtn);
    }

    @SuppressLint("MissingInflatedId")
    private void showVideoDialog(Uri uri, String title) {
        View videoDialog = LayoutInflater.from(WordTrace.this).inflate(R.layout.video_player_dialog, null);
        AlertDialog.Builder videoDialogBuilder = new AlertDialog.Builder(WordTrace.this);

        videoDialogBuilder.setView(videoDialog);

        VideoView my_video_player = videoDialog.findViewById(R.id.videoPlayer);
        ImageView close = videoDialog.findViewById(R.id.btn_close);
        TextView video_title = videoDialog.findViewById(R.id.video_title);

        //boolean isPlay;

        final AlertDialog videosDialog = videoDialogBuilder.create();

        Objects.requireNonNull(videosDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        videosDialog.setCanceledOnTouchOutside(false);

        close.setOnClickListener(view -> videosDialog.dismiss());
        video_title.setText(title);

        // Set up MediaController to control playback
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(my_video_player);
        my_video_player.setMediaController(mediaController);

        // Set the video URI to the VideoView
        my_video_player.setVideoURI(uri);

        // Start playing the video
        my_video_player.start();

        videosDialog.show();
    }
}