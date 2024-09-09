package com.fastcampus.toy2_7.service;

import com.fastcampus.toy2_7.dao.MemberDao;
import com.fastcampus.toy2_7.domain.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberDao memberDao;

    public MemberDto getMemberById(String memberId) {
        return memberDao.getMemberById(memberId);
    }
}
