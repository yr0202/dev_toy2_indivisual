package com.fastcampus.toy2_7.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageHandlerTest {

    @Test
    public void begin과endPageTest(){
        PageHandler ph = new PageHandler(250,1);
        assertTrue(ph.getBeginPage()==1);
        assertTrue(ph.getEndPage()==10);

        PageHandler ph2 = new PageHandler(250,12);
        ph2.print();
        assertTrue(ph2.getBeginPage()==11);
        assertTrue(ph2.getEndPage()==20);

        PageHandler ph3 = new PageHandler(250,10);
        ph3.print();
        assertTrue(ph3.getBeginPage()==1);
        assertTrue(ph3.getEndPage()==10);

        PageHandler ph4 = new PageHandler(250,13);
        assertTrue(ph4.getBeginPage()==11);
        assertTrue(ph4.getEndPage()==20);

        PageHandler ph5 = new PageHandler(250,23);
        ph5.print();
        assertTrue(ph5.getBeginPage()==21);
        assertTrue(ph5.getEndPage()==25);

        PageHandler ph6 = new PageHandler(251,23);
        ph6.print();
        assertTrue(ph6.getBeginPage()==21);
        assertTrue(ph6.getEndPage()==26);
    }



}