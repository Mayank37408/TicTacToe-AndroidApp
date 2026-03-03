package com.example.tic_tac_toe;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    boolean gameactive=true;
    // player representation
//    0-X
//    1-o
//    2-Null
    int activeplayer=0;
    int [] gamestate={2,2,2,2,2,2,2,2,2};
    int [][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameactive){
            gamereset(view);
        }
        if(gamestate[tappedImage]==2){
            gamestate[tappedImage] = activeplayer;
            img.setTranslationY(-1000f);
            if(activeplayer==0){
                img.setImageResource(R.drawable.x);
                activeplayer=1;
                TextView Status = findViewById(R.id.Status);
                Status.setText("O's Turn Tap To Play");
            }
            else{
                img.setImageResource(R.drawable.o);
                activeplayer=0;
                TextView Status = findViewById(R.id.Status);
                Status.setText("X's Turn Tap To Play");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int[] winpos:winpos){
            if(gamestate[winpos[0]]==gamestate[winpos[1]]&&gamestate[winpos[1]]==gamestate[winpos[2]]&&gamestate[winpos[0]]!=2){
                String str ;
                gameactive=false;
                if(gamestate[winpos[0]]==0){
                    str="x has won the match";
                }
                else{
                    str="O has won the match";
                }
                TextView Status =findViewById(R.id.Status);
                Status.setText(str);
            }
        }
    }
    public void gamereset(View view){
        gameactive=true;
        activeplayer=0;
        for(int i=0;i<gamestate.length;i++){
            gamestate[i]=2;
        }
        ((ImageView)findViewById(R.id.imageView21)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView22)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView23)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView24)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView25)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView26)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView27)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView28)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView29)).setImageResource(0);
        TextView Status = findViewById(R.id.Status);
        Status.setText("status");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}