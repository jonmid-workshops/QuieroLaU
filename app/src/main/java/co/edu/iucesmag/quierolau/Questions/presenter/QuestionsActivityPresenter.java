package co.edu.iucesmag.quierolau.Questions.presenter;

import co.edu.iucesmag.quierolau.Questions.interactor.QuestionsActivityInteractor;
import co.edu.iucesmag.quierolau.Questions.interactor.QuestionsActivityInteractorInter;
import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;
import co.edu.iucesmag.quierolau.Questions.view.QuestionsActivityView;

public class QuestionsActivityPresenter implements QuestionsActivityPresenterInter {

    private QuestionsActivityView questionsActivityView;
    private QuestionsActivityInteractorInter questionsActivityInteractorInter;

    public QuestionsActivityPresenter(QuestionsActivityView questionsActivityView) {
        this.questionsActivityView = questionsActivityView;
        questionsActivityInteractorInter = new QuestionsActivityInteractor(this);
    }

    @Override
    public void saveQuestions(String ident, String fecini, String fecfin, String listdata) {
        questionsActivityInteractorInter.saveQuestions(ident, fecini, fecfin, listdata);
    }

    @Override
    public void saveQuestionsResult(ResponseQuestion responseQuestion) {
        questionsActivityView.saveQuestionsResult(responseQuestion);
    }
}
