package com.fastcampus.toy2_7.domain;

public class PageHandler {
    private SearchCondition sc;
    private int pageSize = 10; // 한 페이지당 게시물 갯수
    private int page; // 현재 페이지
    private String  option;
    private String  keyword;
    public  final int NAV_SIZE = 10; // page navigation size
    private int totalCnt; // 게시물의 총 갯수
    private int totalPage; // 전체 페이지의 갯수
    private int beginPage; // 화면에 보여줄 첫 페이지
    private int endPage; // 화면에 보여줄 마지막 페이지
    private boolean showNext = false; // 이후를 보여줄지의 여부. endPage==totalPage이면, showNext는 false
    private boolean showPrev = false; // 이전을 보여줄지의 여부. beginPage==1이 아니면 showPrev는 false

    public PageHandler(int totalCnt, int page){
        this(totalCnt, page, 10);
    }

    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.page = page;
        this.pageSize = pageSize;
    }
}
