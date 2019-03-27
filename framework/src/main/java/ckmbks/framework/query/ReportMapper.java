package ckmbks.framework.query;

import ckmbks.framework.io.FileUtil;
import ckmbks.framework.io.resource.ResourceUtil;
import ckmbks.framework.map.CaseInsensitiveMap;
import ckmbks.framework.system.SystemUtil;
import ckmbks.framework.util.StrUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.var;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

@XmlRootElement(name = "mapper")
public class ReportMapper {
    public static ReportMapper Create(String reportName) {
        return getAllReportConfig().get(reportName);
    }

    public static CaseInsensitiveMap<String, ReportMapper> getAllReportConfig() {
        var path = ResourceUtil.getResource("reportmapper");
        var files = FileUtil.loopFiles(path.getPath(), new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(".ReportConfig.xml");
            }
        });

        var result = new CaseInsensitiveMap<String, ReportMapper>();
        for (var file : files) {
            try {
                JAXBContext context = JAXBContext.newInstance(ReportMapper.class);
                Unmarshaller unMar = context.createUnmarshaller();
                var report = (ReportMapper) unMar.unmarshal(file);
                result.put(report.getName(), report);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    private String name = "";

    @XmlAttribute
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String desc = "";

    @XmlAttribute
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Getter
    @Setter
    private String prepareSql = "";

    @Setter
    private String reportSql = "";

    public String getReportSql() {
        if (StrUtil.isEmpty(reportSql))
            throw new RuntimeException(StrUtil.format("请为报表{0}配置ReportSql", name));

        return reportSql;
    }

    @Setter
    private String totalSql = "";

    public String getTotalSql() {
        if (StrUtil.isEmpty(totalSql))
            throw new RuntimeException(StrUtil.format("请为报表{0}配置TotalSql", name));

        return totalSql;
    }

    @Getter
    @Setter
    private String orderSql = "";

    private List<ReportMapperParamGroup> paramGroups;

    @XmlElement(name = "paramGroup")
    public List<ReportMapperParamGroup> getParamGroups() {
        return paramGroups;
    }

    public void setParamGroups(List<ReportMapperParamGroup> paramGroups) {
        this.paramGroups = paramGroups;
    }


}
