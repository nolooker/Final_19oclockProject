package com.ilgusi.service.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ilgusi.member.model.vo.Member;
import com.ilgusi.service.model.vo.Join;

@Repository
public class ServiceDao {
	@Autowired
	private SqlSessionTemplate session;

	public Join selectOneMember(String id) {
		return session.selectOne("service.selectOneMember",id);
	}

	public int insertService(Join join) {
		return session.insert("service.insertService",join);
	}

	public int updateFreelancer(Member m) {
		return session.update("service.updateFreelancer",m);
	}

	public Member selectOneMember(int MNO) {
		return session.selectOne("service.freelancerOneMember",MNO);
	}


	

	
}
