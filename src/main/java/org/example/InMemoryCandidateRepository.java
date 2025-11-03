package org.example;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InMemoryCandidateRepository implements CandidateRepository {

    private final List<Candidate> candidates = new ArrayList<>();

    @Override
    public void addCandidate(Candidate candidate) {
        candidates.add(candidate);
    }

    @Override
    public void removeCandidateByFirstName(String firstName) {
        Iterator<Candidate> iterator = candidates.iterator();
        while (iterator.hasNext()) {
            Candidate c = iterator.next();
            if (c.getFirstName().equalsIgnoreCase(firstName)) {
                iterator.remove();
                return;
            }
        }
    }

    @Override
    public List<Candidate> getAllCandidates() {
        // Returnera en kopia för att skydda interna listan
        return new ArrayList<>(candidates);
    }
}
