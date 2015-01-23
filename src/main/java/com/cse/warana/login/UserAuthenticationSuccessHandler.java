package com.cse.warana.login;

import com.cse.warana.constants.SessionConstants;
import com.cse.warana.model.User;
import com.cse.warana.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: Sashika
 * Date: 12/16/14
 * Time: 12:18 PM
 */
public class UserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static final Logger LOG = LoggerFactory.getLogger(UserAuthenticationSuccessHandler.class);

    private RequestCache requestCache = new HttpSessionRequestCache();

    @Autowired
    @Qualifier("loginService")
    private LoginService loginService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);

        //get the current session or create a new session if there is no session
        HttpSession httpSession = request.getSession();

        String username = authentication.getName();
        User user = loginService.getUser(username);

        //set the attributes to the session
        httpSession.setAttribute(SessionConstants.USER_ID, user.getUserId());
        httpSession.setAttribute(SessionConstants.USER_NAME, username);
        if (user.getPrivilegeLvl() == 1) {
            httpSession.setAttribute(SessionConstants.USER_ROLE, "ADMIN");
        } else {
            httpSession.setAttribute(SessionConstants.USER_ROLE, "USER");
        }
        httpSession.setAttribute(SessionConstants.FIRST_NAME, user.getFirstName());
        httpSession.setAttribute(SessionConstants.LAST_NAME, user.getLastName());

        if (savedRequest == null) {
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }
        String targetUrlParameter = getTargetUrlParameter();
        if (isAlwaysUseDefaultTargetUrl() || (targetUrlParameter != null && StringUtils.hasText(request.getParameter(targetUrlParameter)))) {
            requestCache.removeRequest(request, response);
            super.onAuthenticationSuccess(request, response, authentication);

            return;
        }

        clearAuthenticationAttributes(request);

        LOG.info("User Successfully login to the system as {}", username);

        // Use the DefaultSavedRequest URL
        String targetUrl = savedRequest.getRedirectUrl();
        logger.debug("Redirecting to DefaultSavedRequest Url: " + targetUrl);
        getRedirectStrategy().sendRedirect(request, response, targetUrl);

    }
}
