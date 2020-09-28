package com.commonFunction.DAO; 

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class fileDAOImpl implements fileDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public void insertFile(Map<String, Object> map) throws Exception{
		sqlSession.insert("commonFileSQL.insertFile", map);
	}
	
	@Override
	public List<Map<String, Object>> selectFileList(String bCode) throws Exception{
		return sqlSession.selectList("commonFileSQL.selectFileList", bCode);
	}
	
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("commonFileSQL.selectFileInfo", map);
	}
	
	@Override
	public void updateFile(Map<String, Object> map) throws Exception{
		sqlSession.update("commonFileSQL.updateFile", map);
	}
}