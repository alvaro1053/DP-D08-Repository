
package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendeService;
import services.UserService;
import controllers.AbstractController;
import domain.Rende;
import domain.User;
import forms.RendeForm;

@Controller
@RequestMapping("/rende/user")
public class RendeUserController extends AbstractController {

	// Services

	@Autowired
	private RendeService	rendeService;

	@Autowired
	private UserService		userService;


	// Constructors

	public RendeUserController() {
		super();
	}

	// Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Rende> rendes;
		final User principal = this.userService.findByPrincipal();
		final Boolean mayorDeEdad = false;
		rendes = this.rendeService.findAll();
		result = new ModelAndView("rende/list");
		result.addObject("mayor", mayorDeEdad);
		result.addObject("principal", principal);
		result.addObject("rendes", rendes);
		return result;
	}
	// Creation 
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		RendeForm rendeForm;
		rendeForm = this.rendeService.create();
		result = this.createEditModelAndView(rendeForm);
		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendeId) {
		ModelAndView result;
		Rende rende;
		rende = this.rendeService.findOne(rendeId);
		Assert.notNull(rende);

		result = this.createEditModelAndView(rende);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RendeForm rendeForm, final BindingResult binding) {
		ModelAndView result;
		Rende rende;

		rende = this.rendeService.reconstruct(rendeForm, binding);

		if (binding.hasErrors())
			result = this.createEditModelAndView(rende);
		else
			try {
				this.rendeService.save(rende);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(rende, "rende.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Rende rende, final BindingResult binding) {
		ModelAndView result;

		try {
			this.rendeService.delete(rende);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(rende, "rende.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Rende rende) {
		ModelAndView result;

		result = this.createEditModelAndView(rende, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Rende rende, final String message) {
		final ModelAndView result;
		final Collection<Rende> rendes = this.rendeService.findAll();
		domain.User principal;
		principal = this.userService.findByPrincipal();
		Assert.notNull(principal);
		Boolean permision = true;
		if (rende.getId() != 0 && (rende.getUser().getId() != principal.getId()))
			permision = false;

		result = new ModelAndView("rende/edit");
		result.addObject("permision", permision);
		result.addObject("rende", rende);
		result.addObject("rendes", rendes);
		result.addObject("message", message);
		return result;
	}

	// Ancillary methods Nuevos forms ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final RendeForm rendeForm) {
		ModelAndView result;

		result = this.createEditModelAndView(rendeForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final RendeForm rendeForm, final String message) {
		final ModelAndView result;
		final Collection<Rende> rendes = this.rendeService.findAll();

		result = new ModelAndView("rende/edit");
		result.addObject("rendeForm", rendeForm);
		result.addObject("rendes", rendes);
		result.addObject("message", message);

		return result;
	}
}
