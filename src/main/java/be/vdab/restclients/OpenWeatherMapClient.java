package be.vdab.restclients;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.exceptions.KanTemperatuurNietLezenException;

@Component
public class OpenWeatherMapClient implements TemperatuurClient {

    private static final Logger LOGGER = 
	    Logger.getLogger(OpenWeatherMapClient.class.getName());

    private final String uriTemplate;
    private final RestTemplate restTemplate;

    public OpenWeatherMapClient(@Value("${openWeatherMapURL}") String uriTemplate, 
	    RestTemplate restTemplate) {
	this.uriTemplate = uriTemplate;
	this.restTemplate = restTemplate;
    }

    @Override
    public BigDecimal getTemperature(String gemeente) {
	try {
	    Current current = restTemplate.getForObject(uriTemplate, Current.class, gemeente);
	    return current.temperature.value;
	} catch (RestClientException ex) {
	    LOGGER.log(Level.SEVERE, "kan temperatuur niet lezen", ex);
	    throw new KanTemperatuurNietLezenException();
	}
    }

}
