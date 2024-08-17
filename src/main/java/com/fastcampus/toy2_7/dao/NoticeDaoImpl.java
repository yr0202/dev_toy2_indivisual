package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.NoticeDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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
    public List<NoticeDto> selectVisible(){
        return session.selectList(namespace+"selectVisible");
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
    public int insertAI(NoticeDto noticeDto){
        return session.insert(namespace+"insertAI",noticeDto);
    }

    @Override
    public int update(NoticeDto noticeDto){
        return session.update(namespace+"update",noticeDto);
    }

    @Override
    public void setDisplayFlagsN(){
        session.update(namespace+"setDisplayFlgsN");
    }

    @Override
    public void setDisplayFlags(@Param("ids") List<Integer> displayFlagIds){
        session.update(namespace+"setDisplayFlags",displayFlagIds);
    }

    // 페이징처리
    @Override
    public List<NoticeDto> selectPage(Map map) {
        return session.selectList(namespace+"selectPage",map);
    }
}
