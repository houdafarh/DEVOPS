package tn.esprit.spring.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.EmployeServiceImpl;

@SpringBootTest
public class EmployeTest {

	@Autowired
    EmployeServiceImpl employeServImp;
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	
	
	
	@Test
	public void testAjouterEmploye() throws ParseException {
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		//assertTrue(emp.getRole().equals(Role.INGENIEUR));	
		assertNotNull("it is not empty", emp);
		l.info("compatible  " + emp);
		}
	
	
	
	@Test
	public void mettreAjourEmailByEmployeId()throws ParseException{
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		//Employe emp1  = new Employe("kallel1","khaled1","Khaled.kallel1@ssiiconsulting.tn",true,Role.INGENIEUR);
		String mail1="Nourhen.allagui@gmail.com";
		emp.setEmail(mail1);
		String mail = emp.getEmail();
		assertTrue(emp.getEmail().equals(mail1));
		l.info("compatible  " + mail);
		
	}
	
	
	@Test
	public void testAffecterEmployeADepartement(){
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		emp.setId(1);
	    Departement dep = new Departement("informatique");
	    dep.setId(1);
	    if(dep.getEmployes()== null){
	    	ArrayList<Employe> lemp = new ArrayList();
	    	lemp.add(emp);
	    	dep.setEmployes(lemp);
	    	assertNotNull("it is not empty", dep.getEmployes());
	    	l.info("cc " +dep.getEmployes());
	    	dep.setEmployes(lemp);
	    	
	    	
	    	
	    }
	    else {
	    dep.getEmployes().add(emp);
	    assertNotNull(dep);
	    l.debug("good " +dep.getEmployes());
	    }
	}
	
	
	
	@Test
	public void testDesaffecterEmployeDuDepartement(){
		Departement dep = new Departement("informatique");
		ArrayList<Employe> lemp = new ArrayList();
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		emp.setId(1);
		int employeId = emp.getId();
		lemp.add(emp);
		dep.setEmployes(lemp);
		int employeNb = dep.getEmployes().size();
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				assertNotEquals(dep.getEmployes().size(), 1);
				l.info("des affecter Employe Du Departement", +employeNb);
				break;
				
			}
		}
		
	}
	
	
	@Test
	public void testAjouterContrat(){
		Contrat cont = new Contrat("10/10/2021","CDI", 120.3f);
		cont.setReference(1);
		int contrat =cont.getReference();
        assertEquals(contrat, 1);
        l.info("reference not null", +contrat);
	}
	
	@Test
	public void testAffecterContratAEmploye(){
		Contrat cont = new Contrat("10/10/2021","CDI", 120.3f);
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		cont.setEmploye(emp);
		assertTrue("affecté", cont.getEmploye() != null);
		l.info("affecté", cont.getEmploye());
	}
	
	@Test
	public void testGetEmployePrenomById(){
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		emp.setId(1);
		if(emp.getId()== 1){
		assertTrue(emp.getPrenom() != null);
		}
	}
	/*
	@Test
	public void testGetNombreEmployeJPQL(){
		System.out.println(employeServImp.getNombreEmployeJPQL());
		
	}*/
	/*
	@Test
	public void testDeleteAllContratJPQL(){
		employeServImp.deleteAllContratJPQL();
	}*/

}
