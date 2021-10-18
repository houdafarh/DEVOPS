package tn.esprit.spring.test;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.services.EmployeServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeTest {

	@Autowired
    EmployeServiceImpl employeServImp;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepository;
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	

	
	@Test
	public void testAjouterEmploye() throws ParseException {
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int Id = employeServImp.ajouterEmploye(emp);
		assertNotNull("it is not empty",Id);
		l.info("employe a ete ajoute  " + Id);
		employeServImp.deleteEmployeById(Id);
		}
	
	
	@Test
	public void testMettreAjourEmailByEmployeId(){
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int Id = employeServImp.ajouterEmploye(emp);
		if(emp.getId()== Id){
		String mail1="Nourhen.allagui@gmail.com";
		emp.setEmail(mail1);
		String mail = emp.getEmail();
		l.info("it's" + mail);
		assertTrue(emp.getEmail().equals(mail1));
		l.info("compatible  " + mail);
		}
		else 
			l.info("sorry");
		employeServImp.deleteEmployeById(Id);
	}
	
	
	@Test
	public void testAffecterEmployeADepartement(){
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int id = employeServImp.ajouterEmploye(emp);
	    Departement dep = new Departement("informatique");
	    deptRepoistory.save(dep);
	    int Idd = dep.getId();
	    if(dep.getEmployes() == null){
	    	ArrayList<Employe> lemp = new ArrayList<Employe>();
	    	lemp.add(emp);
	    	dep.setEmployes(lemp);
	    	assertNotNull("it is not empty", dep.getEmployes());
	    	l.info("cc " +dep.getEmployes());
	    	dep.setEmployes(lemp);
	    	
	    	
	    	
	    }
	    else {
	    dep.getEmployes().add(emp);
	    assertNotNull(dep);
	    }
	    deptRepoistory.deleteById(Idd);
	    employeServImp.deleteEmployeById(id);
	}
	
	
	
	@Test
	public void testDesaffecterEmployeDuDepartement(){
		Departement dep = new Departement("informatique");
		ArrayList<Employe> lemp = new ArrayList<Employe>();
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int employeId = employeServImp.ajouterEmploye(emp);
		lemp.add(emp);
		dep.setEmployes(lemp);
		int employeNb = dep.getEmployes().size();
		System.out.println(employeNb);
		for(int index = 0; index < employeNb; index++){
			if(dep.getEmployes().get(index).getId() == employeId){
				dep.getEmployes().remove(index);
				assertNotEquals(dep.getEmployes().size(), 1);
				l.info("des affecter Employe Du Departement", +employeNb);
				break;
				
			}
		}
		deptRepoistory.deleteAll();
		employeServImp.deleteEmployeById(employeId);
	}
	
	
	@Test
	public void testAjouterContrat(){
		Date d=new Date(12-01-2021);
		Contrat cont = new Contrat(d,"CDI", 120.3f);
		contratRepository.save(cont);
		int id = cont.getReference();
        assertEquals(cont.getReference(), id);
        l.info("reference not null", +id);
        contratRepository.deleteAll();
	}
	
	@Test
	public void testAffecterContratAEmploye(){
		Date d=new Date(12/01/2021);
		Contrat cont = new Contrat(d,"CDI", 120.3f);
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int empId = employeServImp.ajouterEmploye(emp);
		cont.setEmploye(emp);
		assertTrue("affecté", cont.getEmploye() != null);
		l.info("affecté", cont.getEmploye());
		contratRepository.deleteAll();
		employeServImp.deleteEmployeById(empId);
	}
	
	@Test
	public void testGetEmployePrenomById(){
		Employe emp  = new Employe("kallel","khaled","Khaled.kallel@ssiiconsulting.tn",true,Role.INGENIEUR);
		int Id = employeServImp.ajouterEmploye(emp);
		if(Id!= 0){
		assertTrue(emp.getPrenom() != null);
		l.info("Prenom :", emp.getPrenom());
		}
		employeServImp.deleteEmployeById(Id);;
	}
	
}
