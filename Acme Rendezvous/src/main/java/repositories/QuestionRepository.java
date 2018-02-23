
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	@Query("select rQ.question from User u join u.repliesQuestions rQ where u.id =?1")
	public Collection<Question> repliedByUserId(int userId);

}
