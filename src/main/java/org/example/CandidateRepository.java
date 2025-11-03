package org.example;

import java.util.List;

public interface CandidateRepository {
    void addCandidate(Candidate candidate);
    void removeCandidateByFirstName(String firstName);
    List<Candidate> getAllCandidates();
}
