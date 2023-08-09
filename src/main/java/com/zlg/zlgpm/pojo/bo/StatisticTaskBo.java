package com.zlg.zlgpm.pojo.bo;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/11 10:07:54
 */
public class StatisticTaskBo {

    private Integer uid;
    private String nickName;
    private Integer taskTotal;
    private Integer taskTimeoutCount;
    private Integer feedbackCount;
    private Integer acceptCount;
    private Integer total;
    private Integer qualitySum;
    private Integer documentSum;
    private Integer planWorkTimeSum;
    private Integer workTimeSum;
    private Integer delayDayCount;

    public StatisticTaskBo() {
    }

    public Integer getDelayDayCount() {
        return delayDayCount;
    }

    public void setDelayDayCount(Integer delayDayCount) {
        this.delayDayCount = delayDayCount;
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

    public Integer getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(Integer taskTotal) {
        this.taskTotal = taskTotal;
    }

    public Integer getTaskTimeoutCount() {
        return taskTimeoutCount;
    }

    public void setTaskTimeoutCount(Integer taskTimeoutCount) {
        this.taskTimeoutCount = taskTimeoutCount;
    }

    public Integer getFeedbackCount() {
        return feedbackCount;
    }

    public void setFeedbackCount(Integer feedbackCount) {
        this.feedbackCount = feedbackCount;
    }

    public Integer getAcceptCount() {
        return acceptCount;
    }

    public void setAcceptCount(Integer acceptCount) {
        this.acceptCount = acceptCount;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getQualitySum() {
        return qualitySum;
    }

    public void setQualitySum(Integer qualitySum) {
        this.qualitySum = qualitySum;
    }

    public Integer getDocumentSum() {
        return documentSum;
    }

    public void setDocumentSum(Integer documentSum) {
        this.documentSum = documentSum;
    }

    public Integer getPlanWorkTimeSum() {
        return planWorkTimeSum;
    }

    public void setPlanWorkTimeSum(Integer planWorkTimeSum) {
        this.planWorkTimeSum = planWorkTimeSum;
    }

    public Integer getWorkTimeSum() {
        return workTimeSum;
    }

    public void setWorkTimeSum(Integer workTimeSum) {
        this.workTimeSum = workTimeSum;
    }

}
