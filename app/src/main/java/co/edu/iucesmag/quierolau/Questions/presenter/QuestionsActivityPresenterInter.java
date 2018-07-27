package co.edu.iucesmag.quierolau.Questions.presenter;

import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;

public interface QuestionsActivityPresenterInter {
    void saveQuestions(String ident, String fecini, String fecfin, String listdata);
    void saveQuestionsResult(ResponseQuestion responseQuestion);
}
