package co.edu.iucesmag.quierolau.Admin.presenter;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.model.Student;

public interface ReportActivityPresenterInter {
    void getDataStudent();
    void showResultStudent(List<Student> studentList);
}
