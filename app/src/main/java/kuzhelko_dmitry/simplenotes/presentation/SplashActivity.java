package kuzhelko_dmitry.simplenotes.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import kuzhelko_dmitry.simplenotes.presentation.notesList.view.NotesActivity;

/**
 * Created by kuzhe on 27.01.2018.
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, NotesActivity.class);
        startActivity(intent);
        finish();
    }
}