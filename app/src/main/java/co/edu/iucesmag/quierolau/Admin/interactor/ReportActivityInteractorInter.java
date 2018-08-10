package co.edu.iucesmag.quierolau.Admin.interactor;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.model.Student;

public interface ReportActivityInteractorInter {
    void getDataStudent();
    void showResultStudent(List<Student> studentList);
}
