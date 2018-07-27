package co.edu.iucesmag.quierolau.Questions.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import co.edu.iucesmag.quierolau.HomeGame.view.HomeActivity;
import co.edu.iucesmag.quierolau.Questions.model.Question;
import co.edu.iucesmag.quierolau.R;

public class QuestionsActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView textViewQuestion, textViewCountQuestion;
    RadioGroup radioGroupIndex;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    Button buttonNextQuestion;
    List<Question> questionList;
    Integer sizeListQuestion = 0;
    Integer positionListQuestion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        sharedPreferences = getSharedPreferences("PreferencesQuieroLaU", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        textViewQuestion = (TextView) findViewById(R.id.textview_question);
        textViewCountQuestion = (TextView) findViewById(R.id.textview_count_question);
        radioGroupIndex = (RadioGroup) findViewById(R.id.radiogroup_index);
        radioButtonA = (RadioButton) findViewById(R.id.radio_a);
        radioButtonB = (RadioButton) findViewById(R.id.radio_b);
        radioButtonC = (RadioButton) findViewById(R.id.radio_c);
        radioButtonD = (RadioButton) findViewById(R.id.radio_d);
        buttonNextQuestion = (Button) findViewById(R.id.button_next_question);

        positionListQuestion = sharedPreferences.getInt("positionQuestion", 0);

        processQuestions();
    }

    public void processQuestions(){
        Gson gson = new Gson();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.question)));
        questionList = gson.fromJson(bufferedReader, new TypeToken<List<Question>>(){}.getType());
        sizeListQuestion = questionList.size();
        showQuestion(positionListQuestion);
    }

    public void showQuestion(Integer position){
        radioGroupIndex.clearCheck();
        textViewQuestion.setText(questionList.get(position).getTitle());
        radioButtonA.setText(questionList.get(position).getOpt_a());
        radioButtonB.setText(questionList.get(position).getOpt_b());
        radioButtonC.setText(questionList.get(position).getOpt_c());
        radioButtonD.setText(questionList.get(position).getOpt_d());

        if(position == (sizeListQuestion-1)){
            buttonNextQuestion.setText("Terminar preguntas");
        }else{
            buttonNextQuestion.setText("Siguiente pregunta");
        }

        textViewCountQuestion.setText("Pregunta: "+(position+1)+" / "+sizeListQuestion);
    }

    public void onButtonClickedNextQuestion(View view){
        /*for (Question item : questionList){
            System.out.println(item);
        }*/





        if (radioGroupIndex.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Para continuar seleccione una opción", Toast.LENGTH_SHORT).show();
        }else {
            if(positionListQuestion < (sizeListQuestion-1)){
                positionListQuestion = positionListQuestion + 1;
                editor.putInt("positionQuestion", positionListQuestion);
                editor.commit();
                showQuestion(positionListQuestion);
            }else {
                positionListQuestion = 0;
                editor.putInt("positionQuestion", 0);
                editor.commit();
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_a:
                if (checked)
                    Toast.makeText(this, "OPCION 1", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_b:
                if (checked)
                    Toast.makeText(this, "OPCION 2", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_c:
                if (checked)
                    Toast.makeText(this, "OPCION 3", Toast.LENGTH_SHORT).show();
                break;
            case R.id.radio_d:
                if (checked)
                    Toast.makeText(this, "OPCION 4", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
