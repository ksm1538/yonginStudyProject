package com.commonFunction.DAO;

import java.util.List;
import java.util.Map;

public interface fileDAO {

	public void insertFile(Map<String, Object> map) throws Exception;
	
	public List<Map<String, Object>> selectFileList(String bCode) throws Exception;
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
	
	public void updateFile(Map<String, Object> map) throws Exception;
	
	public void updateNFileWithDeleteBoard(String boardCode) throws Exception;

}