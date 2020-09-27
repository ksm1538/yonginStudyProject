package com.commonFunction.Service;

import java.util.List;
import java.util.Map;

public interface fileService {
	List<Map<String, Object>> selectFileList(String bCode) throws Exception;
	
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;
}
