
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AnnouncementService;
import services.RendeService;
import domain.Announcement;

@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {

	//Autowired
	@Autowired
	AnnouncementService		announcementService;

	@Autowired
	RendeService			rendeService;

	@Autowired
	private ActorService	actorService;


	//Constructor
	public AnnouncementController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Announcement> announcements;

		announcements = this.announcementService.findAll();

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		return result;
	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int rendeId) {
		ModelAndView result;
		Collection<Announcement> announcements;

		final String uri = "";

		announcements = this.announcementService.announcementsChronological(rendeId);

		result = new ModelAndView("announcement/display");
		result.addObject("announcements", announcements);
		result.addObject("uri", uri);

		result.addObject("permisos", true);
		return result;
	}
}
