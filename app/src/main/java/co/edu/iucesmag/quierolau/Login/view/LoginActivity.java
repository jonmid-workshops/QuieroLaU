package co.edu.iucesmag.quierolau.Login.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import co.edu.iucesmag.quierolau.HomeGame.view.HomeActivity;
import co.edu.iucesmag.quierolau.Login.model.Login;
import co.edu.iucesmag.quierolau.Login.presenter.LoginActivityPresenter;
import co.edu.iucesmag.quierolau.Login.presenter.LoginActivityPresenterInter;
import co.edu.iucesmag.quierolau.R;

public class LoginActivity extends AppCompatActivity implements LoginActivityView {

    SharedPreferences sharedPreferences;
    EditText editTextUsername, editTextPassword;
    Dialog dialogProgressBar;

    private LoginActivityPresenterInter loginActivityPresenterInter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextUsername = (EditText) findViewById(R.id.id_txv_login_username);
        editTextPassword = (EditText) findViewById(R.id.id_txv_login_password);

        loginActivityPresenterInter = new LoginActivityPresenter(this);
    }

    public void onClickButtonSignIn(View view) {
        loginActivityPresenterInter.isValidateFormData(editTextUsername, editTextPassword);
    }

    @Override
    public void isValidateFormResult(Boolean status) {
        if (status){
            dialogProgressBar = showProgressBarDialog();
            dialogProgressBar.show();
            loginActivityPresenterInter.signIn(editTextUsername.getText().toString(), editTextPassword.getText().toString());
        }else{
            Dialog dialog = showAlertDialog();
            dialog.show();
        }
    }

    @Override
    public void signInResut(Login login) {
        if (login.isSearchUser()){
            sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("statusLogin", login.isSearchUser());
            editor.putInt("id", login.getId());
            editor.putString("name", login.getName());
            editor.commit();

            dialogProgressBar.cancel();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else {
            dialogProgressBar.cancel();
            Toast.makeText(this, "Identificación y/o código son incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public Dialog showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_alert_title_error);
        builder.setMessage(R.string.str_alert_message_error);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.str_alert_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //
            }
        });
        return builder.create();
    }

    public Dialog showProgressBarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_progress_bar, null));
        builder.setCancelable(false);
        return builder.create();
    }
}
