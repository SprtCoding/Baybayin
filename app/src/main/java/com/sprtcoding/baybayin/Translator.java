package com.sprtcoding.baybayin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sprtcoding.baybayin.Translators.TagalogTranslator;

public class Translator extends AppCompatActivity {
    private EditText _tagalogText;
    private TextView _translatedText;
    private ImageView backBtn;
    boolean translatePerWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translator);
        _var();

        _tagalogText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() > 1) {
                    translatePerWord = true;
                }else {
                    translatePerWord = false;
                }
                String translatedText = TagalogTranslator.translate(_tagalogText.getText().toString().toLowerCase());
                _translatedText.setText(translatedText);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        backBtn.setOnClickListener(view -> finish());
    }

    private void _var() {
        _tagalogText = findViewById(R.id.tagalogText);
        _translatedText = findViewById(R.id.translatedText);
        backBtn = findViewById(R.id.backBtn);
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
    }
}