package com.sprintboot.app.component;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class UrlTrailingSlashHandlerFilter extends OncePerRequestFilter {

	/*
	 * //The below method is deprecated
	 *
	 * 
	 * @Override protected void doFilterInternal(HttpServletRequest request,
	 * HttpServletResponse response, FilterChain filterChain) throws
	 * ServletException, IOException {
	 * 
	 * // Redirecting UrlHandlerFilter filter =
	 * UrlHandlerFilter.trimTrailingSlash("/**").andRedirect(HttpStatus.
	 * PERMANENT_REDIRECT).build();
	 * 
	 * // Or transparently handle those for HTTP clients, without any redirect:
	 * UrlHandlerFilter filter =
	 * UrlHandlerFilter.trimTrailingSlash("/**").andHandleRequest().build();
	 * filter.doFilter(request, response, filterChain);
	 * 
	 * }
	 */

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String requestUri = request.getRequestURI();

		if (requestUri.endsWith("/")) {
			String newUrl = requestUri.substring(0, requestUri.length() - 1);
			response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
			response.setHeader(HttpHeaders.LOCATION, newUrl);
			return;
		}

		filterChain.doFilter(request, response);
	}

}
