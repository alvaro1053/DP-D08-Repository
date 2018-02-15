package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ReplyComment;

@Repository
public interface ReplyCommentRepository extends JpaRepository<ReplyComment, Integer> {

}
