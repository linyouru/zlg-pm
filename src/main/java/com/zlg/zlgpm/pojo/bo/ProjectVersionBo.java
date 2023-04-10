package com.zlg.zlgpm.pojo.bo;

public class ProjectVersionBo {

    private Integer id;
    private String version;
    private Integer pid;
    private String name;

    public ProjectVersionBo() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProjectVersionBo{" +
                "id=" + id +
                ", version='" + version + '\'' +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                '}';
    }
}
