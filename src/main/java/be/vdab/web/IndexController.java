package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.valueobjects.TijdVanDeDag;

@Controller
@RequestMapping("/")
public class IndexController {

    private static final String VIEW = "index";
    
    @GetMapping
    ModelAndView index() {
	ModelAndView modelAndView = new ModelAndView(VIEW);
	modelAndView.addObject("begroetingNr", TijdVanDeDag.begroetingNr());
	return modelAndView;
    }
    
}
