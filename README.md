# Project NerdyGadgets
Voordat je begint met het project, lees je eerst de volgende informatie goed door.
Dit bestand beschrijft hoe je code aan dit project kunt toevoegen, en hoe de code is opgebouwd.

Dit project is beheerd door: Robine, Teun, Sam, Guus

Voordat je begint met programmeren lees [dit](beforeCoding.md) document.

## Het toevoegen van code

### Stap 1 - Het maken van een fork

Begin met het maken van een fork van deze repository. Dit kan je doen met de fork knop rechtsboven op de pagina.
![img.png](src/main/resources/readmeImages/img.png)
Let op dat je jezelf selecteert als eigenaar van de repository. Dit doe je door je eigen naam te selecteren in het dropdown menu.
De meeste opties op deze pagina kun je negeren, als je klaar bent klik je op de knop "Create Fork" onderaan de pagina.
![img.png](src/main/resources/readmeImages/createFork.png)

### Stap 2 - Het klonen van de repository

Nu je een fork hebt gemaakt, moet je deze klonen naar je eigen computer. 
Navigeer naar je github profiel en vanaf daar naar je eigen repositories.
Nadat je de repository hebt gevonden, klik je op de knop "Code" en kopieer je de link die er bij HTTPS staat.
Zorg ervoor dat je je eigen fork cloned, en niet mijn repository.
![img.png](src/main/resources/readmeImages/urlKopieëren.png) \
![img.png](src/main/resources/readmeImages/https.png) \
Open nu je IntelIJ en klik in de bovenste balk op de knop Git, en daarna op "Clone". (Mocht je deze optie niet hebben, 
klik dan op de knop "VCS" en selecteer "Get from Version Control", dit is hetzelfde als de optie "Clone" die je net hebt gemist.)
![img.png](src/main/resources/readmeImages/clone.png)
In het menu dat nu opent, plak je de link die je hebt gekopieerd in het veld "URL".
![img.png](src/main/resources/readmeImages/cloneurl.png)
Klik op de knop "Clone" en wacht tot de repository is gekloond.
Zodra dit gedaan is, kun je het project openen.

### Stap 3 - Het bijhouden van de fork

Nu je een fork hebt gemaakt, moet je ervoor zorgen dat je fork up-to-date blijft met de originele repository.
Dit doe je door de originele repository toe te voegen als een remote repository.
Dit doe je door in de bovenste balk op de knop Git te klikken, en daarna op "Remote".

![img.png](src/main/resources/readmeImages/manageRemotes.png)

In het menu dat nu opent, klik je op het plusje linksboven.

![img.png](src/main/resources/readmeImages/plusje.png) 

In het menu dat nu opent, vul je de volgende gegevens in:
- Name: upstream 
- URL: het url van de originele repository. Dit is: https://github.com/RubenJ01/KBS-ICTM2C4.git

Dat ziet er ongeveer zo uit: 

![img.png](src/main/resources/readmeImages/upstream.png) 

Ik heb een compleet andere url gebruikt, maar dat maakt niet uit. Klik nu op OK.
Nu je dit hebt gedaan, moet je ervoor zorgen dat je fork up-to-date blijft met de originele repository.

### Stap 4 - Het synchroniseren van de fork

Iedereen heeft nu zijn eigen versie van het project, maar we willen natuurlijk wel dat iedereen de laatste versie heeft.
Dit doen we door de originele repository te pullen, en deze vervolgens te pushen naar onze fork.
Dit doe je door in de bovenste balk op de knop Git te klikken, en daarna op "Pull".

![img.png](src/main/resources/readmeImages/pull.png) 

In het menu dat nu opent, klik je op de knop "Pull from" en selecteer je "upstream".

![img.png](src/main/resources/readmeImages/pullUpstream.png) 

Klik nu op Pull en wacht tot de originele repository is gepulled. Als dit niet lukt, stuur mij dan een berichtje op discord.
Volg deze stap elke keer uit voordat je begint met werken aan het project.

### Stap 5 - Het toevoegen van code

Nu kunnen we beginnen met het toevoegen van code.
Begin met het schrijven van je code, en zorg ervoor dat je de code goed test.
Zodra je klaar bent met het schrijven van je code, moet je ervoor zorgen dat je code wordt gepushed naar je fork.
Dit doe je door in de bovenste balk op de knop Git te klikken, en daarna op "Commit".

![img.png](src/main/resources/readmeImages/commit.png)

In het menu dat nu opent, selecteer je de bestanden die je wilt committen, 
maar meestal staan alle gewijzigde bestanden al geselecteerd. Vul nu een zinnige commit message in die beschrijfd welke
wijzigingen je hebt gemaakt.

![img.png](src/main/resources/readmeImages/desc.png)

Klik nu op de knop "Commit and Push" en in het menu dat nu opent, klik je op de knop "Push".

![img.png](src/main/resources/readmeImages/push.png)

Nu is als het goed is je code gepushed naar je fork. (Zo niet stuur mij dan een berichtje op discord)

### Stap 6 - Het maken van een pull request

Nu je code is gepushed naar je fork, moet je ervoor zorgen dat je code wordt toegevoegd aan de originele repository.
Dit doe je door een pull request te maken. Dit doe je door naar je fork te navigeren op github, en vervolgens op de knop "Contribute" te klikken.

![img.png](src/main/resources/readmeImages/contribute.png)

Klik nu op de knop "Open pull request". Op deze pagina kun je de wijzigingen zien die je hebt gemaakt, en kun je een titel en beschrijving toevoegen.
Zodra je klaar bent, klik je op de knop "Create pull request".

![img.png](src/main/resources/readmeImages/createPr.png)

Nu is je pull request aangemaakt, en moet je wachten tot iemand jou wijzigingen heeft goedgekeurd.

# Code conventions

Variabelen en functies worden altijd geschreven met lower camel case, dit betekend dat elk woord dat na het eerste woord 
komt met een hoofdletter begint.
```java
int highestNumber = 5;
String multipleWordsHere = "";

public void someRandomFunction(){
    
}
```

