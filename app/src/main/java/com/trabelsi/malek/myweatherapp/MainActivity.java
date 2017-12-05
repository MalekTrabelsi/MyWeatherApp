package com.trabelsi.malek.myweatherapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.trabelsi.malek.myweatherapp.Model.CodePostal;

public class MainActivity extends AppCompatActivity {

    private TextView mWelcomeText;
    private Button mCurrentPositionBtn;
    private Button mCodePostalBtn;
    private EditText mSaisieCodePostal;
    private CodePostal mCodePostal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWelcomeText = (TextView) findViewById(R.id.activity_main_welcome_text);
        mCurrentPositionBtn = (Button) findViewById(R.id.activity_main_current_position_btn);
        mCodePostalBtn = (Button) findViewById(R.id.activity_main_code_postal_btn);
        mSaisieCodePostal = (EditText) findViewById(R.id.activity_main_saisie_code_postal_edit);
        mCodePostal = new CodePostal();

        mCodePostalBtn.setEnabled(false);
        mSaisieCodePostal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                //Le bouton n'est activé que si le champ de saisie du code postal n'est pas vide
                mCodePostalBtn.setEnabled(s.toString().length()!=0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        //Méthode pour détecter l'appui sur le bouton de la météo dans la position de l'utilisateur de l'application (GPS)
        mCurrentPositionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Relier la nouvelle activité CurrentPositionActivity à l'appui sur le bouton mCurrentPositionBtn
                Intent CurrentPositionIntent = new Intent (MainActivity.this,CurrentPositionActivity.class);
                startActivity(CurrentPositionIntent);
            }
        });

        //Méthode pour détecter l'appui sur le bouton de la météo dans la région donnée par le code postal saisi
        mCodePostalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Mémorisation du code postal saisi
                String codePostal = mSaisieCodePostal.getText().toString();
                mCodePostal.setCodePostal(codePostal);

                //Relier la nouvelle activité CodePostalActivity à l'appui sur le bouton mCodePostalBtn
                Intent CodePostalIntent = new Intent(MainActivity.this,CodePostalActivity.class);
                startActivity(CodePostalIntent);
            }
        });
    }
}