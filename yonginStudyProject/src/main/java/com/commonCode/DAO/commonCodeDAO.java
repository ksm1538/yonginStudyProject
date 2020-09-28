package com.commonCode.DAO;

import java.util.List;
import java.util.Map;

import com.commonCode.VO.commonCodeVO;

public interface commonCodeDAO {
	
	/**
	 * COMMON_CODE_DETAIL의 코드값들을 조회한다.
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public List<commonCodeVO> selectCommonCodeList(String code) throws Exception;

	public void updateFile(Map<String, Object> map) throws Exception;
	
}
