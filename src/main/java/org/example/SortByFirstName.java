package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortByFirstName implements CandidateFilter {

    @Override
    public List<Candidate> apply(List<Candidate> all) {
        return all.stream()
                .sorted(Comparator.comparing(
                        c -> c.getFirstName().toLowerCase()
                ))
                .collect(Collectors.toList());
    }
}
