package com.commonFunction.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commonFunction.DAO.fileDAO;

@Service("fileService")
public class fileServiceImpl implements fileService{
	@Autowired
	fileDAO fileDAO;

	@Override
	public List<Map<String, Object>> selectFileList(String bCode) throws Exception{
		return fileDAO.selectFileList(bCode);
	}
	
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return fileDAO.selectFileInfo(map);
	}
}
