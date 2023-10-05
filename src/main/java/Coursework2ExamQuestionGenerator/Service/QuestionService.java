package Coursework2ExamQuestionGenerator.Service;

import Coursework2ExamQuestionGenerator.Model.Question;

import java.util.Collection;

public interface QuestionService {

    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);

    Collection<Question> getAll();

    Question getRandomQuestion();
}