package be.vdab.services;

import java.util.List;
import java.util.Optional;

import be.vdab.entities.Brouwer;

public interface BrouwerService {
    
    Optional<Brouwer> read(long id);

    void create(Brouwer brouwer);
    
    List<Brouwer> findAll();
    
    List<Brouwer> findByNaam(String beginNaam);
    
}
