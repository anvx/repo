package com.jtmog.alukyanov.filter;

import javax.servlet.*;
import java.io.IOException;

public class CheckNameLicense implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        String license = servletRequest.getParameter("license");
        String clientName = servletRequest.getParameter("clientName");

        if (license == null) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/checklicense");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        if (clientName == null || clientName.equals("")) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/checkname");
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
