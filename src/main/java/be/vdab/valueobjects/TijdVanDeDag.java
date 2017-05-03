package be.vdab.valueobjects;

import java.time.LocalDateTime;

public class TijdVanDeDag {

    public static String begroetingNr() {
	int uur = LocalDateTime.now().getHour();
	String begroetingNr = "";
	if (uur > 0 && uur <= 5) {
	    begroetingNr = "begroeting1";
	} else if (uur > 6 && uur <= 11) {
	    begroetingNr = "begroeting2";
	} else if (uur > 12 && uur <= 17) {
	    begroetingNr = "begroeting3";
	} else if (uur > 18 && uur <= 23) {
	    begroetingNr = "begroeting4";
	}
	return begroetingNr;
    }

}
