package my.examples.arc.dto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class ARCReplyDto {
    private int replyIdx;
    private int memberIdx;
    private String memberId;
    private int parentIdx;
    private String content;
    private long replyTime;

    public LocalDateTime getReplyTime() {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(replyTime), TimeZone.getDefault().toZoneId());

        return ldt;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }

    public ARCReplyDto() {}

    public ARCReplyDto(int memberIdx, String content){
        this.memberIdx = memberIdx;
        this.content = content;
    }

    public ARCReplyDto(int memberIdx, Integer parentIdx, String content) {
        this(memberIdx, content);
        this.parentIdx = parentIdx;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public int getReplyIdx() {
        return replyIdx;
    }

    public void setReplyIdx(int replyIdx) {
        this.replyIdx = replyIdx;
    }

    public int getMemberIdx() {
        return memberIdx;
    }

    public void setMemberIdx(int memberIdx) {
        this.memberIdx = memberIdx;
    }

    public int getParentIdx() {
        return parentIdx;
    }

    public void setParentIdx(int parentIdx) {
        this.parentIdx = parentIdx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
