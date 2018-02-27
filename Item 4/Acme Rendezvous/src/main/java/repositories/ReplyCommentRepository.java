
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ReplyComment;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Integer> {

	@Query("select c.repliesComments from Rende r join r.comments c where r.id =?1")
	public Collection<ReplyComment> selectByRendeId(int id);
}
