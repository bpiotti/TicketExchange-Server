package com.benpiotti.cs252lab6.servlet;

import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Properties;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import com.benpiotti.cs252lab6.util.Config;



@WebServlet(name="/InitServlet",
        urlPatterns="/initservlet",
        loadOnStartup=1,
        description="Configuration Servlet")
public class InitServlet extends HttpServlet {


    private static final org.slf4j.Logger log = LoggerFactory.getLogger(InitServlet.class);

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        printConfigProperties();

        Properties props;
        props = Config.getProps();

        // Set response content type
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + "Application Popertie$$$$" + "</h1>");
        Enumeration<?> e = props.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            log.debug("Key : {}, Value : {}", key, value);
            out.println(key + " = " + value + "<br> </br>");
        }
        out.close();
        return;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    }

    private void printConfigProperties() {
        Properties props = Config.getInstance().getProps();

        Enumeration<?> e = props.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = props.getProperty(key);
            log.debug("Key : {}, Value : {}", key, value);
        }
    }


}