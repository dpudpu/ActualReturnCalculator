package my.examples.arc.dto;

public class MyGoodsListDto {
    private int rownum;
    private String id;
    private String goodsName;
    private int investPeriod;
    private int myPrice;
    private Long prfRto;
    private double cms;
    private double profits; // 수익금

    public double getProfits() {
        return profits;
    }

    public void setProfits(double profits) {
        this.profits = profits;
    }


    public int getRownum() {
        return rownum;
    }

    public void setRownum(int rownum) {
        this.rownum = rownum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getInvestPeriod() {
        return investPeriod;
    }

    public void setInvestPeriod(int investPeriod) {
        this.investPeriod = investPeriod;
    }

    public int getMyPrice() {
        return myPrice;
    }

    public void setMyPrice(int myPrice) {
        this.myPrice = myPrice;
    }

    public Long getPrfRto() {
        return prfRto;
    }

    public void setPrfRto(Long prfRto) {
        this.prfRto = prfRto;
    }

    public double getCms() {
        return cms;
    }

    public void setCms(double cms) {
        this.cms = cms;
    }
}
