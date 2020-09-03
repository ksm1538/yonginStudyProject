package com.main.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.DAO.mainDAO;
import com.main.VO.studyInfoVO;

@Service("mainService")
public class mainServiceImpl implements mainService{
	@Autowired
	mainDAO mainDAO;
	
	@Override
	public List<studyInfoVO> selectStudyList(){
		return mainDAO.selectStudyList();
	}
}
