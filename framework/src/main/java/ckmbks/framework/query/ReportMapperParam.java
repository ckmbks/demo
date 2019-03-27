package ckmbks.framework.query;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

public class ReportMapperParam {
    private String value = "";

    @XmlValue
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String name = "";

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String type;

    @XmlAttribute
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getParamValue(Object param) {
        switch (type.toLowerCase()) {
            case "like":
                return "%" + param.toString() + "%";
            case "leftlike":
                return "%" + param.toString();
            case "rightlike":
                return param.toString() + "%";
            default:
                return param.toString();
        }
    }
}
