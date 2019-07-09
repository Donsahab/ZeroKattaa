package com.example.zerokatta;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    RelativeLayout relativeLayout, relative_layout_playagain;
    ImageView imageView_turn_player1, imageView_turn_player2;
    LinearLayout gridLayout;
    TextView textView;
    int activePlayer = 0;
    boolean gameState = true;
    private static final String TAG = "MainActivity";
    int[] stateCheck = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] checkWinner = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void loadImage(View view) {
        ImageView imageView = (ImageView) view;
        int i = Integer.parseInt((String) imageView.getTag());

        if (stateCheck[i] == 2 && gameState) {
            stateCheck[i] = activePlayer;
            imageView.setTranslationY(-1000f);


            if (activePlayer == 0) {
                imageView.setImageResource(R.drawable.ic_zero);
                activePlayer = 1;
                imageView_turn_player1.setVisibility(View.GONE);
                imageView_turn_player2.setVisibility(View.VISIBLE);
            } else {

                imageView.setImageResource(R.drawable.ic_cross);
                imageView_turn_player1.setVisibility(View.VISIBLE);
                imageView_turn_player2.setVisibility(View.GONE);
                activePlayer = 0;
            }
            imageView.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int[] winner : checkWinner) {

                if (stateCheck[winner[0]] == stateCheck[winner[1]]
                        && stateCheck[winner[1]] == stateCheck[winner[2]]
                        && stateCheck[winner[0]] != 2) {
                    gameState = false;
                    String winner_person = "Green";
                    if (stateCheck[winner[0]] == 0) {
                        winner_person = "RED";
                    }
                    imageView_turn_player1.setVisibility(View.GONE);
                    imageView_turn_player2.setVisibility(View.GONE);
                    textView.setText(winner_person + " Won the match");
                    relative_layout_playagain.setVisibility(View.VISIBLE);
                } else {
                    boolean isGameOver = true;
                    for (int state : stateCheck) {
                        if (state == 2) {
                            isGameOver = false;
                        }
                    }
                    if (isGameOver) {
                        imageView_turn_player1.setVisibility(View.GONE);
                        imageView_turn_player2.setVisibility(View.GONE);
                        textView.setText("Game Draw?");
                        relative_layout_playagain.setVisibility(View.VISIBLE);

                    }
                }
            }
        }
    }

    public void playAgain(View view) {
        imageView_turn_player1.setVisibility(View.VISIBLE);
        gameState = true;
        relative_layout_playagain.setVisibility(View.GONE);
        activePlayer = 0;
        for (int i = 0; i < 9; i++) {
            ImageView img = findViewById(R.id.activity_detailed_view).findViewWithTag(i + "");
            img.setImageResource(0);
            stateCheck[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = findViewById(R.id.relative_layout);
        gridLayout = findViewById(R.id.grid_layout);
        textView = findViewById(R.id.text);
        imageView_turn_player1 = findViewById(R.id.player1_imageview);
        imageView_turn_player2 = findViewById(R.id.player2_imageview);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        relativeLayout.getLayoutParams().height = metrics.widthPixels;
        gridLayout.getLayoutParams().height = metrics.widthPixels;
        relative_layout_playagain = findViewById(R.id.relative_layout_playagain);
        imageView_turn_player1.setVisibility(View.VISIBLE);


    }


}
