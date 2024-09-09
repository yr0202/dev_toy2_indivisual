package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.MemberDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDao {
    @Autowired
    private SqlSession session;

    private static String namespace = "com.fastcampus.toy2_7.dao.MemberMapper.";

    public MemberDto getMemberById(String memberId) {
        return session.selectOne(namespace + "getMemberById", memberId);
    }
}
