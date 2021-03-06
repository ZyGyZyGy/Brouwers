package be.vdab.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.BeginnaamForm;

@Controller
@RequestMapping(path = "/brouwers", produces = MediaType.TEXT_HTML_VALUE)
public class BrouwerController {

    private static final String BROUWERS_VIEW = "brouwers/brouwers";
    private static final String BEGINNAAM_VIEW = "brouwers/beginnaam";
    private static final String TOEVOEGEN_VIEW = "brouwers/toevoegen";
    private static final String BROUWERS_OP_ALFABET = "brouwers/opAlfabet";
    private static final String REDIRECT_NA_TOEVOEGEN = "redirect:/";
    
    private static final String[] alleLetters = "A B C D E F G H I J K L N O P Q R S T U V W X Y Z".split(" ");
    private final BrouwerService brouwerService;

    BrouwerController(BrouwerService brouwerService) {
	this.brouwerService= brouwerService;
    }

    @GetMapping
    ModelAndView findAll() {
	return new ModelAndView(BROUWERS_VIEW, "brouwers", brouwerService.findAll());
    }

    @InitBinder("brouwer")
    void initBinderBrouwer(WebDataBinder binder) {
	binder.initDirectFieldAccess();
    }
    
    @GetMapping("toevoegen")
    ModelAndView createForm() {
	return new ModelAndView(TOEVOEGEN_VIEW).addObject(new Brouwer());
    }
    
    @PostMapping
    String toevoegen(@Valid Brouwer brouwer, BindingResult bindingResult) {
	if (bindingResult.hasErrors()) {
	    return TOEVOEGEN_VIEW;
	}
	brouwerService.create(brouwer);
	return REDIRECT_NA_TOEVOEGEN;
    }

    @GetMapping("opAlfabet")
    ModelAndView opAlfabet() {
	return new ModelAndView(BROUWERS_OP_ALFABET, "alleLetters", alleLetters);
    }

    @GetMapping("/opAlfabet/{letter}")
    ModelAndView opAlfabet(@PathVariable String letter) {
	ModelAndView modelAndView = new ModelAndView(BROUWERS_OP_ALFABET);
	List<Brouwer> brouwers = brouwerService.findByNaam(letter);
	modelAndView
        	.addObject("alleLetters", alleLetters)
        	.addObject("aangeklikteLetter", letter);
	if (!brouwers.isEmpty()) {
	    modelAndView.addObject("brouwers", brouwers);
	} else {
	    modelAndView.addObject("fout", "Geen brouwers gevonden");
	}
	return modelAndView;
    }

    @GetMapping("beginnaam")
    ModelAndView findMetBeginnaam() {
	return new ModelAndView(BEGINNAAM_VIEW).addObject(new BeginnaamForm());
    }

    @InitBinder("beginnaam")
    void initBinderBeginnaam(DataBinder dataBinder) {
	dataBinder.setRequiredFields("beginnaam");
    }

    @GetMapping(params = { "beginnaam" })
    ModelAndView findMetBeginnaam(@Valid BeginnaamForm beginnaam, BindingResult bindingResult) {
	ModelAndView modelAndView = new ModelAndView(BEGINNAAM_VIEW);
	if (!bindingResult.hasErrors()) {
	    List<Brouwer> brouwers = brouwerService.findByNaam(beginnaam.getBeginnaam());
	    if (!brouwers.isEmpty()) {
		modelAndView.addObject("brouwers", brouwers);
	    } else {
		modelAndView.addObject("fout", "geen brouwers gevonden");
	    }
	}
	return modelAndView;
    }
    
}










