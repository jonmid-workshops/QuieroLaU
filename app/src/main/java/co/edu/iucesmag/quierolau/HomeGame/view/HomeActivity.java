package co.edu.iucesmag.quierolau.HomeGame.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.edu.iucesmag.quierolau.Login.view.LoginActivity;
import co.edu.iucesmag.quierolau.Questions.view.QuestionsActivity;
import co.edu.iucesmag.quierolau.R;
import co.edu.iucesmag.quierolau.SplashScreen.view.SplashScreenActivity;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Dialog dialogProgressBar;
    TextView textViewNameStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        textViewNameStudent = (TextView) findViewById(R.id.textview_name_student);

        initializeVariables();
    }

    public void initializeVariables(){
        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        textViewNameStudent.setText(sharedPreferences.getString("name", null));

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

    public void onClickButtonSingOut(View view) {
        Dialog dialog = showAlertDialog();
        dialog.show();
    }

    public void onStartGame(){
        dialogProgressBar = showProgressBarDialog();
        dialogProgressBar.show();

        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTimeToday = formatDate.format(today);

        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("startGame", true);
        editor.putInt("positionQuestion", 0);
        editor.putString("fecIni", dateTimeToday);
        editor.commit();

        Intent intent = new Intent(this, QuestionsActivity.class);
        startActivity(intent);
    }

    public void onSingOut(){
        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
    }

    public Dialog showProgressBarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_progress_bar, null));
        builder.setCancelable(false);
        return builder.create();
    }

    public Dialog showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage("¿Estás segur@ de cerrar sesión? Recuerda que puedes jugar cuantas veces desees y así ser uno de los ganadores de un fabuloso premio.");
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.str_alert_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                onSingOut();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        return builder.create();
    }
}
