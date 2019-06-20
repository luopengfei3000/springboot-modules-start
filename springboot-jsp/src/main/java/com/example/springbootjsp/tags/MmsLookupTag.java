package com.example.springbootjsp.tags;

import java.util.Arrays;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.example.springbootjsp.entity.ConfigLInfo;
import com.example.springbootjsp.service.ConfigLInfoService;
import com.example.springbootjsp.utils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

public class MmsLookupTag extends TagSupport {
    private static final long serialVersionUID = 1L;
    private String name;
    private String dataOptions;
    private String lookupCode;
    private String defaultValue;
    private String classStyle;
    private boolean isNull;
    private String title;
    private String exclusions;
    private final static String FORMAT_SELECT = "<select id=\"%s\" name=\"%s\" class=\"%s\" title= \"%s\" data-options=\"%s\">%s</select>"; 
    private final static String FORMAT_OPTION = "<option value=\"%s\" %s>%s</option>";
    private final static String SELECTED = "SELECTED";
    private final static String EMPTY = "";

    private final static ConfigLInfoService configLService = BeanUtils.getBean(ConfigLInfoService.class);

    public MmsLookupTag() {
        this.dataOptions = "width:180,editable:false,panelHeight:'auto',onShowPanel:comboboxOnShowPanel";
        this.classStyle = "easyui-combobox";
    }
    
    @Override
    public int doStartTag() throws JspException {
        JspWriter out = this.pageContext.getOut();
        try {
            //查询索引类型下拉列表
            List<ConfigLInfo> dtos = configLService.findConfigLInfoByConfigCode(this.getLookupCode());
            if (getIsNull()) {
                ConfigLInfo nullDTO = new ConfigLInfo();
                nullDTO.setDetailValue("");
                nullDTO.setDetailName("请选择");
                dtos.add(0, nullDTO);
            }
            StringBuilder sb = new StringBuilder();
            List<String> excludedValues = null;
            if (StringUtils.isNotEmpty(this.getExclusions())) {
                excludedValues = Arrays.asList(this.getExclusions().split(","));
            }
            for (ConfigLInfo dto : dtos) {
                if (excludedValues != null && excludedValues.contains(dto.getDetailValue())) {
                    continue;
                }
                if (StringUtils.equals(dto.getDetailValue(), this.getDefaultValue())) {
                    sb.append(String.format(FORMAT_OPTION, new Object[] {dto.getDetailValue(), SELECTED, dto.getDetailName()}));
                } else {
                    sb.append(String.format(FORMAT_OPTION, new Object[] {dto.getDetailValue(), EMPTY, dto.getDetailName()}));
                }
            }
            if (this.getTitle() == null) {
                this.setTitle(this.getName());
            }
            out.print(String.format(FORMAT_SELECT, new Object[] { getName(), getName(), getClassStyle(),
                    getTitle(), getDataOptions(), sb.toString() }));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return super.doStartTag();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataOptions() {
        return this.dataOptions;
    }

    public void setDataOptions(String dataOptions) {
        this.dataOptions = dataOptions;
    }

    public String getLookupCode() {
        return this.lookupCode;
    }

    public void setLookupCode(String lookupCode) {
        this.lookupCode = lookupCode;
    }

    public String getDefaultValue() {
        return this.defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getClassStyle() {
        return this.classStyle;
    }

    public void setClassStyle(String classStyle) {
        this.classStyle = classStyle;
    }

    public boolean getIsNull() {
        return this.isNull;
    }

    public void setIsNull(boolean isNull) {
        this.isNull = isNull;
    }

    public String getExclusions() {
        return exclusions;
    }

    public void setExclusions(String exclusions) {
        this.exclusions = exclusions;
    }
}
