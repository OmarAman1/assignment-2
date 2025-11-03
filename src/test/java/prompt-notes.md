# Prompt engineering logg

1. Ursprunglig prompt till LLM:
   "Jag vill bygga ett rekryteringssystem i Java som kan lägga till kandidater,
   ta bort kandidater, filtrera, sortera och använda Stream API. Kan du skriva koden?"

2. Problem:
   Svaret täckte inte loggning, inte tester, och inte SOLID (DIP/OCP).

3. Förbättrad prompt:
   "Systemet måste uppfylla alla krav:
- Stream API och lambdas
- SOLID (SRP, OCP, DIP)
- SLF4J loggning
- JUnit + Mockito
- prompt engineering dokumentation
  Visa exakt vilka filer jag ska skapa och vad de ska innehålla, steg för steg."

4. Effekt:
   Det förbättrade svaret gav en fullständig projektstruktur inkl.:
- interface + implementation (DIP)
- filter-strategier (OCP)
- SLF4J-loggning
- testklass med Mockito
- dokumentation av prompten själv (den här filen)

5. Reflektion:
   Genom att specificera kursens krav i prompten fick jag kod som direkt matchar betygskraven.
   Detta visar hur man styr en LLM mot rätt nivå.
