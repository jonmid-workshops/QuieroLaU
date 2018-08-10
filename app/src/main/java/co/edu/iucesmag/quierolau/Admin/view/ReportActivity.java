package co.edu.iucesmag.quierolau.Admin.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.adapter.StudentAdapter;
import co.edu.iucesmag.quierolau.Admin.model.Student;
import co.edu.iucesmag.quierolau.Admin.presenter.ReportActivityPresenter;
import co.edu.iucesmag.quierolau.Admin.presenter.ReportActivityPresenterInter;
import co.edu.iucesmag.quierolau.R;
import co.edu.iucesmag.quierolau.SplashScreen.view.SplashScreenActivity;

public class ReportActivity extends AppCompatActivity implements ReportActivityView {

    private ReportActivityPresenterInter reportActivityPresenterInter;
    CoordinatorLayout coordinatorLayoutIndex;
    ProgressBar progressBarReport;
    RecyclerView recyclerViewReport;
    Toolbar toolbarAdmin;
    SharedPreferences sharedPreferences;
    FloatingActionButton floatingActionButtonUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        reportActivityPresenterInter = new ReportActivityPresenter(this);
        coordinatorLayoutIndex = (CoordinatorLayout) findViewById(R.id.id_coo_report);
        progressBarReport = (ProgressBar) findViewById(R.id.id_pgb_report);
        recyclerViewReport = (RecyclerView) findViewById(R.id.id_rcv_report);
        recyclerViewReport.setLayoutManager(new LinearLayoutManager(this));
        toolbarAdmin = (Toolbar) findViewById(R.id.id_tb_toolbar_admin);
        floatingActionButtonUpdate = (FloatingActionButton) findViewById(R.id.id_fab_update_admin);

        floatingActionButtonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });

        setSupportActionBar(toolbarAdmin);

        loadData();
    }

    public void loadData(){
        progressBarReport.setVisibility(View.VISIBLE);
        reportActivityPresenterInter.getDataStudent();
    }

    @Override
    public void showResultStudent(List<Student> studentList) {
        progressBarReport.setVisibility(View.GONE);
        recyclerViewReport.setAdapter(new StudentAdapter(studentList, this));

        Snackbar.make(coordinatorLayoutIndex, "Los datos se actualizarón correctamente", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_menu_adm_1:
                Dialog dialog = showAlertDialog();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void inSignOut(){
        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();

        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
    }

    public Dialog showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Información");
        builder.setMessage("¿Estás segur@ de cerrar sesión?");
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.str_alert_button_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                inSignOut();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        return builder.create();
    }
}
