package org.example;

import java.util.List;
import java.util.stream.Collectors;

public class FilterByIndustry implements CandidateFilter {

    private final String industry;

    public FilterByIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public List<Candidate> apply(List<Candidate> all) {
        return all.stream()
                .filter(c -> c.getIndustry().equalsIgnoreCase(industry))
                .collect(Collectors.toList());
    }
}
