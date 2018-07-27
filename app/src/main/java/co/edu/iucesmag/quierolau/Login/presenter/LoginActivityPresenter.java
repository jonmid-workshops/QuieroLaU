package co.edu.iucesmag.quierolau.Login.presenter;

import android.widget.EditText;

import co.edu.iucesmag.quierolau.Login.interactor.LoginActivityInteractor;
import co.edu.iucesmag.quierolau.Login.interactor.LoginActivityInteractorInter;
import co.edu.iucesmag.quierolau.Login.model.Login;
import co.edu.iucesmag.quierolau.Login.view.LoginActivityView;

public class LoginActivityPresenter implements LoginActivityPresenterInter {

    private LoginActivityView loginActivityView;
    private LoginActivityInteractorInter loginActivityInteractorInter;

    public LoginActivityPresenter(LoginActivityView loginActivityView) {
        this.loginActivityView = loginActivityView;
        loginActivityInteractorInter = new LoginActivityInteractor(this);
    }

    @Override
    public void isValidateFormData(EditText editTextUser, EditText editTextPass) {
        loginActivityInteractorInter.isValidateFormData(editTextUser, editTextPass);
    }

    @Override
    public void isValidateFormResult(Boolean status) {
        loginActivityView.isValidateFormResult(status);
    }

    @Override
    public void signIn(String user, String pass) {
        loginActivityInteractorInter.signIn(user, pass);
    }

    @Override
    public void signInResut(Login login) {
        loginActivityView.signInResut(login);
    }
}
