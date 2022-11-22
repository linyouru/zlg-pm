package com.zlg.zlgpm.pojo.bo;

public class TaskStatisticsBo {

    private String rateOfFinish;
    private String finishTaskNum;
    private String taskTotal;
    private String progressTaskNum;
    private String warningTaskNum;
    private String overtimeTaskNum;

    public TaskStatisticsBo() {
    }

    public TaskStatisticsBo(String rateOfFinish, String finishTaskNum, String taskTotal, String progressTaskNum, String warningTaskNum, String overtimeTaskNum) {
        this.rateOfFinish = rateOfFinish;
        this.finishTaskNum = finishTaskNum;
        this.taskTotal = taskTotal;
        this.progressTaskNum = progressTaskNum;
        this.warningTaskNum = warningTaskNum;
        this.overtimeTaskNum = overtimeTaskNum;
    }

    public String getRateOfFinish() {
        return rateOfFinish;
    }

    public void setRateOfFinish(String rateOfFinish) {
        this.rateOfFinish = rateOfFinish;
    }

    public String getFinishTaskNum() {
        return finishTaskNum;
    }

    public void setFinishTaskNum(String finishTaskNum) {
        this.finishTaskNum = finishTaskNum;
    }

    public String getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(String taskTotal) {
        this.taskTotal = taskTotal;
    }

    public String getProgressTaskNum() {
        return progressTaskNum;
    }

    public void setProgressTaskNum(String progressTaskNum) {
        this.progressTaskNum = progressTaskNum;
    }

    public String getWarningTaskNum() {
        return warningTaskNum;
    }

    public void setWarningTaskNum(String warningTaskNum) {
        this.warningTaskNum = warningTaskNum;
    }

    public String getOvertimeTaskNum() {
        return overtimeTaskNum;
    }

    public void setOvertimeTaskNum(String overtimeTaskNum) {
        this.overtimeTaskNum = overtimeTaskNum;
    }

    @Override
    public String toString() {
        return "TaskStatisticsBo{" +
                "rateOfFinish='" + rateOfFinish + '\'' +
                ", finishTaskNum='" + finishTaskNum + '\'' +
                ", taskTotal='" + taskTotal + '\'' +
                ", progressTaskNum='" + progressTaskNum + '\'' +
                ", warningTaskNum='" + warningTaskNum + '\'' +
                ", overtimeTaskNum='" + overtimeTaskNum + '\'' +
                '}';
    }
}
