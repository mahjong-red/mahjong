package cn.mahjong.utils.tag.easyui;

import java.io.IOException;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import cn.mahjong.utils.ObjectUtil;

/**
 * 
 * 选择下拉框
 */
public class EnumSelectTag extends TagSupport {

	private static final long serialVersionUID = 1;
	private String enumClass;
	private String name;
	private String id;
	private String defaultVal;
	private String css;
	private String dataOptions;//当css为jquery组件
	private String style;
	
	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer end() {
		StringBuffer sb = new StringBuffer("<select id=");
		sb.append(id);
		sb.append(" name=");
		sb.append(name);
		sb.append(" class=");
		sb.append(css);
		sb.append(" style=");
		sb.append(style);
		sb.append(" data-options=\"");
		sb.append(dataOptions);
		sb.append("\">");
		sb.append("<option value=''>请选择</option>");
		
		Object ts[] = null;
		try {
			System.out.println(enumClass);
			ts = Class.forName(enumClass).getEnumConstants();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		for (Object t : ts) {
			int val = (Integer) ObjectUtil.getInvokeValue(t, "getVal");
			String description = (String) ObjectUtil.getInvokeValue(t, "getDescription");
			sb.append("<option value=");
			sb.append(val);
			if (StringUtils.isNotBlank(defaultVal) && defaultVal.equals(String.valueOf(val))) {
				sb.append(" selected='selected'");
			}
			sb.append(" >");
			sb.append(description);
			sb.append("</option>");
		}
		sb.append("</select>");
		return sb;
	}

	public String getEnumClass() {
		return enumClass;
	}

	public void setEnumClass(String enumClass) {
		this.enumClass = enumClass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDefaultVal() {
		return defaultVal;
	}

	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}

	public String getCss() {
		return css;
	}

	public void setCss(String css) {
		this.css = css;
	}

	public String getDataOptions() {
		return dataOptions;
	}

	public void setDataOptions(String dataOptions) {
		this.dataOptions = dataOptions;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
	
}
