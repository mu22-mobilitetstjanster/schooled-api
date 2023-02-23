package com.example.schooledcourseapi.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Log4j2
@Component
public class LogFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    log.info(request.getRemoteAddr() + " is connecting to endpoint " + request.getRequestURI());

    log.debug("Cookies: " + request.getCookies());
    log.debug("User agent: " + request.getHeader("User-Agent"));
    log.debug("Connection: " + request.getHeader("Connection"));

    filterChain.doFilter(request, response);
  }
}
