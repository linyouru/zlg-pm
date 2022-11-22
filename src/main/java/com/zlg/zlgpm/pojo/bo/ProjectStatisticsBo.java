package com.zlg.zlgpm.pojo.bo;

public class ProjectStatisticsBo {
    private Integer id;
    private String name;
    private String version;
    private Integer taskTotal;
    private Integer finishTaskNum;

    public ProjectStatisticsBo() {
    }

    public ProjectStatisticsBo(Integer id, String name, String version, Integer taskTotal, Integer finishTaskNum) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.taskTotal = taskTotal;
        this.finishTaskNum = finishTaskNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getTaskTotal() {
        return taskTotal;
    }

    public void setTaskTotal(Integer taskTotal) {
        this.taskTotal = taskTotal;
    }

    public Integer getFinishTaskNum() {
        return finishTaskNum;
    }

    public void setFinishTaskNum(Integer finishTaskNum) {
        this.finishTaskNum = finishTaskNum;
    }

    @Override
    public String toString() {
        return "ProjectStatisticsBo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", taskTotal=" + taskTotal +
                ", finishTaskNum=" + finishTaskNum +
                '}';
    }
}
