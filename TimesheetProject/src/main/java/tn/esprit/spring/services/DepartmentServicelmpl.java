package tn.esprit.spring.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;


@Service
public class DepartmentServicelmpl implements IDepartmentService {
	
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	EntrepriseRepository entrepriseRepository;
	
	
    //done
	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}

    
	
	//done
	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}

    
	
	//done
	public Departement getDepartementById(int depId) {
		return deptRepoistory.findById(depId).get();	
	}

    
	//done
	public List<Departement> getAllDepartements() {
		return (List<Departement>) deptRepoistory.findAll();
	}
	
	
	//done
	public void mettreAjourNameDepartmentByDepartmentId(String name, int departmentId) {
		Departement department = deptRepoistory.findById(departmentId).get();
		department.setName(name);
		deptRepoistory.save(department);

	}
	
	
	//done
	@Transactional	
	public void affecterDepartementAEntreprise(int departmentId, int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepository.findById(entrepriseId).get();
		Departement departementManagedEntity = deptRepoistory.findById(departmentId).get();
			departementManagedEntity.setEntreprise(entrepriseManagedEntity);
			deptRepoistory.save(departementManagedEntity);
			entrepriseRepository.save(entrepriseManagedEntity);
			
		

	}
	
	//done
	@Transactional
	public void desaffecterDepartementDuEntreprise(int departmentId, int entrepriseId)
	{
        Departement d = deptRepoistory.findById(departmentId).orElse(new Departement());
        Entreprise e = entrepriseRepository.findById(entrepriseId).orElse(new Entreprise());
        d.setEntreprise(null); deptRepoistory.save(d);
        e.getDepartements().remove(d); entrepriseRepository.save(e);
	}
	
	
	
	public List<Departement> getAllDepartmentByEntreprise(int entrepriseId) {
		return deptRepoistory.getAllEmployeByEntreprisec(entrepriseId);
	}
	
	
	
	public int getNombreDepartment() {
		return deptRepoistory.countdep();
	}
	
	
	

}
