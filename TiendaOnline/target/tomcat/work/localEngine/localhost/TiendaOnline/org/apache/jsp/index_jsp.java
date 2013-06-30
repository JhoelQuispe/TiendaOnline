package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01//EN\" \"HTTP://www.w3.org/TR/html4/strict.dtd\">\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<title>Online Media Store</title>\n");
      out.write("<META HTTP-EQUIV=\"CACHE-CONTROL\" CONTENT=\"NO-CACHE\">\n");
      out.write("<META HTTP-EQUIV=\"CONTENT-TYPE\" CONTENT=\"text/html; charset=UTF-8\">\n");
      out.write("<META HTTP-EQUIV=\"PRAGMA\" CONTENT=\"NO-CACHE\">\n");
      out.write("\n");
      out.write("<!--Stylesheets-->\n");
      out.write("<link rel=\"styleSheet\" type=\"text/css\" href=\"resources/css/blue/style.css\">\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"resources/css/css/ui-lightness/jquery-ui-1.8.20.custom.css\">\n");
      out.write("<!-- <link rel=\"styleSheet\" type=\"text/css\" href=\"resources/css/itxmain.css\"> -->\n");
      out.write("\n");
      out.write("<!--JavaScript-->\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery-1.7.2.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery.ba-hashchange.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery.tablesorter.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery-ui-1.8.20.custom.min.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery.cookie.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/hogan.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/lib/jquery.uitablefilter.js\" charset=\"utf-8\"></script>\n");
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/main.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/application-controller.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/presenter/list-user-presenter.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/presenter/edit-user-presenter.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/view/list-user-view.js\" charset=\"utf-8\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"resources/js/view/edit-user-view.js\" charset=\"utf-8\"></script>\n");
      out.write("\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("</body>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
