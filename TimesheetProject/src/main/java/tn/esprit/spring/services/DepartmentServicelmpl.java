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

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}


	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	public Departement getDepartementById(int depId) {
		return deptRepoistory.findById(depId).get();	
	}


	public List<Departement> getAllDepartements() {
		return (List<Departement>) deptRepoistory.findAll();
	}
	
	public void mettreAjourNameDepartmentByDepartmentId(String name, int departmentId) {
		Departement department = deptRepoistory.findById(departmentId).get();
		department.setName(name);
		deptRepoistory.save(department);

	}
	
	
	@Transactional	
	public void affecterDepartementAEntreprise(int departmentId, int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepository.findById(entrepriseId).get();
		Departement departementManagedEntity = deptRepoistory.findById(departmentId).get();

		if(entrepriseManagedEntity.getDepartements() == null){

			List<Departement> departements = new ArrayList<>();
			departements.add(departementManagedEntity);
			entrepriseManagedEntity.setDepartements(departements);
			
		}else{

			entrepriseManagedEntity.getDepartements().add(departementManagedEntity);

		}

	}
	
	@Transactional
	public void desaffecterDepartementDuEntreprise(int departmentId, int entrepriseId)
	{
		Entreprise enp = entrepriseRepository.findById(entrepriseId).get();

		int departmentNb = enp.getDepartements().size();
		for(int index = 0; index < departmentNb; index++){
			if(enp.getDepartements().get(index).getId() == departmentId){
				enp.getDepartements().remove(index);
				break;
			}
		}
	}
	
	
	public List<Departement> getAllDepartmentByEntreprise(int entrepriseId) {
		return deptRepoistory.getAllEmployeByEntreprisec(entrepriseId);
	}
	
	public int getNombreDepartment() {
		return deptRepoistory.countdep();
	}
	
	
	

}
