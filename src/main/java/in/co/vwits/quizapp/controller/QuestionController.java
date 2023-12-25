package in.co.vwits.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.vwits.quizapp.model.Question;
import in.co.vwits.quizapp.service.QuestionService;

@RestController
@RequestMapping("question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		return questionService.getAllQuestions();
	}
	
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
		return questionService.getQuestionByCategory(category);
	}
	
	@PostMapping("add")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return this.questionService.addQuestion(question);
	}
	
	@PutMapping("update")
	public String upadateQuestion(@RequestBody Question question) {
		this.questionService.updateQuestion(question);
		return "Updated";
	}
	
	@DeleteMapping("delete/{id}")
	public String deleteQuestion(@PathVariable Integer id) {
		this.questionService.deleteQuestion(id);
		return "Deleted";
	}
}
