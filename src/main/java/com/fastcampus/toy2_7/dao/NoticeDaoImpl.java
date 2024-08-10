package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.NoticeDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoticeDaoImpl implements NoticeDao {
    @Autowired
    private SqlSession session;

    private static String namespace = "com.fastcampus.toy2_7.dao.NoticeMapper.";

    @Override
    public int count() throws Exception{
        return session.selectOne(namespace+"count");
    }

    @Override
    public NoticeDto selectOne(int noticeID){
        return session.selectOne(namespace+"selectOne",noticeID);

    }

    @Override
    public List<NoticeDto> selectAll() throws Exception {
        return session.selectList(namespace+"selectAll");
    }

    @Override
    public int deleteAll(){
        return session.delete(namespace+"deleteAll");
    }

    @Override
    public int deleteById(int noticeID){
        return session.delete(namespace+"deleteById",noticeID);
    }

    @Override
    public int deleteByIds(List<Integer> noticeIDs) {
        return session.delete(namespace + "deleteByIds", noticeIDs);
    }

    @Override
    public int insert(NoticeDto noticeDto){
        return session.insert(namespace+"insert",noticeDto);
    }

    @Override
    public int update(NoticeDto noticeDto){
        return session.update(namespace+"update",noticeDto);
    }
}
