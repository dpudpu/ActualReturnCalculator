package my.examples.arc.servlet;

public class ArcDto {
    private String gdsNm;
    private int investPeriod;
    private Long prfRto;
    private int myPrice;
    private int idx;
    private String id;
    private int idxCode;
    private float cms;
    private int gdsCd;
    private double investPrice;


    /*public ArcDto(int idx, String id, int idxCode, int investPeriod, int myPrice) {
        this.idx = idx;
        this.id = id;
        this.idxCode = idxCode;
        this.investPeriod = investPeriod;
        this.myPrice = myPrice;
    }*/

    public ArcDto() {
    }

    public ArcDto(String gdsNm, int investPeriod, Long prfRto, int myPrice) {
        this.gdsNm = gdsNm;
        this.investPeriod = investPeriod;
        this.prfRto = prfRto;
        this.myPrice = myPrice;
    }

    public String getGdsNm() {
        return gdsNm;
    }

    public void setGdsNm(String gdsNm) {
        this.gdsNm = gdsNm;
    }

    public Long getPrfRto() {
        return prfRto;
    }

    public void setPrfRto(Long prfRto) {
        this.prfRto = prfRto;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdxCode() {
        return idxCode;
    }

    public void setIdxCode(int idxCode) {
        this.idxCode = idxCode;
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

    public float getCms() {
        return cms;
    }

    public void setCms(float cms) {
        this.cms = cms;
    }

    public int getGdsCd() {
        return gdsCd;
    }

    public void setGdsCd(int gdsCd) {
        this.gdsCd = gdsCd;
    }

    public double getInvestPrice() {
        return investPrice;
    }

    public void setInvestPrice(double investPrice) {
        this.investPrice = investPrice;
    }
}
