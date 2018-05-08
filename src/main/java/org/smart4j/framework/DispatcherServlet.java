package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName DispatcherServlet
 * @Description:
 * @Author Raymond Zhang
 * @Date 2018/5/7 20:03
 * @Version 1.0
 **/
public class DispatcherServlet extends HttpServlet{

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求方法和路径
        String resquestMethod =req.getMethod().toLowerCase();
        String resquestPath = req.getPathInfo();

        //get Handler
        Handler handler  = ControllerHelper.getHandler(resquestMethod,resquestPath);

        if (handler != null ){
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            //create request params
            Map<String ,Object> paramMap  =new HashMap<String, Object>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()){
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName,paramValue);
            }
            String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
            if(StringUtil.isNotEmpty(body)) {

                String[] params = StringUtil.splitString(body, "&");
                if (ArrayUtil.isNotEmpty(params)) {
                    //从发出的url获取后面的参数
                    for (String param : params) {
                        String[] array = StringUtil.splitString(param, "=");
                        if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                            String paramName = array[0];
                            String paramValue = array[1];
                            paramMap.put(paramName, paramValue);
                        }
                    }
                }
            }
            Param param  = new Param(paramMap);
            Method  actionMethod =handler.getActionMethod();
            Object result = param.isEmpty() ? ReflectionUtil.invokeMethod(controllerBean, actionMethod) : ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);

            if(result instanceof View){
                View view =(View) result;
                String path =view.getPath();
                if(StringUtil.isNotEmpty(path)){
                    if(path.startsWith("/")){

                        resp.sendRedirect(req.getContextPath()+path);
                    }else{
                        Map<String,Object> model = view.getModel();
                        for(Map.Entry<String,Object> entry : model.entrySet()){
                            req.setAttribute(entry.getKey(),entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath()+path).forward(req,resp);

                    }
                }
            } else if(result instanceof Data){
             Data data = (Data) result;
             Object model =data.getModel();
             if(model != null){
                 resp.setContentType("application/json");
                 resp.setCharacterEncoding("UTF-8");
                 PrintWriter printWriter = resp.getWriter();
                 String json = JsonUtil.toJson(model);
                 printWriter.write(json);
                 printWriter.flush();
                 printWriter.close();
             }
            }

        }




    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        HelperLoader.init();
        //用于注册Servlet
        ServletContext servletContext = config.getServletContext();
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath()+"*");
        //注册处理静态资源的servlet
        ServletRegistration assetServlet = servletContext.getServletRegistration("default");
        assetServlet.addMapping(ConfigHelper.getAppAssetPath()+"*");

    }
}
