package com.fastcampus.toy2_7.dao;

import com.fastcampus.toy2_7.domain.NoticeDto;

import java.util.List;

public interface NoticeDao {
    int count() throws Exception;

    NoticeDto selectOne(int noticeID);

    List<NoticeDto> selectAll() throws Exception;

    int deleteAll();

    int deleteById(int noticeID);

    int deleteByIds(List<Integer> noticeIDs);

    int insert(NoticeDto noticeDto);

    int update(NoticeDto noticeDto);
}
