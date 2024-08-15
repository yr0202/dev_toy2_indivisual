package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.NoticeDto;

import java.util.List;
import java.util.Map;

public interface NoticeDao {
    int count() throws Exception;

    NoticeDto selectOne(int noticeID);

    List<NoticeDto> selectAll() throws Exception;

    List<NoticeDto> selectVisible();

    List<NoticeDto> selectPage(Map map);

    int deleteAll();

    int deleteById(int noticeID);

    int deleteByIds(List<Integer> noticeIDs);

    int insert(NoticeDto noticeDto);

    int insertAI(NoticeDto noticeDto);

    int update(NoticeDto noticeDto);

    void setDisplayFlagsN();
    void setDisplayFlags(List<Integer> displayFlagIds);
}
