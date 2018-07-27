package co.edu.iucesmag.quierolau.ServiceRest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestApiAdapter {

    public Service getClientService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(Service.class);
    }

}
