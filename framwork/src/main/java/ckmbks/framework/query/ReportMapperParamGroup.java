package ckmbks.framework.query;

import lombok.Data;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ReportMapperParamGroup {

    private String name = "";
    @XmlAttribute
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private List<ReportMapperParam> params;
    @XmlElement(name="param")
    public List<ReportMapperParam> getParams() {
        return params;
    }
    public void setParams(List<ReportMapperParam> params) {
        this.params = params;
    }
}
