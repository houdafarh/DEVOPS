package tn.esprit.spring.test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.EntrepriseServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EntrepriseServiceImplTest {
private static final Logger l = (Logger) LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Autowired
	EntrepriseServiceImpl es;
	
	//Unit Test for CRUD methods : 
	
	//public int ajouterEntreprise(Entreprise entreprise);
	@Test
	public void testAjouterEntreprise() {
		l.info("Starting add Entreprise test method");
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		l.info("Adding new Entreprise with id : " + Id);
		Entreprise Ese = es.getEntrepriseById(Id);
		assertNotNull(Ese);
		l.info("Entreprise with id(" + Ese.getId() 
				+") , Name(" + Ese.getName() +") and Social Reason(" + Ese.getRaisonSocial() + ")" + " added successfuly");
		es.deleteEntrepriseById(Id);
	}
	

	//public void deleteEntrepriseById(int entrepriseId);
	//added a getAllEntreprises service to test % size of list 
	@Test
	public void testDeleteEntrepriseById() {
		l.info("Starting delete Entreprise test method");
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		assertNotNull(es.getEntrepriseById(Id));
		int lengthBeforeDelete = es.getAllEntreprises().size();
		l.info("Entreprise with id " + Id + " exists" );
		es.deleteEntrepriseById(Id);
		assertEquals(lengthBeforeDelete-1 , es.getAllEntreprises().size());
		l.info("Entreprise deleted successfuly");
	}
	
	
	
	//public Entreprise getEntrepriseById(int entrepriseId);
	@Test
	public void testGetEntrepriseById(){
		l.info("Starting find Entreprise test method");
		Entreprise E = new Entreprise("Samsung","EURL");
		int Id = es.ajouterEntreprise(E);
		assertNotNull(es.getEntrepriseById(Id));
		l.info("Entreprise with id " + Id + " exists" );
		Entreprise Ese = es.getEntrepriseById(Id);
		assertNotNull(Ese);	
		l.info("Entreprise with id " + Id + " was found successfuly : id(" + Ese.getId() 
		+") , Name(" + Ese.getName() +") and Social Reason(" + Ese.getRaisonSocial() + ")" );
		es.deleteEntrepriseById(Id);
		}
}
