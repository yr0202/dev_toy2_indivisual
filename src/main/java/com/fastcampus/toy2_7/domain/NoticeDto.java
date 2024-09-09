package com.fastcampus.toy2_7.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class NoticeDto {
    private int noticeID;
    private String title;
    private String content;
    private String fixedCheck;
    private String fixdeEndDate;
    private String displayCheck;
    private int viewCnt;
    private String regId;
    private Date regDate;
    private String modId;
    private Date modDate;

    // 생성자 만들어줘야함
    public NoticeDto() {}

    @Override
    public String toString() {
        return "NoticeDto{" +
                "noticeID=" + noticeID +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fixedCheck='" + fixedCheck + '\'' +
                ", fixdeEndDate='" + fixdeEndDate + '\'' +
                ", displayCheck='" + displayCheck + '\'' +
                ", viewCnt=" + viewCnt +
                ", regId='" + regId + '\'' +
                ", regDate=" + regDate +
                ", modId='" + modId + '\'' +
                ", modDate=" + modDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NoticeDto noticeDto = (NoticeDto) o;
        return noticeID == noticeDto.noticeID && viewCnt == noticeDto.viewCnt && Objects.equals(title, noticeDto.title) && Objects.equals(content, noticeDto.content) && Objects.equals(fixedCheck, noticeDto.fixedCheck) && Objects.equals(fixdeEndDate, noticeDto.fixdeEndDate) && Objects.equals(displayCheck, noticeDto.displayCheck) && Objects.equals(regId, noticeDto.regId) && Objects.equals(regDate, noticeDto.regDate) && Objects.equals(modId, noticeDto.modId) && Objects.equals(modDate, noticeDto.modDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeID, title, content, fixedCheck, fixdeEndDate, displayCheck, viewCnt, regId, regDate, modId, modDate);
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFixedCheck() {
        return fixedCheck;
    }

    public void setFixedCheck(String fixedCheck) {
        this.fixedCheck = fixedCheck;
    }

    public String getFixdeEndDate() {
        return fixdeEndDate;
    }

    public void setFixdeEndDate(String fixdeEndDate) {
        this.fixdeEndDate = fixdeEndDate;
    }

    public String getDisplayCheck() {
        return displayCheck;
    }

    public void setDisplayCheck(String displayCheck) {
        this.displayCheck = displayCheck;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public Date getModDate() {
        return modDate;
    }

    public void setModDate(Date modDate) {
        this.modDate = modDate;
    }

}