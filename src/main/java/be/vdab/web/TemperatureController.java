package be.vdab.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.exceptions.KanTemperatuurNietLezenException;
import be.vdab.services.TemperatureService;

@Controller
@RequestMapping("temperatuur")
public class TemperatureController {

    private static final String VIEW = "brouwers/temperatuur";
    
    private final TemperatureService temperatureService;
    
    TemperatureController(TemperatureService temperatureService) {
	this.temperatureService = temperatureService;
    }
    
    @GetMapping("{gemeente}")
    ModelAndView toonTemperatuur(@PathVariable String gemeente) {
	ModelAndView modelAndView = new ModelAndView(VIEW);
	try {
	    modelAndView.addObject("gemeente", gemeente);
	    modelAndView.addObject("temperatuur", temperatureService.getTemperature(gemeente));
	} catch (KanTemperatuurNietLezenException ex) {
	    modelAndView.addObject("fout", "Kan temperatuur niet lezen");
	}
	return modelAndView;
    }
}
