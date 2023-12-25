package in.co.vwits.quizapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.co.vwits.quizapp.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer> {

}
