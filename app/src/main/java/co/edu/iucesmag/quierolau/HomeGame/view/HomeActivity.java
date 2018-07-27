package co.edu.iucesmag.quierolau.HomeGame.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
        editor.putString("fecIni", null);
        editor.putString("fecfin", null);
        editor.putString("respuestasPersona", null);
        editor.commit();
    }

    public void onClickButtonHomeGame(View view) {
        onStartGame();
    }

    public void onStartGame(){
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeToday = formatDate.format(today);

        editor.putBoolean("startGame", true);
        editor.putInt("positionQuestion", 0);
        editor.putString("fecIni", dateTimeToday);
        editor.commit();

        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);
    }
}
