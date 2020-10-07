package com.studyManagement.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.notice.VO.boardVO;
import com.studyManagement.VO.studyManagementInfoVO;

@Repository
public class studyManagementDAOImpl implements studyManagementDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<studyManagementInfoVO> selectStudyMemberList(studyManagementInfoVO studyManagementInfoVO){
		return sqlSession.selectList("studyManagementMapper.selectStudyMemberList", studyManagementInfoVO);
	}
	
	@Override
	public int selectStudyMemeberListToCnt(studyManagementInfoVO studyManagementInfoVO)	{
		return sqlSession.selectOne("studyManagementMapper.selectStudyMemeberListToCnt", studyManagementInfoVO);
	}
	
	@Override
	public void writeStudyFreeNotice(boardVO data) throws Exception{
		sqlSession.insert("studyManagementMapper.insertStudyFreeNotice", data);
	}
	
	@Override
	public int selectStudyFreeNoticeListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyFreeNoticeListToCnt", boardVO);
	}
	
	@Override
	public List<boardVO> selectStudyFreeNoticeList(boardVO boardVO){
		return sqlSession.selectList("studyManagementMapper.selectStudyFreeNoticeList", boardVO);
	}
	
	@Override
	public boardVO selectStudyFreeNoticeInfoDetail(String boardCode) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyFreeNoticeInfoDetail", boardCode);
	}
	
	@Override
	public void updateStudyFreeNoticeCnt(String boardCode) throws Exception{
		sqlSession.update("studyManagementMapper.updateStudyFreeNoticeCnt", boardCode);
	}
	
	@Override
	public void reviseStudyFreeNotice(boardVO data) throws Exception{
		sqlSession.update("studyManagementMapper.reviseStudyFreeNotice", data);
	}
	
	@Override
	public void deleteStudyFreeNotice(String boardCode) throws Exception{
		sqlSession.delete("studyManagementMapper.deleteStudyFreeNotice", boardCode);
	}
}
