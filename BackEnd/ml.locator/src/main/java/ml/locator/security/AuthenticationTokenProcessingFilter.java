/**
 * 
 */
package ml.locator.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

/**
 * Jun 27, 2016
 * @author Alex Iakovenko
 * @email aleks.iakovenko@gmail.com
 */
public class AuthenticationTokenProcessingFilter extends GenericFilterBean{
	
	private UserDetailsService userDetailsService;
	
	public AuthenticationTokenProcessingFilter(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}
	
	/* (non-Javadoc)
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = this.getAsHttpServletRequest(request);
		String authToken = extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);
		
		if (userName != null){
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
			if(TokenUtils.isTokenValid(authToken, userDetails)){
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(request, response);
	}
	
	private HttpServletRequest getAsHttpServletRequest(ServletRequest request){
		
		if(request instanceof HttpServletRequest){
			return (HttpServletRequest)request;
		} else {
			throw new RuntimeException("Expecting an Http reques");
		}
	}
	
	private String extractAuthTokenFromRequest(HttpServletRequest request){
		String authToken = request.getHeader("X-Auth-Token");
		
		if(authToken == null){
			authToken = request.getParameter("token");
		}
		
		return authToken;
	}

}
