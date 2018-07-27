package co.edu.iucesmag.quierolau.Questions.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import co.edu.iucesmag.quierolau.HomeGame.view.HomeActivity;
import co.edu.iucesmag.quierolau.Questions.model.Question;
import co.edu.iucesmag.quierolau.Questions.model.ResponseQuestion;
import co.edu.iucesmag.quierolau.Questions.model.ResultQuestion;
import co.edu.iucesmag.quierolau.Questions.presenter.QuestionsActivityPresenter;
import co.edu.iucesmag.quierolau.Questions.presenter.QuestionsActivityPresenterInter;
import co.edu.iucesmag.quierolau.R;

public class QuestionsActivity extends AppCompatActivity implements QuestionsActivityView {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    TextView textViewQuestion, textViewCountQuestion;
    RadioGroup radioGroupIndex;
    RadioButton radioButtonA, radioButtonB, radioButtonC, radioButtonD;
    Button buttonNextQuestion;
    List<Question> questionList;
    Integer sizeListQuestion = 0;
    Integer positionListQuestion = 0;
    List<ResultQuestion> resultQuestionList = new ArrayList<>();
    String respuesta;

    Dialog dialogProgressBar;

    private QuestionsActivityPresenterInter questionsActivityPresenterInter;

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

        questionsActivityPresenterInter = new QuestionsActivityPresenter(this);

        positionListQuestion = sharedPreferences.getInt("positionQuestion", 0);

        if( (sharedPreferences.getString("respuestasPersona", null)) != null ){
            Gson gson = new Gson();
            resultQuestionList = gson.fromJson(
                    sharedPreferences.getString("respuestasPersona", null),
                    new TypeToken<List<ResultQuestion>>(){}.getType()
            );
        }

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
            Gson gson = new Gson();

            String tmpRes = questionList.get(positionListQuestion).getRes();
            ResultQuestion resultQuestion = new ResultQuestion();
            resultQuestion.setNropreg(questionList.get(positionListQuestion).getId());
            resultQuestion.setRespreg(respuesta);
            resultQuestion.setCorrecto((tmpRes.equals(respuesta))?true:false);
            resultQuestionList.add(resultQuestion);

            String listJson = gson.toJson(resultQuestionList);
            editor.putString("respuestasPersona", listJson);

            positionListQuestion = positionListQuestion + 1;
            editor.putInt("positionQuestion", positionListQuestion);
            editor.commit();

            if (positionListQuestion < sizeListQuestion){
                showQuestion(positionListQuestion);
            }else {
                dialogProgressBar = showProgressBarDialog();
                dialogProgressBar.show();

                Date today = Calendar.getInstance().getTime();
                SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String tmpFecFin = formatDate.format(today);
                String tmpFecIni = sharedPreferences.getString("fecIni", null);
                String tmpData = sharedPreferences.getString("respuestasPersona", null);
                String tmpId = String.valueOf(sharedPreferences.getInt("id", 0));

                questionsActivityPresenterInter.saveQuestions(tmpId, tmpFecIni, tmpFecFin, tmpData);

            }
        }
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.radio_a:
                if (checked)
                    respuesta = "a";
                break;
            case R.id.radio_b:
                if (checked)
                    respuesta = "b";
                break;
            case R.id.radio_c:
                if (checked)
                    respuesta = "c";
                break;
            case R.id.radio_d:
                if (checked)
                    respuesta = "d";
                break;
        }
    }

    public Dialog showProgressBarDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_progress_bar, null));
        builder.setCancelable(false);
        return builder.create();
    }

    @Override
    public void saveQuestionsResult(ResponseQuestion responseQuestion) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
