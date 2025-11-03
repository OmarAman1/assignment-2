package org.example;

import java.util.List;

public interface CandidateFilter {
    List<Candidate> apply(List<Candidate> all);
}
