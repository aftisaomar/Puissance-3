package com.coffre.mami.puissance4.controleur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.coffre.mami.puissance4.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText bleu, jaune;
    private Button play;
    private int nombleu=0;
    private int nomjaune=0;
    private ArrayList<String> nom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bleu= findViewById(R.id.nomBleu);
        jaune=findViewById(R.id.nomJaune);
        play=findViewById(R.id.play);
        nom= new ArrayList<String>();
        play.setEnabled(false);


        bleu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length() !=0) {nombleu=1;}

                if((nombleu==1) && (nomjaune==1)) {play.setEnabled(true);}

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });



        jaune.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(s.toString().length()!=0) {nomjaune=1;

                }
                if((nombleu==1) && (nomjaune==1)) play.setEnabled(true);

            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });



        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //On lance notre deuxieme activite
                Intent jeux= new Intent(MainActivity.this, grilleJeu.class);
                nom.add(jaune.getText().toString());
                nom.add(bleu.getText().toString());
                jeux.putStringArrayListExtra("keyName", nom );
                startActivity(jeux);


            }
        });









    }
}
