package com.example.veronschreuder.higer_lower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<ThrowDice> mThrowDice;
    private int[] mImageNames;
    private ImageView mImageView;
    private ListView mListView;
    private ArrayAdapter mAdapter;
    private Button mHigherButton;
    private Button mLowerButton;

    private TextView mTextScore;
    private TextView mTextHighScore;
    private TextView mTextWinLose;

    private int oldRoll = 1;
    private int newRoll;
    private int score = 0;
    private int highScore = 0;
    private int highScoreText = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHigherButton = findViewById(R.id.higherButton);
        mLowerButton = findViewById(R.id.lowerButton);
        mImageView = findViewById(R.id.imageView);
        mListView = findViewById(R.id.listView_main);
        mThrowDice = new ArrayList<>();

        mTextScore = findViewById(R.id.textScore);
        mTextHighScore = findViewById(R.id.textHighScore);
        mTextWinLose = findViewById(R.id.textWinLose);

        updateUI();
        mListView.setAdapter(mAdapter);

        mImageNames = new int[]{R.drawable.d1, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5, R.drawable.d6};


        mHigherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomRoll();
                isHigher();
            }
        });

        mLowerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                randomRoll();
                isLower();
            }
        });

    }

    private void randomRoll() {
        newRoll = (int) (Math.random() * 6);
        mImageView.setImageResource(mImageNames[newRoll]);

        newRoll++;
        throwDiceList();

    }

    private void throwDiceList() {
        String text = Integer.toString(newRoll);
        ThrowDice newThrow = new ThrowDice(text);
        //Add the text to the list (datamodel)
        mThrowDice.add(newThrow);
        //Tell the adapter that the data set has been modified: the screen will be refreshed.
        updateUI();
    }

    private void isHigher() {
        if(newRoll > oldRoll){
            score++;
            mTextWinLose.setText("You win");
            mTextScore.setText("Score: " + score);
            highScoreUpdate();
        } else {
            mTextWinLose.setText("You lose");
            highScoreEnd();
        }
        oldRoll = newRoll;
    }

    private void isLower() {
        if(newRoll < oldRoll){
            score++;
            mTextWinLose.setText("You win");
            mTextScore.setText("Score: " + score);
            highScoreUpdate();
        } else {
            mTextWinLose.setText("You lose");
            highScoreEnd();
        }
        oldRoll = newRoll;
    }

    private void highScoreUpdate() {
        highScore++;

        if(highScore > highScoreText) {
            highScoreText = highScore;
            mTextHighScore.setText("Highscore: " + highScoreText);
        }
    }

    private void highScoreEnd() {
        highScore = 0;
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mThrowDice);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}
