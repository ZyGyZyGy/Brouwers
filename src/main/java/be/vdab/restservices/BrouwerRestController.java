package be.vdab.restservices;

import java.util.HashSet;
import java.util.Set;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.entities.Brouwer;
import be.vdab.exceptions.BrouwerNietGevondenException;
import be.vdab.services.BrouwerService;

@RestController
@RequestMapping("/brouwers")
@ExposesResourceFor(Brouwer.class)
public class BrouwerRestController {

    private final BrouwerService brouwerService;
    private final EntityLinks entityLinks;

    BrouwerRestController(BrouwerService brouwerService, EntityLinks entityLinks) {
	this.brouwerService = brouwerService;
	this.entityLinks = entityLinks;
    }

    @GetMapping("{brouwer}")
    BrouwerResource read(@PathVariable Brouwer brouwer) {
	if (brouwer == null) {
	    throw new BrouwerNietGevondenException();
	}
	return new BrouwerResource(brouwer, entityLinks);
    }

    @ExceptionHandler(BrouwerNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void brouwerNietGevonden() {
    }

    @RequestMapping(path = "{brouwer}", method = RequestMethod.OPTIONS)
    HttpHeaders options(@PathVariable Brouwer brouwer) {
	if (brouwer == null) {
	    throw new BrouwerNietGevondenException();
	}
	Set<HttpMethod> allowedMethods = new HashSet<>();
	allowedMethods.add(HttpMethod.GET);
	HttpHeaders headers = new HttpHeaders();
	headers.setAllow(allowedMethods);
	return headers;
    }

    @GetMapping(params = "beginnaam")
    BrouwersByNaamResource findByNaam(String beginnaam) {
	return new BrouwersByNaamResource(brouwerService.findByNaam(beginnaam), entityLinks);
    }

}
