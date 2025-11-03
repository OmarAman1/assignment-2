# SOLID-användning i assignment-2

## SRP (Single Responsibility Principle)
- Candidate: ansvarar bara för att hålla data om en kandidat (förnamn, efternamn, ålder, bransch, erfarenhet).
- InMemoryCandidateRepository: ansvarar bara för lagring/hämtning av kandidater.
- CandidateService: ansvarar bara för affärslogik (lägga till, ta bort, filtrera, logga).
- RecruiterCLI: ansvarar bara för användarinteraktion via meny.

Varje klass har ETT ansvarsområde ⇒ SRP följs.

## OCP (Open/Closed Principle)
Vi har ett interface `CandidateFilter` och flera implementationer:
- FilterByIndustry
- FilterByMinExperience
- SortByFirstName

Systemet är "öppet för utökning" (jag kan skapa en ny filterklass, t.ex. `FilterByAgeRange`)
men "stängt för modifikation" (jag behöver INTE ändra `CandidateService` för att lägga till ett nytt filter).
Det uppfyller OCP.

## DIP (Dependency Inversion Principle)
- CandidateService beror på `CandidateRepository` (ett interface), inte på en konkret klass.
- I main använder vi `new InMemoryCandidateRepository()`, men vi kan byta till en annan implementation utan att ändra `CandidateService`.
- I testerna mockar vi `CandidateRepository` med Mockito. Det funkar tack vare DIP.

Detta visar att högnivåkod beror på abstraktioner, inte detaljer.
