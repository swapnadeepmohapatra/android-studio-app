package com.example.homepc.quiz;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button submit;
    int correctAnswers = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit = findViewById(R.id.submitButton);
        submit.setOnClickListener(submitButtonOnClickListener);
    }

    private void checkQuestionOneAnswers(){
        CheckBox questionOneMario = findViewById(R.id.checkboxQuest1AnswerMario);
        CheckBox questionOneDonkey = findViewById(R.id.checkboxQuest1AnswerDonkey);
        CheckBox questionOnePortal = findViewById(R.id.checkboxQuest1AnswerPortal);
        boolean isQuestionOneMarioChecked = questionOneMario.isChecked();
        boolean isQuestionOneDonkeyChecked = questionOneDonkey.isChecked();
        boolean isQuestionOnePortalChecked = questionOnePortal.isChecked();

        if (isQuestionOneMarioChecked && isQuestionOneDonkeyChecked && !isQuestionOnePortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionTwoAnswers(){
        RadioButton radioButton1989 = findViewById(R.id.radio_1989);
        boolean isQuestionTwo1989Checked = radioButton1989.isChecked();
        if (isQuestionTwo1989Checked){
            correctAnswers += 1;
        }
    }

    private String getQuestionThreeUserInput() {
        EditText userInputLastName = findViewById(R.id.answerInputUserLastName);
        String name = userInputLastName.getText().toString();
        return name;
    }

    private void checkQuestionThreeAnswer(){
        String name = getQuestionThreeUserInput();
        if (name.trim().equalsIgnoreCase("206")){
            correctAnswers += 1;
        }
    }

    private void checkQuestionFourAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio_portal);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionFiveAnswers(){
        CheckBox questionFiveGameBoy = findViewById(R.id.checkboxQuest5GameBoy);
        CheckBox questionFivePSP = findViewById(R.id.checkboxQuestPSP);
        CheckBox questionFiveWii = findViewById(R.id.checkboxQuest5Wii);
        boolean isQuestionFiveGameBoyChecked = questionFiveGameBoy.isChecked();
        boolean isQuestionFivePSPChecked = questionFivePSP.isChecked();
        boolean isQuestionFiveWiiChecked = questionFiveWii.isChecked();

        if (isQuestionFiveGameBoyChecked && !isQuestionFivePSPChecked && isQuestionFiveWiiChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionSixAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio2_alphabet);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionSevenAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio3_microsoft);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionEightAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio4_microsoft);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionNineAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio5_alphabet);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionTenAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio6_microsoft);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkQuestionElevenAnswers(){
        RadioButton radioButtonPortal = findViewById(R.id.radio7_not);
        boolean isQuestionTwoPortalChecked = radioButtonPortal.isChecked();
        if (isQuestionTwoPortalChecked){
            correctAnswers += 1;
        }
    }

    private void checkAllQuestions(){
        checkQuestionOneAnswers();
        checkQuestionTwoAnswers();
        checkQuestionThreeAnswer();
        checkQuestionFourAnswers();
        checkQuestionFiveAnswers();
        checkQuestionSixAnswers();
        checkQuestionSevenAnswers();
        checkQuestionEightAnswers();
        checkQuestionNineAnswers();
        checkQuestionTenAnswers();
        checkQuestionElevenAnswers();
    }

    private void resetCounterCorrectAnswers(){
        correctAnswers = 0;
    }

    final View.OnClickListener submitButtonOnClickListener = new View.OnClickListener() {
        public void onClick(final View v){
            checkAllQuestions();
            Toast.makeText(MainActivity.this, "Correct Answers: " + correctAnswers + "/11",
                    Toast.LENGTH_LONG).show();
            resetCounterCorrectAnswers();
        }
    };
}