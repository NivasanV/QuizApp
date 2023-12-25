package in.co.vwits.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.co.vwits.quizapp.model.Question;
import in.co.vwits.quizapp.model.QuestionWrapper;
import in.co.vwits.quizapp.model.Quiz;
import in.co.vwits.quizapp.model.Response;
import in.co.vwits.quizapp.repository.QuestionDao;
import in.co.vwits.quizapp.repository.QuizDao;


@Service
public class QuizService {

	@Autowired
	private QuizDao quizDao;
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numQ);
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Question> questionsFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList<>();
		for(Question q : questionsFromDb) {
			QuestionWrapper qw = new QuestionWrapper(
					q.getId(), 
					q.getQuestionTitle(), 
					q.getOption1(), 
					q.getOption2(), 
					q.getOption3(), 
					q.getOption4()
			);
			questionsForUsers.add(qw);
		}
		
		return new ResponseEntity<>(questionsForUsers, HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz = quizDao.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for(Response response : responses) {
			System.out.println("--------");
			System.out.println(response.getId());
			System.out.println(questions.get(i).getId());
			System.out.println(response.getResponse());
			System.out.println(questions.get(i).getRightAnswer());
			if(response.getResponse().equals(questions.get(i).getRightAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<Integer>(right, HttpStatus.OK);
	}
}

