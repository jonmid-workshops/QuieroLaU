package co.edu.iucesmag.quierolau.Admin.repository;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.interactor.ReportActivityInteractorInter;
import co.edu.iucesmag.quierolau.Admin.model.Student;
import co.edu.iucesmag.quierolau.ServiceRest.RestApiAdapter;
import co.edu.iucesmag.quierolau.ServiceRest.Service;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReportActivityRepository implements ReportActivityRepositoryInter {

    private ReportActivityInteractorInter reportActivityInteractorInter;
    List<Student> studentList;

    public ReportActivityRepository(ReportActivityInteractorInter reportActivityInteractorInter) {
        this.reportActivityInteractorInter = reportActivityInteractorInter;
    }

    @Override
    public void getDataStudent() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Service service = restApiAdapter.getClientService();
        Call<List<Student>> students = service.getDataStudent();
        students.enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                studentList = response.body();
                reportActivityInteractorInter.showResultStudent(studentList);
            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                //
            }
        });
    }
}
