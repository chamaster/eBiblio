package com.test.projet.jbehave.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.test.projet.ebiblio.domain.adherent.AdherentRepositoryMock;
import com.test.projet.ebiblio.domain.livre.Livre;
import com.test.projet.ebiblio.domain.livre.LivreRepository;
import com.test.projet.ebiblio.domain.livre.LivreRepositoryMock;
import com.test.projet.ebiblio.domain.livre.Reference;
import com.test.projet.ebiblio.domain.tier.Adherent;
import com.test.projet.ebiblio.domain.tier.AdherentRepository;
import com.test.projet.ebiblio.domain.tier.NoAdherent;

public class GererLivreSteps {

    LivreRepository livreRepository = LivreRepositoryMock.getInstance();
    AdherentRepository adherentRepository = AdherentRepositoryMock.getInstance();

    Adherent adherent;
    Livre livre;
    
    @Given("mon adh�rent %noAdherent existe.")
    public void givenMonAdherentExiste(String noAdherent) {
        adherent = adherentRepository.findBy(new NoAdherent(noAdherent));
    }

    @Given("mon livre r�f�rence %reference est disponible.")
    public void givenMonLivreReferenceEstDisponible(String reference) {
        livre = livreRepository.findBy(new Reference(reference));
        Assert.assertTrue("Mon livre n'est pas disponible", livre.estDisponible());
    }
    
    @When("l'adh�rent loue le livre.")
    public void whenLadherentLoueLeLivre() {
        adherent.louer(livre);
    }

    @Then("le livre est lou�.")
    public void thenLeLivreEstLoue() {
        Assert.assertTrue("Mon livre n'est pas lou�", livre.estLoue());
    }
    
    @Then("l'adh�rent a lou� %nombre livre.")
    public void thenLadherentALoueLivres(int nombre) {
     Assert.assertEquals("Le nombre de livre lou� est incorrecte.", nombre, adherent.nombreDeLivreEnLocation());
    }

}
