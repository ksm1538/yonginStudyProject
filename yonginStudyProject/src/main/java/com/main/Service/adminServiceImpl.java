package com.main.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DAO.adminDAO;

@Service("adminService")
public class adminServiceImpl implements adminService{
	@Autowired
	adminDAO adminDAO;
	
}
