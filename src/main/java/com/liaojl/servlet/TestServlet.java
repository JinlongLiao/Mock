package com.liaojl.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author LiaoJL
 * @description TODO
 * @file TestServlet.java
 * @email jinlongliao@foxmail.com
 * @date 2020/5/17 09:15
 */
@WebServlet("/test/*")
public class TestServlet extends HttpServlet {
    /**
     * 方法	结果
     * URL	http://127.0.0.1:8080/test/test2?a=2342
     * getServletPath	/test
     * getContextPath	""
     * getPathInfo	/test2
     * getPathTranslated()	/private/var/folders/t1/czp1j7dx6kz5c_25_2glbfw40000gn/T/undertow-docbase.2053444027420375252.8080/test2
     * getRealPath("/")	/private/var/folders/t1/czp1j7dx6kz5c_25_2glbfw40000gn/T/undertow-docbase.2053444027420375252.8080
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String contextPath = req.getContextPath();
        String pathInfo = req.getPathInfo();
        String pathTranslated = req.getPathTranslated();

        ServletContext servletContext = req.getServletContext();
        String realPath = servletContext.getRealPath("/");
        Set<String> resourcePaths = servletContext.getResourcePaths("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}
