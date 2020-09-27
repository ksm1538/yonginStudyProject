package com.notice.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.notice.VO.moreNoticeInfoVO;

@Repository
public class systemNoticeDAOImpl implements systemNoticeDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<moreNoticeInfoVO> selectSystemNoticeList(moreNoticeInfoVO moreNoticeInfoVO){
		return sqlSession.selectList("systemNoticeMapper.selectSystemNoticeList", moreNoticeInfoVO);
	}
	
	@Override
	public void insertSystemNotice(moreNoticeInfoVO data) throws Exception{
		sqlSession.insert("systemNoticeMapper.insertSystemNotice", data);
	}
	
	@Override
	public void deleteSystemNotice(String noticeCode) throws Exception{
		sqlSession.delete("systemNoticeMapper.deleteSystemNotice", noticeCode);
	}
	
	@Override
	public int selectSystemNoticeListToCnt(moreNoticeInfoVO moreNoticeInfoVO) {
		return sqlSession.selectOne("systemNoticeMapper.selectSystemNoticeListToCnt", moreNoticeInfoVO);
	}
	
	@Override
	public moreNoticeInfoVO selectSystemNoticeInfoDetail(String systemNoticeCode) {
		return sqlSession.selectOne("systemNoticeMapper.selectSystemNoticeInfoDetail", systemNoticeCode);
	}
	
	@Override
	public void reviseSystemNotice(moreNoticeInfoVO data) throws Exception{
		sqlSession.update("systemNoticeMapper.reviseSystemNotice", data);
	}
	
	/*
	 * @Override public void insertFile(Map<String, Object> map) throws Exception{
	 * sqlSession.insert("commonFileSQL.insertFile", map); }
	 * 
	 * @Override public List<Map<String, Object>> selectFileList(String bCode)
	 * throws Exception{ return
	 * sqlSession.selectList("commonFileSQL.selectFileList", bCode); }
	 * 
	 * @Override public Map<String, Object> selectFileInfo(Map<String, Object> map)
	 * throws Exception { return
	 * sqlSession.selectOne("commonFileSQL.selectFileInfo", map); }
	 */
}