package com.fastcampus.toy2_7.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class NoticeServiceImplTest {
    @Autowired
    NoticeServiceImpl noticeService;

    @Test
    public void getCount() {

    }

    @Test
    public void readAllNotices() {
    }

    @Test
    public void getPage() {
    }

    @Test
    public void readVisibleNotices() {
    }

    @Test
    public void readNotice() {
    }

    @Test
    public void creatNotice() {
    }

    @Test
    public void creatAutoNotice() {
    }

    @Test
    public void deleteAllNotices() {
    }

    @Test
    public void deleteNotice() {
    }

    @Test
    public void deleteNotices() {
    }

    @Test
    public void updateNotice() {
    }

    @Test
    public void updateDisplayFlags() {
    }
}