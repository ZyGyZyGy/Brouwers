package be.vdab.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import be.vdab.entities.Brouwer;
import be.vdab.valueobjects.Adres;

@Repository
public class InMemoryBrouwerRepository implements BrouwerRepository {

    private final Map<Long, Brouwer> brouwers = new ConcurrentHashMap<>();

    InMemoryBrouwerRepository() {
	brouwers.put(1L,
		new Brouwer(1L, "Jupiler", Integer.valueOf(1000), new Adres("hertsvelde", "10", 8200, "Sint-Andries")));
	brouwers.put(2L,
		new Brouwer(2L, "Leffe", Integer.valueOf(2000), new Adres("leffestraat", "52", 8000, "Brugge")));
	brouwers.put(3L,
		new Brouwer(3L, "Svyturys", Integer.valueOf(9999), new Adres("kauno", "15", 123456, "Klaipeda")));
	brouwers.put(4L,
		new Brouwer(4L, "Jupiler Light", Integer.valueOf(1000), new Adres("hertsvelde", "10", 8200, "Sint-Andries")));
    }

    @Override
    public void create(Brouwer brouwer) {
	brouwer.setBrouwerNr(Collections.max(brouwers.keySet()) + 1L);
	brouwers.put(brouwer.getBrouwerNr(), brouwer);
    }

    @Override
    public List<Brouwer> findAll() {
	return new ArrayList<>(brouwers.values());
    }

    @Override
    public List<Brouwer> findByNaam(String beginNaam) {
	List<Brouwer> brouwerList = new ArrayList<>();
	for (Brouwer brouwer : brouwers.values()) {
	    if (brouwer.getNaam().toLowerCase()
		    .startsWith(beginNaam.toLowerCase().trim())) {
		brouwerList.add(brouwer);
	    }
	}
	return brouwerList;
    }
    
    public List<Brouwer> findByNaam(char eersteLetter) {
	List<Brouwer> brouwerList = new ArrayList<>();
	for (Brouwer brouwer : brouwers.values()) {
	    if (brouwer.getNaam().toUpperCase().charAt(0) == eersteLetter) {
		brouwerList.add(brouwer);
	    }
	}
	return brouwerList;
    }

}






