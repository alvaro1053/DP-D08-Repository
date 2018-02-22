
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rende;

@Repository
public interface RendeRepository extends JpaRepository<Rende, Integer> {

	@Query("select u.rendes from User u where u.id = ?1")
	public Collection<Rende> selectByUserId(int id);

	@Query("select r from Rende r where r.adultOnly = false")
	public Collection<Rende> selectNotAdultRendes();

	@Query("select l from Rende r join r.linked l where r.id MEMBER OF l.linked AND r.id =?1")
	public Collection<Rende> selectLinkedById(int id);
}
