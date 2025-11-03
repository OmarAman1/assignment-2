package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class CandidateService {

    private static final Logger logger =
            LoggerFactory.getLogger(CandidateService.class);

    private final CandidateRepository repository;

    // DIP: vi tar emot ett interface, inte en konkret klass
    public CandidateService(CandidateRepository repository) {
        this.repository = repository;
    }

    public void addCandidate(Candidate candidate) {
        logger.info("Försöker lägga till kandidat: {}", candidate);
        repository.addCandidate(candidate);
        logger.info("Kandidat tillagd.");
    }

    public void removeCandidate(String firstName) {
        logger.info("Försöker ta bort kandidat med förnamn: {}", firstName);
        repository.removeCandidateByFirstName(firstName);
        logger.info("Borttagning utförd (om kandidaten fanns).");
    }

    public List<Candidate> getAll() {
        logger.debug("Hämtar alla kandidater");
        return repository.getAllCandidates();
    }

    // Använd ett filter-objekt (OCP: vi kan lägga till fler utan att röra koden här)
    public List<Candidate> applyFilter(CandidateFilter filter) {
        logger.debug("Filtrerar kandidater med {}", filter.getClass().getSimpleName());
        return filter.apply(repository.getAllCandidates());
    }

    // Transformation: vi mappar objekt → strängar (krav 5)
    public List<String> getSummaries() {
        logger.debug("Skapar text-sammanfattningar för kandidater");
        return repository.getAllCandidates().stream()
                .map(c -> c.getFirstName() + " " + c.getLastName()
                        + " - " + c.getIndustry()
                        + " (" + c.getYearsOfExperience() + " år)")
                .collect(Collectors.toList());
    }
}
