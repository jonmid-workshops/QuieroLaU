package co.edu.iucesmag.quierolau.Questions.repository;

import android.util.Log;

import com.google.gson.Gson;

import co.edu.iucesmag.quierolau.Questions.interactor.QuestionsActivityInteractorInter;
import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;
import co.edu.iucesmag.quierolau.ServiceRest.RestApiAdapter;
import co.edu.iucesmag.quierolau.ServiceRest.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionsActivityRepository implements QuestionsActivityRepositoryInter {

    private QuestionsActivityInteractorInter questionsActivityInteractorInter;
    ResponseQuestion responseQuestion;

    public QuestionsActivityRepository(QuestionsActivityInteractorInter questionsActivityInteractorInter) {
        this.questionsActivityInteractorInter = questionsActivityInteractorInter;
    }

    @Override
    public void saveQuestions(String ident, String fecini, String fecfin, String listdata) {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getClientServicePrueba();
        service.onSaveQuestion(ident, fecini, fecfin, listdata).enqueue(new Callback<ResponseQuestion>() {
            @Override
            public void onResponse(Call<ResponseQuestion> call, Response<ResponseQuestion> response) {
                responseQuestion = response.body();
                questionsActivityInteractorInter.saveQuestionsResult(responseQuestion);

                //String json = new Gson().toJson(responseQuestion);
                //Log.d("LoginPost", json);
            }

            @Override
            public void onFailure(Call<ResponseQuestion> call, Throwable t) {
                //
            }
        });
    }
}
