package co.edu.iucesmag.quierolau.SplashScreen.presenter;

import android.content.Context;

import co.edu.iucesmag.quierolau.SplashScreen.interactor.SplashScreenActivityInteractor;
import co.edu.iucesmag.quierolau.SplashScreen.interactor.SplashScreenActivityInteractorInter;
import co.edu.iucesmag.quierolau.SplashScreen.view.SplashScreenActivityView;

public class SplashScreenActivityPresenter implements SplashScreenActivityPresenterInter {

    private SplashScreenActivityView splashScreenActivityView;
    private SplashScreenActivityInteractorInter splashScreenActivityInteractorInter;

    public SplashScreenActivityPresenter(SplashScreenActivityView splashScreenActivityView) {
        this.splashScreenActivityView = splashScreenActivityView;
        splashScreenActivityInteractorInter = new SplashScreenActivityInteractor(this);
    }

    @Override
    public void isOnLine(Context context) {
        splashScreenActivityInteractorInter.isOnLine(context);
    }

    @Override
    public void isOnLineResult(Boolean status) {
        splashScreenActivityView.isOnLineResult(status);
    }
}
