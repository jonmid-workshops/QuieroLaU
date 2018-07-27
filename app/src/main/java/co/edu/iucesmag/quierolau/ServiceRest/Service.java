package co.edu.iucesmag.quierolau.ServiceRest;

import co.edu.iucesmag.quierolau.Login.model.Login;
import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Service {
    @POST(Constants.URL_POST_LOGIN)
    @FormUrlEncoded
    Call<Login> onSignIn(@Field("user") String user, @Field("pass") String pass);

    @POST(Constants.URL_POST_SAVE)
    @FormUrlEncoded
    Call<ResponseQuestion> onSaveQuestion(@Field("ident") String ident, @Field("fecini") String fecini, @Field("fecfin") String fecfin, @Field("listdata") String listdata);
}
