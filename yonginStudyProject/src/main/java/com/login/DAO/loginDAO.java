package com.login.DAO;

import java.util.List;

import com.login.VO.testVO;
import com.login.VO.userInfoVO;

public interface loginDAO {
	public void insertMember(userInfoVO data) throws Exception;
}
