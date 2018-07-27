package co.edu.iucesmag.quierolau.ServiceRest;

import co.edu.iucesmag.quierolau.Login.model.Login;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {
    @POST(Constants.URL_POST_LOGIN)
    @FormUrlEncoded
    Call<Login> onSignIn(@Field("user") String user, @Field("pass") String pass);
}
