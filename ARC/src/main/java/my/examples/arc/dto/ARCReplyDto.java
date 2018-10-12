package my.examples.arc.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ARCReplyDto {
    private int reply_idx;
    private int member_idx;
    private String member_id;
    private int parent_idx;
    private String content;
    private long reply_time;

    public LocalDateTime getReply_time() {
        LocalDateTime ldt = LocalDateTime.ofInstant(Instant.ofEpochMilli(reply_time), TimeZone.getDefault().toZoneId());

        return ldt;
    }

    public void setReply_time(long reply_time) {
        this.reply_time = reply_time;
    }

    public ARCReplyDto() {}

    public ARCReplyDto(int member_idx, Integer parent_idx, String content) {
        this.member_idx = member_idx;
        this.parent_idx = parent_idx;
        this.content = content;
    }

    public String getMember_id() {
        return member_id;
    }

    public void setMember_id(String member_id) {
        this.member_id = member_id;
    }

    public int getReply_idx() {
        return reply_idx;
    }

    public void setReply_idx(int reply_idx) {
        this.reply_idx = reply_idx;
    }

    public int getMember_idx() {
        return member_idx;
    }

    public void setMember_idx(int member_idx) {
        this.member_idx = member_idx;
    }

    public int getParent_idx() {
        return parent_idx;
    }

    public void setParent_idx(int parent_idx) {
        this.parent_idx = parent_idx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
