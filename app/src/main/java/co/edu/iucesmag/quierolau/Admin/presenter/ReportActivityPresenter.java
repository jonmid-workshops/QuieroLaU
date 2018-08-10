package co.edu.iucesmag.quierolau.Admin.presenter;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.interactor.ReportActivityInteractor;
import co.edu.iucesmag.quierolau.Admin.interactor.ReportActivityInteractorInter;
import co.edu.iucesmag.quierolau.Admin.model.Student;
import co.edu.iucesmag.quierolau.Admin.view.ReportActivityView;

public class ReportActivityPresenter implements ReportActivityPresenterInter {

    private ReportActivityView reportActivityView;
    private ReportActivityInteractorInter reportActivityInteractorInter;

    public ReportActivityPresenter(ReportActivityView reportActivityView) {
        this.reportActivityView = reportActivityView;
        reportActivityInteractorInter = new ReportActivityInteractor(this);
    }

    @Override
    public void getDataStudent() {
        reportActivityInteractorInter.getDataStudent();
    }

    @Override
    public void showResultStudent(List<Student> studentList) {
        reportActivityView.showResultStudent(studentList);
    }
}
