package co.edu.iucesmag.quierolau.Login.interactor;

import android.widget.EditText;

import co.edu.iucesmag.quierolau.Login.model.Login;
import co.edu.iucesmag.quierolau.Login.presenter.LoginActivityPresenterInter;
import co.edu.iucesmag.quierolau.Login.repository.LoginActivityRepository;
import co.edu.iucesmag.quierolau.Login.repository.LoginActivityRepositoryInter;

public class LoginActivityInteractor implements LoginActivityInteractorInter {

    private LoginActivityPresenterInter loginActivityPresenterInter;
    private LoginActivityRepositoryInter loginActivityRepositoryInter;

    public LoginActivityInteractor(LoginActivityPresenterInter loginActivityPresenterInter) {
        this.loginActivityPresenterInter = loginActivityPresenterInter;
        loginActivityRepositoryInter = new LoginActivityRepository(this);
    }

    @Override
    public void isValidateFormData(EditText editTextUser, EditText editTextPass) {
        if (editTextUser.getText().toString().trim().isEmpty() || editTextPass.getText().toString().trim().isEmpty()){
            loginActivityPresenterInter.isValidateFormResult(false);
        }else {
            loginActivityPresenterInter.isValidateFormResult(true);
        }
    }

    @Override
    public void signIn(String user, String pass) {
        loginActivityRepositoryInter.signIn(user, pass);
    }

    @Override
    public void signInResut(Login login) {
        loginActivityPresenterInter.signInResut(login);
    }
}
