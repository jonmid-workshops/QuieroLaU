package co.edu.iucesmag.quierolau.Login.presenter;

import android.widget.EditText;

import co.edu.iucesmag.quierolau.Login.model.Login;

public interface LoginActivityPresenterInter {
    void isValidateFormData(EditText editTextUser, EditText editTextPass);
    void isValidateFormResult(Boolean status);
    void signIn(String user, String pass);
    void signInResut(Login login);
    void signInResutAdmin();
}
