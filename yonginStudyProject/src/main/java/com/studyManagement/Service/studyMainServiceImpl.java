package com.studyManagement.Service; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyManagement.DAO.studyMainDAO;

@Service("studyMainService")
public class studyMainServiceImpl implements studyMainService{
	@Autowired
	studyMainDAO studyMainDAO;
	
	

}
