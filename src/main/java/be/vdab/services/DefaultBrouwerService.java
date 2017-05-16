package be.vdab.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import be.vdab.entities.Brouwer;
import be.vdab.repositories.BrouwerRepository;

@Service
@ReadOnlyTransactionalService
public class DefaultBrouwerService implements BrouwerService {

    private final BrouwerRepository brouwerRepository;

    DefaultBrouwerService(BrouwerRepository brouwerRepository) {
	this.brouwerRepository = brouwerRepository;
    }

    @Override
    @ModifyingTransactionalServiceMethod
    public void create(Brouwer brouwer) {
	brouwerRepository.save(brouwer);
    }

    @Override
    public List<Brouwer> findAll() {
	return brouwerRepository.findAll();
    }

    @Override
    public List<Brouwer> findByNaam(String beginNaam) {
	return brouwerRepository.findByNaamStartingWithOrderByNaam(beginNaam);
    }

    @Override
    public Optional<Brouwer> read(long id) {
	return Optional.ofNullable(
		brouwerRepository.findOne(id));
    }
    
}
