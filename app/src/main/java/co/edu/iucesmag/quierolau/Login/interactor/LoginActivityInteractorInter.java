package co.edu.iucesmag.quierolau.Login.interactor;

import android.widget.EditText;

import co.edu.iucesmag.quierolau.Login.model.Login;

public interface LoginActivityInteractorInter {
    void isValidateFormData(EditText editTextUser, EditText editTextPass);
    void signIn(String user, String pass);
    void signInResut(Login login);
    void signInResutAdmin();
}
