package com.commonCode.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commonCode.DAO.commonCodeDAO;
import com.commonCode.VO.commonCodeVO;

@Service("commonCodeService")
public class commonCodeServiceImpl implements commonCodeService{
	@Autowired
	commonCodeDAO commonCodeDAO;
	
	@Override
	public List<commonCodeVO> selectCommonCodeList(String code) throws Exception{
		return commonCodeDAO.selectCommonCodeList(code);
	}
}
