package my.examples.arc.servlet;

public class InvestGoodsDto {
    private int goodsIndex;
    private int goodsCode;
    private String goodsName;
    private int profitRatio;
    private int commissions;

    public InvestGoodsDto(int goodsIndex, int goodsCode, String goodsName, int profitRatio, int commissions) {
        this.goodsIndex = goodsIndex;
        this.goodsCode = goodsCode;
        this.goodsName = goodsName;
        this.profitRatio = profitRatio;
        this.commissions = commissions;
    }

    public int getGoodsIndex() {
        return goodsIndex;
    }

    public void setGoodsIndex(int goodsIndex) {
        this.goodsIndex = goodsIndex;
    }

    public int getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(int goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getProfitRatio() {
        return profitRatio;
    }

    public void setProfitRatio(int profitRatio) {
        this.profitRatio = profitRatio;
    }

    public int getCommissions() {
        return commissions;
    }

    public void setCommissions(int commissions) {
        this.commissions = commissions;
    }
}
