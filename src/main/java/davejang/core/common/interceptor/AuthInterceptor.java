package davejang.core.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

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
            currentUser = (String)session.getAttribute("username");
            request.setAttribute("currentUser", currentUser);
            authExists = true;
        }

        request.setAttribute("authExists", authExists);
        return true;
    }

}
