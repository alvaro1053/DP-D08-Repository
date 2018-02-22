
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ReplyQuestion;

@Repository
public interface ReplyQuestionRepository extends JpaRepository<ReplyQuestion, Integer> {

	@Query("select q.replyQuestions from Rende r join r.questions q where r.id =?1")
	public Collection<ReplyQuestion> selectByRendeId(int id);
}
