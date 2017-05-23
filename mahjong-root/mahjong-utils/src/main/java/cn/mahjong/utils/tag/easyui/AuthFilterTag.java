package cn.mahjong.utils.tag.easyui;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
/**
 * 
 * @Title:AuthFilterTag
 * @description:列表按钮权限过滤
 */
public class AuthFilterTag extends TagSupport{
	
	private static final long serialVersionUID = 7426065676997092470L;
	/**列表容器的ID*/
	protected String name;
	
	public int doStartTag() throws JspException {
		return super.doStartTag();
	}
	
	public int doEndTag() throws JspException {
		try {
			JspWriter out = this.pageContext.getOut();
				out.print(end().toString());
				out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
		
	}
	protected Object end() {
		StringBuilder out = new StringBuilder();
		getAuthFilter(out);
		return out;
	}
	/**
	 * 获取隐藏按钮的JS代码
	 * @param out
	 */
	@SuppressWarnings("unchecked")
	protected void getAuthFilter(StringBuilder out) {
		out.append("<script type=\"text/javascript\">");
		out.append("$(document).ready(function(){");
		List<String> nolist = (List<String>) super.pageContext.getRequest().getAttribute("noauto_operationCodes");
		if(nolist!=null&&nolist.size()>0){
			for(String s:nolist){
				out.append("$(\"#"+name+"\").find(\"#"+s.replaceAll(" ", "")+"\").hide();");
			}
		}
		out.append("});");
		out.append("</script>");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
