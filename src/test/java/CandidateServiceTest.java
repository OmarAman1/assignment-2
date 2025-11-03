package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CandidateServiceTest {

    @Test
    void addCandidate_skaLäggaTillOchLogga() {
        // arrange
        CandidateRepository mockRepo = Mockito.mock(CandidateRepository.class);
        CandidateService service = new CandidateService(mockRepo);

        Candidate c = new Candidate("Anna", "Svensson", 30, "IT", 5);

        // act
        service.addCandidate(c);

        // assert
        verify(mockRepo, times(1)).addCandidate(c);
    }

    @Test
    void filterByIndustry_endastRättBransch() {
        // arrange
        CandidateRepository mockRepo = Mockito.mock(CandidateRepository.class);
        CandidateService service = new CandidateService(mockRepo);

        Candidate c1 = new Candidate("Anna", "Svensson", 30, "IT", 5);
        Candidate c2 = new Candidate("Bo", "Larsson", 45, "Ekonomi", 20);
        Candidate c3 = new Candidate("Cecilia", "Karlsson", 28, "IT", 3);

        when(mockRepo.getAllCandidates()).thenReturn(List.of(c1, c2, c3));

        // act
        List<Candidate> result = service.applyFilter(new FilterByIndustry("IT"));

        // assert
        assertEquals(2, result.size());
        assertTrue(result.contains(c1));
        assertTrue(result.contains(c3));
        assertFalse(result.contains(c2));
    }

    @Test
    void sortByFirstName_sorterarAlfabetiskt() {
        // arrange
        CandidateRepository mockRepo = Mockito.mock(CandidateRepository.class);
        CandidateService service = new CandidateService(mockRepo);

        Candidate z = new Candidate("Zara", "Zulu", 33, "IT", 7);
        Candidate a = new Candidate("Adam", "Alpha", 22, "IT", 1);
        Candidate k = new Candidate("Kalle", "Karlsson", 40, "Bygg", 15);

        when(mockRepo.getAllCandidates()).thenReturn(List.of(z, a, k));

        // act
        List<Candidate> result = service.applyFilter(new SortByFirstName());

        // assert
        assertEquals("Adam", result.get(0).getFirstName());
        assertEquals("Kalle", result.get(1).getFirstName());
        assertEquals("Zara", result.get(2).getFirstName());
    }
}
