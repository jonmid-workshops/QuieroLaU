package co.edu.iucesmag.quierolau.Questions.interactor;

import co.edu.iucesmag.quierolau.Questions.presenter.QuestionsActivityPresenterInter;
import co.edu.iucesmag.quierolau.Questions.repository.QuestionsActivityRepository;
import co.edu.iucesmag.quierolau.Questions.repository.QuestionsActivityRepositoryInter;

public class QuestionsActivityInteractor implements QuestionsActivityInteractorInter {

    private QuestionsActivityPresenterInter questionsActivityPresenterInter;
    private QuestionsActivityRepositoryInter questionsActivityRepositoryInter;

    public QuestionsActivityInteractor(QuestionsActivityPresenterInter questionsActivityPresenterInter) {
        this.questionsActivityPresenterInter = questionsActivityPresenterInter;
        questionsActivityRepositoryInter = new QuestionsActivityRepository(this);
    }

    @Override
    public void saveQuestions(String ident, String fecini, String fecfin, String listdata) {
        questionsActivityRepositoryInter.saveQuestions(ident, fecini, fecfin, listdata);
    }
}
