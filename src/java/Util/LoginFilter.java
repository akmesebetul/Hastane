/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

/**
 *
 * @author Casper
 */

import entity.user;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter("/*")
public class LoginFilter implements Filter {
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; //süzgeç görevi görür
        HttpServletResponse res = (HttpServletResponse) response;
        
        String url = req.getRequestURI();
        
        user u = (user) req.getSession().getAttribute("valid_user");
        
        if (u == null) { //kullanıcı yoksa
            
            if (url.contains("secret") || url.contains("logout")) { //kayıtlı olmayan kullanıcı şartı
                res.sendRedirect(req.getContextPath()+"/login.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else { //varsa
            if (url.contains("register") || url.contains("login")) {
                 res.sendRedirect(req.getContextPath()+"/secret/secret.xhtml");
            } else if (url.contains("logout")) {
                req.getSession().invalidate(); //session gecersiz
                 res.sendRedirect(req.getContextPath()+"/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }
    }
    
    @Override
    public void destroy() {
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    
}

















/*import entity.user;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

/*@WebFilter("/") //filter olduğu belirtilir
public abstract class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request; //Container a gelen her bir istek , servlet sınıfına HttpServletRequest sınıfı ile iletilir

        HttpServletResponse res = (HttpServletResponse) response; //Container dışına verilecek olan response(cevap)  servlet tarafından HttpServletResponse ile verilir

        String url = req.getRequestURI();

        user u = (user) req.getSession().getAttribute("valid_user");

        if (u == null) { //kullanıcı yoksa

            if (url.contains("secret") || url.contains("logout")) { //kayıtlı olmayan kullanıcı şartı
                res.sendRedirect(req.getContextPath() + "/login.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        } else { //varsa
            if (url.contains("register") || url.contains("login")) { //kayıtlı kullanıcı şartı
                res.sendRedirect(req.getContextPath() + "/secret/secret.xhtml");
            } else if (url.contains("logout")) {
                req.getSession().invalidate();
                res.sendRedirect(req.getContextPath() + "/index.xhtml");
            } else {
                chain.doFilter(request, response);
            }
        }

    }

}*/
