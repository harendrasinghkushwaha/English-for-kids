package com.example.language_translator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find the View that shows the numbers  category
        TextView numbers = (TextView) findViewById(R.id.number);
        //Set a clicklistener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                startActivity(numbersIntent);
            }
        });

        //find the View that shows the families  category
        TextView families = (TextView) findViewById(R.id.family);
        //Set a clicklistener on that View
        families.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familiesIntent = new Intent(MainActivity.this, FamilyActivity.class);
                startActivity(familiesIntent);
            }
        });

        //find the View that shows the colors  category
        TextView colors = (TextView) findViewById(R.id.color);
        //Set a clicklistener on that View
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsIntent = new Intent(MainActivity.this, ColorsActivity.class);
                startActivity(colorsIntent);
            }
        });
        //find the View that shows the phrases  category
        TextView phrases = (TextView) findViewById(R.id.phrase);
        //Set a clicklistener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });
    }


}