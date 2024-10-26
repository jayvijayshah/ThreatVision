package com.nvidia.hackathon.filter;

import java.io.IOException;
import java.util.UUID;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nvidia.hackathon.util.Misc;

@Priority(1)
@Provider
public class LogFilter implements ContainerRequestFilter, ContainerResponseFilter {

	private static final Logger LOG = LogManager.getLogger(LogFilter.class.getName());
	@Context
	private HttpServletRequest httpRequest;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		httpRequest.setCharacterEncoding("UTF-8");
		UriInfo info = requestContext.getUriInfo();
		if (!info.getBaseUri().getPath().contains("api/")) {
			return;
		}

		requestContext.setProperty("requestTime", System.currentTimeMillis());
		requestContext.setProperty("requestId", UUID.randomUUID().toString());
		StringBuilder sb = new StringBuilder();
		sb.append("Endpoint: ").append(requestContext.getUriInfo().getPath());
		sb.append(" - IP: ").append(Misc.getIpAddr(httpRequest));
		sb.append(" - Header: ").append(requestContext.getHeaders());
		sb.append(" - Path Params: ").append(requestContext.getUriInfo().getPathParameters());
		sb.append(" - Query Params: ").append(requestContext.getUriInfo().getQueryParameters());
		LOG.info("=================> HTTP REQUEST : {} : {}", requestContext.getProperty("requestId"), sb);

	}

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		UriInfo info = requestContext.getUriInfo();
		if (!info.getBaseUri().getPath().contains("api/")) {
			return;
		}
		long startTime = (long) requestContext.getProperty("requestTime");
		StringBuilder sb = new StringBuilder();
		sb.append("Header: ").append(responseContext.getHeaders());
		String responseString = responseContext.getEntity().toString();
		sb.append(" - Entity: ").append(responseString);
		sb.append(" - TimeTaken: ").append(System.currentTimeMillis() - startTime).append("ms");
		LOG.info("<================= HTTP RESPONSE : {} : {}", requestContext.getProperty("requestId"), sb);
	}

}
