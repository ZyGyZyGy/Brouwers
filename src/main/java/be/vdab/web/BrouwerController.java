package be.vdab.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;

@Controller
@RequestMapping("/brouwers")
public class BrouwerController {

    private static final String BROUWERS_VIEW = "brouwers/brouwers";
    private static final String BEGINNAAM_VIEW = "brouwers/beginnaam";
    private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
    private static final String BROUWERS_OP_ALFABET = "brouwers/opAlfabet";
    private static final char[] alleLetters = "ABCDEFGHIJKLNOPQRSTUVWXYZ".toCharArray();
    private final BrouwerService brouwerService;

    BrouwerController(BrouwerService brouwerService) {
	this.brouwerService= brouwerService;
    }

    @GetMapping
    ModelAndView findAll() {
	return new ModelAndView(BROUWERS_VIEW, "brouwers", brouwerService.findAll());
    }

    @GetMapping("beginnaam")
    String findMetBeginnaam() {
	return BEGINNAAM_VIEW;
    }

    @GetMapping("toevoegen")
    String brouwersToevoegen() {
	return TOEVOEGEN_VIEW;
    }

    @GetMapping("opAlfabet")
    ModelAndView opAlfabet() {
	return new ModelAndView(BROUWERS_OP_ALFABET, "alleLetters", alleLetters);
    }
    
    @GetMapping("/opAlfabet/{letter}")
    ModelAndView opAlfabet(@PathVariable char letter) {
	ModelAndView modelAndView = new ModelAndView(BROUWERS_OP_ALFABET);
	List<Brouwer> brouwers = brouwerService.findByNaam(letter);
	modelAndView
		.addObject("alleLetters", alleLetters)
		.addObject("brouwers", brouwers);
	return modelAndView;
    }

}
