package com.example.courserablock5;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // display the GUI defined in the activity_main.xml file
        setContentView(R.layout.activity_main);

        // if a preferences file named COLOR_PREF already exists, get it
        // otherwise, create one (when required by the associate editor)
        // data stored in this file are for the use of this app only
        SharedPreferences preferences = getSharedPreferences("COLOR_PREF", Context.MODE_PRIVATE);
        // create an editor to read and write from/to the preferences file
        final SharedPreferences.Editor editor = preferences.edit();

        // retrieve a reference to the layout defined in the activity_main.xml
        final RelativeLayout layout = (RelativeLayout)findViewById(R.id.layout);

        // if the preferences file contains a key 'colorId', retrieve the associated value
        // and use it to set the background color
        if (preferences.contains(("colorId")))
            layout.setBackgroundColor(preferences.getInt("colorId", 0));

        // retrieve a reference to the group of radio buttons defined in the activity_main.xml
        RadioGroup radioGroup_colors = (RadioGroup) findViewById(R.id.radioGroup_colors);
        // attach a listener to the radioGroup to react to the change of selected item/radio button
        radioGroup_colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int colorCode = 0;
                switch (checkedId) {
                    case R.id.radioButton_blue:
                        colorCode = Color.BLUE;
                        break;
                    case R.id.radioButton_magenta:
                        colorCode = Color.MAGENTA;
                        break;
                    case R.id.radioButton_yellow:
                        colorCode = Color.YELLOW;
                        break;
                }
                // change background color
                layout.setBackgroundColor(colorCode);
                // save selected color to the preferences file immediately
                editor.putInt("colorId", colorCode);
                editor.commit();
            }
        });
    }
}
