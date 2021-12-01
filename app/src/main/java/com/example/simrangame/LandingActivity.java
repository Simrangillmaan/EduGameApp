package com.example.simrangame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cuegame.R;
import com.example.cuegame.SettingActivity;
import com.example.simrangame.helper.DBHelper;

public class LandingActivity extends AppCompatActivity {
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        dbHelper = new DBHelper(this);
        test_db();
    }

    public void goClicked(View view){
        EditText nameText = findViewById(R.id.nameText);
        //get username
        String username = nameText.getText().toString().trim();
        if(username.length() == 0){
            Toast.makeText(this,
                    "Please enter a username", Toast.LENGTH_SHORT).show();
            return;
        }
        //save username
        SharedPreferences sharedPreferences =
                getSharedPreferences("items", MODE_PRIVATE);
        sharedPreferences.edit().clear().putString("username", username).apply();
        Intent intent = new Intent(this, SettingActivity.class);
        startActivity(intent);
    }


    private void test_db() {
        //test insertPlayer
        dbHelper.insertPlayer("Tony", 50, 20, "1");
        dbHelper.insertPlayer("Hellene", 70, 50, "1");
        dbHelper.insertPlayer("Sarah", 40, 60, "1");

        dbHelper.insertPlayer("Tony", 40, 50, "2");
        dbHelper.insertPlayer("Hellene", 60, 80, "2");
        dbHelper.insertPlayer("Sarah", 80, 70, "2");

        dbHelper.insertPlayer("Tony", 30, 40, "3");
        dbHelper.insertPlayer("Hellene", 60, 90, "3");
        dbHelper.insertPlayer("Sarah", 60, 100, "3");

        //test getAllPlayers
        Cursor cursor = dbHelper.getAllPlayers();
        if(cursor != null){
            cursor.moveToFirst();
            //do {
            //}while (cursor.moveToNext());
        }
    }



}