package co.edu.iucesmag.quierolau.Admin.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.edu.iucesmag.quierolau.Admin.model.Student;
import co.edu.iucesmag.quierolau.R;

public class StudentAdapter extends RecyclerView.Adapter {

    List<Student> studentList;
    Context context;

    public StudentAdapter(List<Student> studentList, Context context) {
        this.studentList = studentList;
        this.context = context;
    }

    // ********************************************************************************************

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
        return new ViewHolderStudent(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Student object = studentList.get(position);
        ViewHolderStudent viewHolderStudent = (ViewHolderStudent) holder;
        viewHolderStudent.textViewProgram.setText(object.getProgram());
        viewHolderStudent.textViewNameStudent.setText(object.getName());
        viewHolderStudent.textViewIdent.setText(object.getIdentification());
        viewHolderStudent.textViewAttempts.setText(object.getAttempts());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    // ********************************************************************************************

    public class ViewHolderStudent extends RecyclerView.ViewHolder{
        TextView textViewProgram, textViewNameStudent, textViewIdent, textViewAttempts;

        public ViewHolderStudent(View item) {
            super(item);

            textViewProgram = item.findViewById(R.id.id_txv_program);
            textViewNameStudent = item.findViewById(R.id.id_txv_name);
            textViewIdent = item.findViewById(R.id.id_txv_identification);
            textViewAttempts = item.findViewById(R.id.id_txv_attempts);
        }
    }
}
