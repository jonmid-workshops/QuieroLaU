package co.edu.iucesmag.quierolau.Login.repository;

import android.util.Log;

import com.google.gson.Gson;

import co.edu.iucesmag.quierolau.Login.interactor.LoginActivityInteractorInter;
import co.edu.iucesmag.quierolau.Login.model.Login;
import co.edu.iucesmag.quierolau.ServiceRest.RestApiAdapter;
import co.edu.iucesmag.quierolau.ServiceRest.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityRepository implements LoginActivityRepositoryInter {

    private LoginActivityInteractorInter loginActivityInteractorInter;
    Login loginList;

    public LoginActivityRepository(LoginActivityInteractorInter loginActivityInteractorInter) {
        this.loginActivityInteractorInter = loginActivityInteractorInter;
    }

    @Override
    public void signIn(String user, String pass) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getClientService();
        service.onSignIn(user, pass).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                loginList = response.body();
                loginActivityInteractorInter.signInResut(loginList);

                //String json = new Gson().toJson(loginList);
                //Log.d("LoginPost", json);
                //Log.d("LoginPost", loginList.getName());
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                //
            }
        });
    }
}
