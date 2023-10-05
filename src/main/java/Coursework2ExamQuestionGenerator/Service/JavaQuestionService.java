package Coursework2ExamQuestionGenerator.Service;

import Coursework2ExamQuestionGenerator.Exception.QuestionAlreadyExistsException;
import Coursework2ExamQuestionGenerator.Exception.QuestionNotFoundException;
import Coursework2ExamQuestionGenerator.Model.Question;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService {
    private final Set<Question> questions;
    private final Random random;

    public JavaQuestionService() {
        this.questions = new HashSet<>();
        this.random = new Random();
    }

    @Override
    public Question add(String question, String answer) {
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (!questions.add(question)) {
            throw new QuestionAlreadyExistsException();
        }
        return question;
    }

    @Override
    public Question remove(Question question) {
        if (!questions.remove(question))
            throw new QuestionNotFoundException();
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        // return questions;
        return new HashSet<>(questions);
    }

    @Override
    public Question getRandomQuestion() {
        int numberOfMembersInTheSetQuestions = questions.size();
        int randomNumber = random.nextInt(numberOfMembersInTheSetQuestions);
        List<Question> questionList = new ArrayList<>(questions);
        return questionList.get(randomNumber);
    }
}