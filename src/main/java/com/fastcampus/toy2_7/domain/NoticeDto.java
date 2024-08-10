package com.fastcampus.toy2_7.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class NoticeDto {
    private int noticeID;
    private String noticeTitle;
    private String noticeContent;
    private int priority;
    private String status; // 없어도 될 것 같은데...
    private String regId;
    private LocalDateTime regDate;
    private String modId;
    private LocalDateTime modDate;

    public NoticeDto() {}
    public NoticeDto(int noticeID, String noticeTitle, String noticeContent){
        this.noticeID = noticeID;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
    }
    public NoticeDto( int noticeID, String noticeTitle,String noticeContent, String regId, LocalDateTime regDate){
        this.noticeID = noticeID;
        this.noticeTitle = noticeTitle;
        this.regDate = regDate;
        this.regId = regId;
        this.noticeContent = noticeContent;
    }

    @Override
    public String toString() {
        return "NoticeDto{" +
                "noticeID=" + noticeID +
                ", noticeTitle='" + noticeTitle + '\'' +
                ", noticeContent='" + noticeContent + '\'' +
                ", priority=" + priority +
                ", status='" + status + '\'' +
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
        return noticeID == noticeDto.noticeID && priority == noticeDto.priority && Objects.equals(noticeTitle, noticeDto.noticeTitle) && Objects.equals(noticeContent, noticeDto.noticeContent) && Objects.equals(status, noticeDto.status) && Objects.equals(regId, noticeDto.regId) && Objects.equals(regDate, noticeDto.regDate) && Objects.equals(modId, noticeDto.modId) && Objects.equals(modDate, noticeDto.modDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeID, noticeTitle, noticeContent, priority, status, regId, regDate, modId, modDate);
    }

    public int getNoticeID() {
        return noticeID;
    }

    public void setNoticeID(int noticeID) {
        this.noticeID = noticeID;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }

    public String getModId() {
        return modId;
    }

    public void setModId(String modId) {
        this.modId = modId;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }

    public void setModDate(LocalDateTime modDate) {
        this.modDate = modDate;
    }



}