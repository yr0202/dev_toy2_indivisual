package com.fastcampus.toy2_7.service;

import com.fastcampus.toy2_7.dao.NoticeDao;
import com.fastcampus.toy2_7.domain.NoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    NoticeDao noticeDao;

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

    @Override
    public int getCount() throws Exception {
        return noticeDao.count();
    }
    @Override
    public List<NoticeDto> readAllNotices() throws Exception {
        return noticeDao.selectAll();
    }

    @Override
    public List<NoticeDto> getPage(Map map) {
        return noticeDao.selectPage(map);
    }

    @Override
    public List<NoticeDto> readVisibleNotices(){
        return noticeDao.selectVisible();
    }
    @Override
    public NoticeDto readNotice(int noticeID){
        return noticeDao.selectOne(noticeID);
    }
    @Override
    public int creatNotice(NoticeDto noticeDto){
        return noticeDao.insert(noticeDto);
    }
    @Override
    public int creatAutoNotice(NoticeDto noticeDto){
        return noticeDao.insertAI(noticeDto);
    }
    @Override
    public int deleteAllNotices(){
        return noticeDao.deleteAll(); // 삭제된 행의 수
    }
    @Override
    public int deleteNotice(int noticeID){
        return noticeDao.deleteById(noticeID);
    }
//    @Override
//    public void deleteNotices(List<Integer> noticeIDs){
//        if (noticeIDs != null)
//            noticeDao.deleteByIds(noticeIDs);
//    }
    @Override
    public int deleteNotices(List<Integer> noticeIDs){

        return    noticeDao.deleteByIds(noticeIDs);
    }
    @Override
    public int updateNotice(NoticeDto noticeDto){
        return noticeDao.update(noticeDto);
    }

    @Override
    public void updateDisplayFlags(List<Integer> displayFlagIds) {
        // 1. 모든 displayFlags들은 'n'을 기본값으로 설정
        noticeDao.setDisplayFlagsN();

        //2. 선택된 게시물들만 y로 재설정
        if (displayFlagIds != null ) {
            noticeDao.setDisplayFlags(displayFlagIds); // 선택된 공지의 display_flag를 'y'로 설정
        }
    }
}
