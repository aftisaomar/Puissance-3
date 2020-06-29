package com.coffre.mami.puissance4.controleur;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.coffre.mami.puissance4.R;

import java.util.ArrayList;

public class grilleJeu extends AppCompatActivity implements View.OnClickListener{


    private TextView nom1, nom2;
    private Button[][] matrice= new Button[4][5];
    private int quijoue=0; //bleu
    private int nom1Score, nom2Score;
    private  ArrayList<String> ar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille_jeu);

         ar1=getIntent().getExtras().getStringArrayList("keyName");

        nom1= findViewById(R.id.nom1); // jaune
        nom2=findViewById(R.id.nom2);// bleu
        nom1Score=0;
        nom2Score=0;
        nom1.setText(ar1.get(0)+":"+nom1Score);
        nom2.setText(ar1.get(1)+":"+nom2Score);


        //parcours de notre matrice et assigniation avec nos button dans nos layout

        for(int i=0;i<4;i++){


            for(int j=0;j<4;j++){

                String buttonID = "b" + i + "" + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                matrice[i][j]= findViewById(resID);
                matrice[i][j].setTag(1);
                matrice[i][j].setOnClickListener(this);
                matrice[i][j].setEnabled(false);
                if(i==3)
                    matrice[3][j].setEnabled(true);


            }
        }









    }

    @Override
    public void onClick(View v) {




        if((quijoue % 2)!=0) //Jaune
        {v.setBackgroundResource(R.drawable.roundjaune);
         v.setEnabled(false);
         v.setTag(2);
        }


        if((quijoue % 2)==0) //bleu
        {v.setBackgroundResource(R.drawable.roundbleu);
            v.setEnabled(false);
            v.setTag(-2);

        }

        int g = gagner();
       if( g!=0)
       {
            //Afficher que quelqu'un a gagner

          if(g== -2) {

              nom2Score++;
              nom2.setText(ar1.get(1)+":"+nom2Score);
          }

          if(g==2){

              nom1Score++;
              nom1.setText(ar1.get(0)+":"+nom1Score);


          }


          renitialiser();
       }

        quijoue++;



    }




    public int gagner(){

        for(int i=0;i<4;i++){

            for(int j=0;j<4;j++){

                if(((int)matrice[i][j].getTag())!=1){

                    Activer(i,j);

                    if(ligneGauche(i,j) || ligneDroite(i,j) || haut(i,j) || bas(i,j) || diagonalGauche(i,j) || diagonalDroite(i,j)
                            || diagonalBasDroite(i,j) || diagonalBasGauche(i,j))
                        return (int)matrice[i][j].getTag();

                }

            }


        }

        return 0;
    }


    public boolean ligneGauche(int i, int j){

        if( (j-2)<0 ) return false;

        return (((int)matrice[i][j].getTag() + (int)matrice[i][j-1].getTag() + (int)matrice[i][j-2].getTag())==(3*(int)matrice[i][j].getTag()));
    }

    public boolean ligneDroite(int i, int j){


        if ( (j+2)>3) return false;


        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i][j+1].getTag()+(int)matrice[i][j+2].getTag())== (3*(int)matrice[i][j].getTag()));
    }


    public  boolean haut(int i, int j){

    if( (i-2)<0) return false;

        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i-1][j].getTag()+(int)matrice[i-2][j].getTag())== (3*(int)matrice[i][j].getTag()));

    }



    public  boolean bas(int i, int j){

        if( (i+2)>3) return false; // i==0

        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i+1][j].getTag()+(int)matrice[i+2][j].getTag())== (3*(int)matrice[i][j].getTag()));

    }



     public boolean diagonalGauche(int i, int j){

        if ( (i-2)<0 || (j-2)<0) return false;

         return ( ((int)matrice[i][j].getTag()+ (int)matrice[i-1][j-1].getTag()+(int)matrice[i-2][j-2].getTag())== (3*(int)matrice[i][j].getTag()));


     }


    public boolean diagonalDroite(int i, int j){

        if ( (i-2)<0 || (j+2)>3) return false;

        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i-1][j+1].getTag()+(int)matrice[i-2][j+2].getTag())== (3*(int)matrice[i][j].getTag()));


    }

    public boolean diagonalBasGauche(int i, int j){

        if((i+2)>3 || (j-2)<0) return false;

        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i+1][j-1].getTag()+(int)matrice[i+2][j-2].getTag())== (3*(int)matrice[i][j].getTag()));



    }

    public boolean diagonalBasDroite(int i, int j){

        if((i+2)>3 || (j+2)>3) return false;

        return ( ((int)matrice[i][j].getTag()+ (int)matrice[i+1][j+1].getTag()+(int)matrice[i+2][j+2].getTag())== (3*(int)matrice[i][j].getTag()));



    }


    public void renitialiser(){

        quijoue=-1;
        for(int i=0;i<4;i++){


            for(int j=0;j<4;j++){
                matrice[i][j].setTag(1);
                //matrice[i][j].setOnClickListener(this);
                matrice[i][j].setEnabled(false);
                matrice[i][j].setBackgroundResource(R.drawable.round);
                if(i==3)
                    matrice[3][j].setEnabled(true);


            }
        }




    }



    public void Activer(int i, int j){

        if((i-1)>=0)
        matrice[i-1][j].setEnabled(true);



    }
}


