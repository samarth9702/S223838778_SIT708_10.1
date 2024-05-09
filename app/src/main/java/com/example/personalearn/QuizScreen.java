package com.example.personalearn;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class QuizScreen extends AppCompatActivity {
    public interface MyApiService {
        @GET("api.php?amount=5&type=multiple")
        Call<QuizQueDataModel> getDataModel();
    }

    private TextView questionText, questionNumber, scoreSummary;
    private ProgressBar progressBar;
    private RadioGroup radioGroupOptions;
    private Button buttonNext, buttonRestart;
    private List<QuizQuestion> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        // Initialize UI components
        initializeUI();

        buttonNext.setOnClickListener(v -> handleNextButtonClick());

        loadQuizData();
    }

    private void initializeUI() {
        questionText = findViewById(R.id.questionText);
        questionNumber = findViewById(R.id.questionNumber);
        progressBar = findViewById(R.id.progressBar);
        radioGroupOptions = findViewById(R.id.radioGroupOptions);
        buttonNext = findViewById(R.id.buttonNext);
        scoreSummary = findViewById(R.id.scoreSummary);  // Ensure this ID is correct in your layout file
        buttonRestart = findViewById(R.id.restartButton); // Ensure this ID is correct in your layout file

        buttonRestart.setOnClickListener(v -> finish()); // Finish activity to return to main screen
    }

    private void handleNextButtonClick() {
        RadioButton selectedButton = findViewById(radioGroupOptions.getCheckedRadioButtonId());
        if (selectedButton != null && questions != null && !questions.isEmpty()) {
            if (selectedButton.getText().toString().equals(questions.get(currentQuestionIndex).getCorrectAnswer())) {
                score++;
            }
        }

        if (currentQuestionIndex < questions.size() - 1) {
            currentQuestionIndex++;
            displayQuestion(questions.get(currentQuestionIndex));
        } else {
            showFinalScore();
        }
    }

    private void displayQuestion(QuizQuestion question) {
//        progressBar.setVisibility(View.GONE);
        questionText.setText(question.getQuestion());
        questionNumber.setText("Question #" + (currentQuestionIndex + 1));
        radioGroupOptions.removeAllViews();

        List<String> options = new ArrayList<>(question.getIncorrectAnswers());
        options.add(question.getCorrectAnswer());
        Collections.shuffle(options);

        for (String option : options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setButtonTintList(getColorStateList(R.color.black));
            radioButton.setTextColor(getResources().getColor(R.color.black));
            radioGroupOptions.addView(radioButton);
        }
        radioGroupOptions.clearCheck(); // Clear any previous selection
    }

    private void showFinalScore() {
        progressBar.setVisibility(View.GONE);
        questionText.setVisibility(View.GONE);
        radioGroupOptions.setVisibility(View.GONE);
        questionNumber.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);

        scoreSummary.setText("Quiz completed! Your score: " + score + "/" + questions.size());
        scoreSummary.setVisibility(View.VISIBLE);
        buttonRestart.setVisibility(View.VISIBLE);
        buttonRestart.setText("Return to Main Screen");
    }

    private void loadQuizData() {
        progressBar.setVisibility(View.GONE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://opentdb.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyApiService myApiService = retrofit.create(MyApiService.class);

        myApiService.getDataModel().enqueue(new Callback<QuizQueDataModel>() {
            @Override
            public void onResponse(Call<QuizQueDataModel> call, Response<QuizQueDataModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    questions = response.body().getResults();
                    if (!questions.isEmpty()) {
                        displayQuestion(questions.get(currentQuestionIndex));
                    } else {
                        questionText.setText("No questions available.");
                        buttonNext.setEnabled(false);
                    }
                } else {
                    questionText.setText("Failed to load questions. Response Error: " + response.code());
                    buttonNext.setEnabled(false);
                }
            }
            @Override
            public void onFailure(Call<QuizQueDataModel> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                questionText.setText("Failed to load quiz data: " + t.getMessage());
                buttonNext.setEnabled(false);
            }
        });
    }
}