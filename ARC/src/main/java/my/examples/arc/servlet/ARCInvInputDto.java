package my.examples.arc.servlet;

public class ARCInvInputDto {
    private String invPrdName;
    private int invMoney;
    private int invPeriod;

    public ARCInvInputDto(String invPrdName, int invMoney, int invPeriod) {
        this.invPrdName = invPrdName;
        this.invMoney = invMoney;
        this.invPeriod = invPeriod;
    }

    public String getInvPrdName() {
        return invPrdName;
    }

    public void setInvPrdName(String invPrdName) {
        this.invPrdName = invPrdName;
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
