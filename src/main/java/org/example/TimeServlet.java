package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import java.util.TimeZone;
import javax.servlet.ServletException;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timezone = request.getParameter("timezone");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("lastTimezone")) {
                    timezone = cookie.getValue();
                }
            }
        }

        TimeZone timeZone;
        if (timezone == null || timezone.isEmpty()) {
            timeZone = TimeZone.getTimeZone("UTC");
        } else {
            timeZone = TimeZone.getTimeZone(timezone);
        }

        Cookie timezoneCookie = new Cookie("lastTimezone", timezone);
        response.addCookie(timezoneCookie);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(timeZone);
        String currentTime = sdf.format(new Date());

        request.setAttribute("currentTime", currentTime);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/time.jsp");
        dispatcher.forward(request, response);
    }
}