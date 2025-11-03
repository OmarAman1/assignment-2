package org.example;

public class Main {
    public static void main(String[] args) {
        CandidateRepository repo = new InMemoryCandidateRepository();
        CandidateService service = new CandidateService(repo);
        RecruiterCLI cli = new RecruiterCLI(service);

        cli.start();
    }
}
