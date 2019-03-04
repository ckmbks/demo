package ckmbks.framework.query;

import ckmbks.framework.io.FileUtil;
import ckmbks.framework.map.CaseInsensitiveMap;
import ckmbks.framework.system.SystemUtil;
import ckmbks.framework.util.StrUtil;
import ckmbks.framework.util.XmlBeanUtil;
import ckmbks.framework.util.XmlUtil;
import lombok.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.io.FileFilter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "mapper")
public class ReportMapper {
    public static ReportMapper Create(String reportName) {
        return getAllReportConfig().get(reportName);
    }

    public static CaseInsensitiveMap<String, ReportMapper> getAllReportConfig() {
        var path = SystemUtil.getUserInfo().getCurrentDir();
        var files = FileUtil.loopFiles(path + "\\demo.dataaccess\\src\\main\\resources\\reportmapper\\", new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.getName().contains(".ReportConfig.xml");
            }
        });

        var result = new CaseInsensitiveMap<String, ReportMapper>();
        for (var file : files) {
            var fileStr = FileUtil.readString(file, Charset.defaultCharset());
            try {
                JAXBContext context = JAXBContext.newInstance(ReportMapper.class);
                Unmarshaller unMar = context.createUnmarshaller();
                var report = (ReportMapper) unMar.unmarshal(file);
                result.put(report.getName(), report);
            } catch (JAXBException e) {
                e.printStackTrace();
            }
//            var report = (ReportMapper)XmlBeanUtil.xmlToObj( ReportMapper.class,fileStr);
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

    @Getter
    @Setter
    private String reportSql = "";

    @Getter
    @Setter
    private String totalSql = "";

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

    public String getReportSql() {
        if (StrUtil.isEmpty(reportSql))
            throw new RuntimeException(StrUtil.format("请为报表{0}配置ReportSql", name));

        return reportSql;
    }

    public String getTotalSql() {
        if (StrUtil.isEmpty(totalSql))
            throw new RuntimeException(StrUtil.format("请为报表{0}配置TotalSql", name));

        return totalSql;
    }

}
