package co.edu.iucesmag.quierolau.SplashScreen.interactor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import co.edu.iucesmag.quierolau.SplashScreen.presenter.SplashScreenActivityPresenterInter;

public class SplashScreenActivityInteractor implements SplashScreenActivityInteractorInter {

    private SplashScreenActivityPresenterInter splashScreenActivityPresenterInter;

    public SplashScreenActivityInteractor(SplashScreenActivityPresenterInter splashScreenActivityPresenterInter) {
        this.splashScreenActivityPresenterInter = splashScreenActivityPresenterInter;
    }

    @Override
    public void isOnLine(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null){
            splashScreenActivityPresenterInter.isOnLineResult(true);
        }else {
            splashScreenActivityPresenterInter.isOnLineResult(false);
        }
    }
}
