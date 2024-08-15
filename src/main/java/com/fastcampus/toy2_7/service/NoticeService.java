package com.fastcampus.toy2_7.service;

import com.fastcampus.toy2_7.domain.NoticeDto;

import java.util.List;

public interface NoticeService {
    /*
        - 게시물 전체 목록 보기(readAllNotices) : 컨트롤러의 list()호출 -> 서비스의 getPage() 호출 -> 가져온 페이지를 리스트에 담아서(List<BoardDto>) 반환
        - 게시물 1개 상세 보기(readNotice)
        - 게시물 작성(createNotice)
        - 게시물 전체 삭제(deleteAllNotices)
        - 게시물 1개 삭제(deleteNotice)
        - 게시물 N개 삭제(deleteNotices)
        - 게시물 수정(updateNotice)
        - 게시물 검색(searchNotices)
        */
    List<NoticeDto> readAllNotices() throws Exception;

    List<NoticeDto> readVisibleNotices();

    NoticeDto readNotice(int noticeID);

    int creatNotice(NoticeDto noticeDto);

    int creatAutoNotice(NoticeDto noticeDto);

    int deleteAllNotices();

    int deleteNotice(int noticeID);

    int deleteNotices(List<Integer> noticeIDs);

    int updateNotice(NoticeDto noticeDto);

    void updateDisplayFlags(List<Integer> displayFlagIds);
}
