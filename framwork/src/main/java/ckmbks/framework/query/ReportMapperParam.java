package ckmbks.framework.query;

import ckmbks.framework.io.FileUtil;
import ckmbks.framework.map.CaseInsensitiveMap;
import ckmbks.framework.system.SystemUtil;
import ckmbks.framework.util.StrUtil;
import ckmbks.framework.util.XmlUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import javax.xml.bind.annotation.XmlAttribute;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class ReportMapperParam {

    @Getter
    @Setter
    private String value = "";

    private String name = "";
    @XmlAttribute
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    private boolean isFixed;
    @XmlAttribute
    public boolean isFixed() {
        return isFixed;
    }
    public void setFixed(boolean fixed) {
        isFixed = fixed;
    }
}
