
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Rende;
import domain.User;

@Controller
@RequestMapping("announcement/user")
public class AnnouncementUserController extends AbstractController {

	//Services
	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private RendeService		rendeService;

	@Autowired
	private UserService			userService;


	//Constructor
	public AnnouncementUserController() {

	}

	//Edit
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Announcement announcement;

		announcement = this.announcementService.create();

		result = this.createEditModelAndView(announcement);

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Announcement announcement, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(announcement);
		else
			try {
				this.announcementService.save(announcement);
				result = new ModelAndView("redirect:../list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(announcement, "announcement.commit.error");
			}
		return result;

	}

	//Display
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Collection<Announcement> announcements;
		final User principal = this.userService.findByPrincipal();
		Boolean permisos = false;
		announcements = this.announcementService.announcementsChronologicalByUser(principal.getId());

		permisos = true;

		result = new ModelAndView("announcement/display");
		result.addObject("announcements", announcements);
		result.addObject("principal", principal);
		result.addObject("permisos", permisos);
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView result;

		result = this.createEditModelAndView(announcement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Announcement announcement, final String message) {
		final ModelAndView result;
		Collection<Rende> rendes;
		final User principal = this.userService.findByPrincipal();
		rendes = this.rendeService.findByUserId(principal.getId());
		result = new ModelAndView("announcement/edit");
		result.addObject("announcement", announcement);
		result.addObject("rendes", rendes);
		result.addObject("message", message);

		return result;
	}

}
