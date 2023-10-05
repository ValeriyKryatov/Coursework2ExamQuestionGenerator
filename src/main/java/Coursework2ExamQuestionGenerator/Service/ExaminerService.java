package Coursework2ExamQuestionGenerator.Service;

import Coursework2ExamQuestionGenerator.Model.Question;

import java.util.Collection;

public interface ExaminerService {
    Collection<Question> getQuestions(int amount);
}