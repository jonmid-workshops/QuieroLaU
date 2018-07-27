package co.edu.iucesmag.quierolau.Questions.interactor;

import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;

public interface QuestionsActivityInteractorInter {
    void saveQuestions(String ident, String fecini, String fecfin, String listdata);
    void saveQuestionsResult(ResponseQuestion responseQuestion);
}
