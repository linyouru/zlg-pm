package com.zlg.zlgpm.pojo.bo;

public class ProjectBo {

    private static final long serialVersionUID =1L;

    private int id;
    private String name;
    private String version;
    private int uid;
    private String nickName;
    private String status;
    private String remark;
    private String updateTime;
    private String createTime;

    public ProjectBo() {}
    public ProjectBo(int id, String name, String version, int uid, String nickName, String status, String remark, String updateTime, String createTime) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.uid = uid;
        this.nickName = nickName;
        this.status = status;
        this.remark = remark;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ProjectVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version='" + version + '\'' +
                ", uid=" + uid +
                ", nickName='" + nickName + '\'' +
                ", status='" + status + '\'' +
                ", remark='" + remark + '\'' +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
