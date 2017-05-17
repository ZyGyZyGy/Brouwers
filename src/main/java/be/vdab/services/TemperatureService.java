package be.vdab.services;

import java.math.BigDecimal;

public interface TemperatureService {

    BigDecimal getTemperature(String gemeente);
    
}
