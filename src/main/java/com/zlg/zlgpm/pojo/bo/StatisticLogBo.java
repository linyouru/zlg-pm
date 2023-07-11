package com.zlg.zlgpm.pojo.bo;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 15:00:57
 */
public class StatisticLogBo {

    private Integer uid;
    private String nickName;
    private Integer trueTotal;
    private Integer theoryTotal;
    private Integer abnormal;
    private Integer miss;
    private Integer total;

    public StatisticLogBo() {
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getTrueTotal() {
        return trueTotal;
    }

    public void setTrueTotal(Integer trueTotal) {
        this.trueTotal = trueTotal;
    }

    public Integer getTheoryTotal() {
        return theoryTotal;
    }

    public void setTheoryTotal(Integer theoryTotal) {
        this.theoryTotal = theoryTotal;
    }

    public Integer getAbnormal() {
        return abnormal;
    }

    public void setAbnormal(Integer abnormal) {
        this.abnormal = abnormal;
    }

    public Integer getMiss() {
        return miss;
    }

    public void setMiss(Integer miss) {
        this.miss = miss;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
