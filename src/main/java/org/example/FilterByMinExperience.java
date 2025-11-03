package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByMinExperience implements CandidateFilter {

    private final int minYears;

    public FilterByMinExperience(int minYears) {
        this.minYears = minYears;
    }

    @Override
    public List<Candidate> apply(List<Candidate> all) {
        return all.stream()
                .filter(c -> c.getYearsOfExperience() >= minYears)
                .collect(Collectors.toList());
    }
}
