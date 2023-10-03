package Coursework2ExamQuestionGenerator.Service;

import Coursework2ExamQuestionGenerator.Exception.QuestionAlreadyExistsException;
import Coursework2ExamQuestionGenerator.Exception.QuestionNotFoundException;
import Coursework2ExamQuestionGenerator.Model.Question;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class JavaQuestionServiceTest {
    private final QuestionService questionService = new JavaQuestionService();

    @BeforeEach
    void setUp() {
        questionService.add(new Question(" question1", " answer1"));
        questionService.add(new Question(" question2", " answer2"));
        questionService.add(new Question(" question3", " answer3"));
        questionService.add(new Question(" question4", " answer4"));
    }

    @AfterEach
    void afterEach() {
        Collection<Question> questions = questionService.getAll();
        questions.forEach(question -> questionService.remove(question));
    }

    @Test
    void addPositiveTest() {
        assertThat(questionService.getAll()).hasSize(4);
        Question expected5 = new Question(" question5", " answer5");
        Question expected6 = new Question(" question6", " answer6");
        assertThat(questionService.add(expected5.getQuestion(), expected5.getAnswer())).isEqualTo(expected5);
        assertThat(questionService.add(expected6)).isEqualTo(new Question(" question6", " answer6"));

        assertThat(questionService.getAll()).contains(expected5);
        assertThat(questionService.getAll()).contains(expected6);
        assertThat(questionService.getAll()).hasSize(6);
    }

    @Test
    void addNegativeTest() {
        Question expected5 = new Question(" question5", " answer5");
        questionService.add(expected5);
        assertThatExceptionOfType(QuestionAlreadyExistsException.class)
                .isThrownBy(() -> questionService.add(expected5));
    }

    @Test
    void removePositiveTest() {
        Question expected1 = new Question(" question1", " answer1");
        assertThat(questionService
                .remove(expected1))
                .isEqualTo(expected1);
    }

    @Test
    void removeNegativeTest() {
        Question expected7 = new Question(" question7", " answer7");
        assertThatExceptionOfType(QuestionNotFoundException.class)
                .isThrownBy(() -> questionService.remove(expected7));
    }

    @Test
    void getAll() {
        Assertions.assertThat(questionService.getAll())
                .hasSize(4)
                .containsExactlyInAnyOrder(
                        new Question(" question1", " answer1"),
                        new Question(" question2", " answer2"),
                        new Question(" question3", " answer3"),
                        new Question(" question4", " answer4")
                );
    }

    @Test
    void getRandomQuestionTest() {
        assertThat(questionService.getRandomQuestion()).isIn(questionService.getAll());
    }
}