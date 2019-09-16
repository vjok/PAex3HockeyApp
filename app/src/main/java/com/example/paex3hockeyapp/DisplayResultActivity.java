package com.example.paex3hockeyapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayResultActivity extends AppCompatActivity {

    static final String KEY_HOMETEAM = "home_team";
    static final String KEY_HOMEGOALS = "home_goals";
    static final String KEY_AWAYTEAM = "visitor_team";
    static final String KEY_AWAYGOALS = "visitor_goals";

    TextView textViewHometeam, textViewHomegoals, textViewAwayteam, textViewAwaygoals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_result);

        textViewHometeam = findViewById(R.id.textviewhome);
        textViewHomegoals = findViewById(R.id.textviewhomescore);
        textViewAwayteam = findViewById(R.id.textviewaway);
        textViewAwaygoals = findViewById(R.id.textviewawayscore);

        Intent intent = getIntent();

        String hometeam = intent.getStringExtra(KEY_HOMETEAM);
        String homegoals = intent.getStringExtra(KEY_HOMEGOALS);
        String awayteam = intent.getStringExtra(KEY_AWAYTEAM);
        String awaygoals = intent.getStringExtra(KEY_AWAYGOALS);


        textViewHometeam.setText(hometeam);
        textViewHomegoals.setText(homegoals);
        textViewAwayteam.setText(awayteam);
        textViewAwaygoals.setText(awaygoals);
    }
}
