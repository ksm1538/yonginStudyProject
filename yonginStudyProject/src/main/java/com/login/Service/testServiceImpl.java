package com.login.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.login.DAO.testDAO;
import com.login.VO.testVO;

@Service("testService")
public class testServiceImpl implements testService{
	@Autowired
	testDAO testDAO;
	
	// Service Interface의 함수를 명시화한 것으로, 해당 select 작업을 한 후 DB의 값을 반환하는 것입니다.
	public List<testVO> selectlistService(){
		return testDAO.selectlist();
	};
	
	public String aa() {
		String aa = "AA";
		return aa; 
	}
}
