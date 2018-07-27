package co.edu.iucesmag.quierolau.HomeGame.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import co.edu.iucesmag.quierolau.Questions.view.QuestionsActivity;
import co.edu.iucesmag.quierolau.R;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        editor.putBoolean("startGame", false);
        editor.putInt("positionQuestion", 0);
        editor.commit();
    }

    public void onClickButtonHomeGame(View view) {
        onStartGame();
    }

    public void onStartGame(){
        editor.putBoolean("startGame", true);
        editor.putInt("positionQuestion", 0);
        editor.commit();

        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);
    }
}
