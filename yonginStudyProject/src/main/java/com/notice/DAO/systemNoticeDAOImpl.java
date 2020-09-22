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
	public List<moreNoticeInfoVO> selectSystemNoticeList(){
		return sqlSession.selectList("systemNoticeMapper.selectSystemNoticeList");
	}
	
	@Override
	public void insertSystemNotice(moreNoticeInfoVO data) throws Exception{
		sqlSession.insert("systemNoticeMapper.insertSystemNotice", data);
	}
	
	@Override
	public void deleteSystemNotice(String noticeCode) throws Exception{
		sqlSession.delete("systemNoticeMapper.deleteSystemNotice", noticeCode);
	}
}
