
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Announcement;

@Repository
public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select a from Announcement a where a.rende.id = ?1 order by a.moment desc")
	Collection<Announcement> announcementsChronological(int rendeId);

	@Query("select a from User u join u.rSVPS r join r.announcements a where u.id = ?1 order by a.moment desc")
	Collection<Announcement> announcementsChronologicalByUser(int userId);
}
