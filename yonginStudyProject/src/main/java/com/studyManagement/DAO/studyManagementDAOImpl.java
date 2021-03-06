package com.studyManagement.DAO; 

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.login.VO.userInfoVO;
import com.main.VO.studyInfoVO;
import com.main.VO.userInStudyVO;
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
	public void writeStudyNotice(boardVO data) throws Exception{
		sqlSession.insert("studyManagementMapper.insertStudyNotice", data);
	}
	
	@Override
	public int selectStudyNoticeListToCnt(boardVO boardVO) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyNoticeListToCnt", boardVO);
	}
	
	@Override
	public List<boardVO> selectStudyNoticeList(boardVO boardVO){
		return sqlSession.selectList("studyManagementMapper.selectStudyNoticeList", boardVO);
	}
	
	@Override
	public boardVO selectStudyNoticeInfoDetail(String boardCode) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyNoticeInfoDetail", boardCode);
	}
	
	@Override
	public void updateStudyNoticeCnt(String boardCode) throws Exception{
		sqlSession.update("studyManagementMapper.updateStudyNoticeCnt", boardCode);
	}
	
	@Override
	public void reviseStudyNotice(boardVO data) throws Exception{
		sqlSession.update("studyManagementMapper.reviseStudyNotice", data);
	}
	
	@Override
	public void deleteStudyNotice(String boardCode) throws Exception{
		sqlSession.delete("studyManagementMapper.deleteStudyNotice", boardCode);
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
	
	@Override
	public userInStudyVO selectStudyMemberAdminView(userInStudyVO userInStudyVO) {
		return sqlSession.selectOne("studyManagementMapper.selectStudyMemberAdminView", userInStudyVO);
	}
	
	@Override
	public void studyMemberAdminChange(userInStudyVO userInStudyVO) throws Exception{
		sqlSession.update("studyManagementMapper.studyMemberAdminChange", userInStudyVO);
	}
	
	@Override
	public void studyMemberMasterChange(userInStudyVO userInStudyVO) throws Exception{
		sqlSession.update("studyManagementMapper.studyMemberMasterChange", userInStudyVO);
	}
	
	@Override
	public int selectStudyMemberToCnt(String studyCode) throws Exception{
		return sqlSession.selectOne("studyManagementMapper.selectStudyMemberToCnt", studyCode);
	}
	
	@Override
	public int selectStudyCountToCnt(String studyCode) throws Exception{
		return sqlSession.selectOne("studyManagementMapper.selectStudyCountToCnt", studyCode);
	}
}
