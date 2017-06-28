package cn.mahjong.utils.tag.easyui.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;


public class EasyuiTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException {
		return EVAL_PAGE;
	}
	
	public int doEndTag() throws JspException {
		try {
			HttpServletRequest request = (HttpServletRequest) this.pageContext.getRequest();
			JspWriter out = this.pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			
			/*css*/
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-easyui-1.5.2/themes/default/easyui.css\"></link>");
			
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
			sb.append(request.getContextPath());
			sb.append("/static/css/main/wu.css\" type=\"text/css\"></link>");
			
			sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"");
			sb.append(request.getContextPath());
			sb.append("/static/css/main/icon.css\" type=\"text/css\"></link>");
			
			/*js*/
			sb.append("<script type=\"text/javascript\" src=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-1.8.0.min.js\"></script>");
			
			sb.append("<script type=\"text/javascript\" src=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-easyui-1.5.2/jquery.easyui.min.js\"></script>");
			
			sb.append("<script type=\"text/javascript\" src=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js\"></script>");
			
			sb.append("<script type=\"text/javascript\" src=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-easyui-1.5.2/validatebox.js\"></script>");
			
			sb.append("<script type=\"text/javascript\" src=\"");
			sb.append(request.getContextPath());
			sb.append("/static/js/jquery-easyui-1.5.2/jquery.daterangebox.js\"></script>");

			out.print(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

}
