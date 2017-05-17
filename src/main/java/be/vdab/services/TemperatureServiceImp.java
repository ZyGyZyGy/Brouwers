package be.vdab.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import be.vdab.restclients.TemperatuurClient;

@Service
public class TemperatureServiceImp implements TemperatureService {

    private final TemperatuurClient temperatureClient;
    
    TemperatureServiceImp(TemperatuurClient temperatureClient) {
	this.temperatureClient = temperatureClient;
    }
    
    @Override
    public BigDecimal getTemperature(String gemeente) {
	return temperatureClient.getTemperature(gemeente);
    }

    
    
}
