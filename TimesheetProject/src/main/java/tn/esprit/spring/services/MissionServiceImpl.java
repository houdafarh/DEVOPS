package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.MissionRepository;
import tn.esprit.spring.repository.TimesheetRepository;

@Service
public class MissionServiceImpl implements MissionService{

@Autowired 
MissionRepository missionRepository;
@Autowired 
EmployeRepository employeRespository;
@Autowired
DepartementRepository deptRepoistory;
@Autowired
TimesheetRepository timesheetRepository;

public int addMission(Mission mission) {
	missionRepository.save(mission);
	return mission.getId();
}


public List<Mission> getAllMission(){
	return (List<Mission>) missionRepository.findAll();
	
}
public Mission getMissionById( int missionId) {
	
	Mission mission =missionRepository.findById(missionId).get();
	return mission;
}

public void deleteMissionById(int missionId) {
	Mission mission = missionRepository.findById(missionId).get();
	missionRepository.delete(mission);
	}

public void mettreAjourMissionName(String name ,int missionId  ) {
	Mission mission = missionRepository.findById(missionId).get();
	mission.setName(name);
	missionRepository.save(mission);
}

@Transactional 
public void affecterMissionADepartement(int missionId, int depId) {
	Mission mission = missionRepository.findById(missionId).get();
	Departement dep = deptRepoistory.findById(depId).get();
	mission.setDepartement(dep);
	missionRepository.save(mission);
	
}
public List<Mission> findAllMissionByEmployeJPQL(int employeId) {
	return timesheetRepository.findAllMissionByEmployeJPQL(employeId);
}
		

public List<Employe> getAllEmployeByMission(int missionId) {
	return timesheetRepository.getAllEmployeByMission(missionId);
}

	
	
}


