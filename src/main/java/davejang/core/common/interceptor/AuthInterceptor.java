package davejang.core.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession(false);
        boolean authExists = false;
        String currentUser;

        if (session != null && session.getAttribute("username") != null) {
            System.out.println((String)session.getAttribute("username"));
            currentUser = (String)session.getAttribute("username");
            request.setAttribute("currentUser", currentUser);
            authExists = true;
        }


        request.setAttribute("authExists", authExists);
        System.out.println("authExists:" + authExists);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if(modelAndView != null && request.getSession().getAttribute("username") != null) {
            modelAndView.addObject("authExists", request.getAttribute("authExists"));
            modelAndView.addObject("currentUser", request.getAttribute("currentUser"));
        }
    }
}
