package com.main.Service;

import java.util.List;

import com.main.VO.studyInfoVO;

public interface mainService {
	
	List<studyInfoVO> selectStudyList(String userCode);

}
