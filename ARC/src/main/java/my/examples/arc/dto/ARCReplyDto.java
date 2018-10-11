package my.examples.arc.dto;

public class ARCReplyDto {
    private int reply_idx;
    private int member_idx;
    private int parent_idx;
    private String content;

    public ARCReplyDto(int member_idx, Integer parent_idx, String content) {
        this.member_idx = member_idx;
        this.parent_idx = parent_idx;
        this.content = content;
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
