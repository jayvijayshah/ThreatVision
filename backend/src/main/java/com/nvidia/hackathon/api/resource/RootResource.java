package com.nvidia.hackathon.api.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.nvidia.hackathon.api.resource.config.ConfigResource;

import process.ProcessResource;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class RootResource {

	@Path("process")
	public ProcessResource getIncomingResource() {
		return new ProcessResource();
	}

	@Path("config")
	public ConfigResource getConfigResource() {
		return new ConfigResource();
	}

}
