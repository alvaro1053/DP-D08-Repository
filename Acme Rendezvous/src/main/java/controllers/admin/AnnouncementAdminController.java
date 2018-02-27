
package controllers.admin;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import controllers.AbstractController;
import domain.Announcement;

@Controller
@RequestMapping("announcement/admin")
public class AnnouncementAdminController extends AbstractController {

	//Autowired
	@Autowired
	AnnouncementService	announcementService;


	//Constructor
	public AnnouncementAdminController() {

	}

	//Save
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int AnnouncementId) {
		ModelAndView result;
		Announcement announcement;
		announcement = this.announcementService.findOne(AnnouncementId);
		Boolean SucessDelete;
		Collection<Announcement> announcements;

		try {
			this.announcementService.delete(announcement);
			SucessDelete = true;
			result = new ModelAndView("announcement/list");
			announcements = this.announcementService.findAll();
			result.addObject("announcements", announcements);
			result.addObject("SucessDelete", SucessDelete);
		} catch (final Throwable oops) {
			announcements = this.announcementService.findAll();
			result = new ModelAndView("announcement/list");
			result.addObject("announcements", announcements);
			result.addObject("message", "announcement.commit.error");
		}
		return result;

	}
}
