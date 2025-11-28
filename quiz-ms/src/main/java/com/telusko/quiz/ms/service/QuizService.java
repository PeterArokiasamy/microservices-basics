package com.telusko.quiz.ms.service;

//import com.telusko.quiz.ms.dao.QuestionDao;
import com.telusko.quiz.ms.Feign.QuizInterface;
import com.telusko.quiz.ms.dao.QuizDao;
import com.telusko.quiz.ms.model.QuestionWrapper;
import com.telusko.quiz.ms.model.Quiz;
import com.telusko.quiz.ms.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;



    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        /* Commented because we dont have Question service to get list of questions
        //To get list of questions from DB and give me questions limited to numQ
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);*/

        //Calling getQuestionsForQuiz of Question ms
        List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);


    }

    //Calling getQuestionsFromId of Question ms
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
        //Quiz quiz = quizDao.findById(id).get(); or
        Optional<Quiz> quiz = quizDao.findById(id);

        /*List<Questions> questionsFromDB = quiz.get().getQuestion(); //Get Question from db
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for (Question q : questionsFromDB){
            QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);*/
        List<Integer> questionIds = quiz.get().getQuestionIds();
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);
        return questions;
    }

    //Calling getScore of Question ms
    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        /*Quiz quiz = quizDao.findById(id).get(); //or
        //Optional<Quiz> quiz = quizDao.findById(id);
        List<Question> questions = quiz.getQuestion();
        int right = 0;
        int i=0;
        for(Response response : responses) {
            System.out.println("Value in the request "+response.getResponse());
            System.out.println("Value from the DB "+questions.get(i).getRightAnswer());
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                System.out.println("Incrementing..");
                right ++;
            }
            i++;
            return new ResponseEntity<>(right, HttpStatus.OK);
        }*/
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }
}
