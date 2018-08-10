package co.edu.iucesmag.quierolau.Admin.interactor;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.model.Student;
import co.edu.iucesmag.quierolau.Admin.presenter.ReportActivityPresenterInter;
import co.edu.iucesmag.quierolau.Admin.repository.ReportActivityRepository;
import co.edu.iucesmag.quierolau.Admin.repository.ReportActivityRepositoryInter;

public class ReportActivityInteractor implements ReportActivityInteractorInter {

    private ReportActivityPresenterInter reportActivityPresenterInter;
    private ReportActivityRepositoryInter reportActivityRepositoryInter;

    public ReportActivityInteractor(ReportActivityPresenterInter reportActivityPresenterInter) {
        this.reportActivityPresenterInter = reportActivityPresenterInter;
        reportActivityRepositoryInter = new ReportActivityRepository(this);
    }

    @Override
    public void getDataStudent() {
        reportActivityRepositoryInter.getDataStudent();
    }

    @Override
    public void showResultStudent(List<Student> studentList) {
        reportActivityPresenterInter.showResultStudent(studentList);
    }
}
