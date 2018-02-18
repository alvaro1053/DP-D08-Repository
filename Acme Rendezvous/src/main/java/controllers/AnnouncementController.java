package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Announcement;

import services.AnnouncementService;



@Controller
@RequestMapping("/announcement")
public class AnnouncementController extends AbstractController {

	
	//Autowired
	@Autowired
	AnnouncementService announcementService;
	
	//Constructor
	public AnnouncementController(){
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
}
