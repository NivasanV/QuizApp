package in.co.vwits.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import in.co.vwits.quizapp.model.Question;
import in.co.vwits.quizapp.repository.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao questionDao;

	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<Question>> getQuestionByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestion(Question question) {
		this.questionDao.save(question);
		try {
			return new ResponseEntity<>("Success", HttpStatus.CREATED);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Not Added", HttpStatus.BAD_REQUEST);
	}

	public void updateQuestion(Question question) {
		this.questionDao.save(question);
	}

	public void deleteQuestion(Integer id) {
		this.questionDao.deleteById(id);
	}

}
