package co.edu.iucesmag.quierolau.SplashScreen.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import co.edu.iucesmag.quierolau.HomeGame.view.HomeActivity;
import co.edu.iucesmag.quierolau.Login.view.LoginActivity;
import co.edu.iucesmag.quierolau.Questions.view.QuestionsActivity;
import co.edu.iucesmag.quierolau.R;
import co.edu.iucesmag.quierolau.SplashScreen.presenter.SplashScreenActivityPresenter;
import co.edu.iucesmag.quierolau.SplashScreen.presenter.SplashScreenActivityPresenterInter;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenActivityView {

    ProgressBar progressBar;
    TextView textView;

    SharedPreferences sharedPreferences;
    SplashScreenActivityPresenterInter splashScreenActivityPresenterInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = (ProgressBar) findViewById(R.id.id_pgb_sc);
        textView = (TextView) findViewById(R.id.id_txv_sc);

        splashScreenActivityPresenterInter = new SplashScreenActivityPresenter(this);

        isOnLine(this);
    }

    public void onValidateStart(){
        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);

        Boolean flagStatusLogin = sharedPreferences.getBoolean("statusLogin", false);
        Boolean flagStartGame = sharedPreferences.getBoolean("startGame", false);
        Integer flagPositionQuestion = sharedPreferences.getInt("positionQuestion", 0);

        if (flagStatusLogin){
            if (flagStartGame){
                // Pasar a la pantalla de preguntas
                Intent intent = new Intent(this, QuestionsActivity.class);
                startActivity(intent);
            }else {
                // Pasar a la pantalla de inicio del juego
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }else {
            // Pasar a la pantalla de login
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void isOnLine(Context context) {
        splashScreenActivityPresenterInter.isOnLine(context);
    }

    @Override
    public void isOnLineResult(Boolean status) {
        if (status){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    onValidateStart();
                }
            }, 2000);
        }else {
            progressBar.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
        }
    }
}
