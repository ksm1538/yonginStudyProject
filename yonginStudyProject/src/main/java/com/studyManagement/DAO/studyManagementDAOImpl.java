package com.studyManagement.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;
import com.main.VO.userInStudyVO;
import com.main.VO.studyInfoVO;
import com.message.VO.messageInfoVO;
import com.notice.VO.boardVO;
import com.study.VO.studyApplicationFormUserVO;

@Repository
public class studyManagementDAOImpl implements studyManagementDAO{
	@Inject
	private SqlSession sqlSession;
	
	@Override
	public List<userInStudyVO> selectStudyMemberList(userInStudyVO userInStudyVO){
		return sqlSession.selectList("studyManagementMapper.selectStudyMemberList", userInStudyVO);
	}
	
	@Override
	public int selectStudyMemberListToCnt(userInStudyVO userInStudyVO)	{
		return sqlSession.selectOne("studyManagementMapper.selectStudyMemeberListToCnt", userInStudyVO);
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
	
	@Override
	public userInfoVO selectStudyMemberManage(String userCode) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyMemberManage", userCode);
	}
	
	@Override
	public void deportStudyMember(messageInfoVO data) throws Exception{
		sqlSession.delete("studyManagementMapper.deportStudyMember", data);
	}
	
	@Override
	public List<studyApplicationFormUserVO> selectStudyApplicationForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception{
		return sqlSession.selectList("studyManagementMapper.selectStudyApplicationForm", studyApplicationFormUserVO);
	}
	
	@Override
	public int selectStudyApplicationFormToCnt(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception{
		return sqlSession.selectOne("studyManagementMapper.selectStudyApplicationFormToCnt", studyApplicationFormUserVO);
	}
	
	@Override
	public void approveStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception{
		sqlSession.update("studyManagementMapper.approveStudyForm", studyApplicationFormUserVO);
	}
	
	@Override
	public void rejectStudyForm(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception{
		sqlSession.update("studyManagementMapper.rejectStudyForm", studyApplicationFormUserVO);
	}
	
	@Override
	public void insertUserInStudy(studyApplicationFormUserVO studyApplicationFormUserVO) throws Exception{
		sqlSession.insert("studyManagementMapper.insertUserInStudy", studyApplicationFormUserVO);
	}
	
	@Override
	public studyInfoVO selectStudyInfoView(String studyCode) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyInfoView", studyCode);
	}
	
	@Override
	public void studyInfoChange(studyInfoVO studyInfoVO) throws Exception{
		sqlSession.update("studyManagementMapper.changeStudyInfo", studyInfoVO);
	}
}
