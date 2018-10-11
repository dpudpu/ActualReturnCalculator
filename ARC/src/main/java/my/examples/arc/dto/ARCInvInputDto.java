package my.examples.arc.dto;

public class ARCInvInputDto {
    private int invPrdIdx;
    private int invMoney;
    private int invPeriod;

    public ARCInvInputDto(int invPrdIdx, int invMoney, int invPeriod) {
        this.invPrdIdx = invPrdIdx;
        this.invMoney = invMoney;
        this.invPeriod = invPeriod;
    }

    public int getInvPrdIdx() {
        return invPrdIdx;
    }

    public void setInvPrdIdx(int invPrdIdx) {
        this.invPrdIdx = invPrdIdx;
    }

    public int getInvMoney() {
        return invMoney;
    }

    public void setInvMoney(int invMoney) {
        this.invMoney = invMoney;
    }

    public int getInvPeriod() {
        return invPeriod;
    }

    public void setInvPeriod(int invPeriod) {
        this.invPeriod = invPeriod;
    }
}
