package com.zlg.zlgpm.pojo.bo;

/**
 * @author linyouru
 * @version 1.0
 * @date 2023/7/12 09:32:05
 */
public class StatisticWorkTimeBo {
    private Integer mid;
    private Integer workTime;
    private String moduleName;

    public StatisticWorkTimeBo() {
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Integer workTime) {
        this.workTime = workTime;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
}
