package process;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nvidia.hackathon.api.service.process.impl.ProcessServiceImpl;
import com.nvidia.hackathon.model.process.ProcessRequest;

@Produces(MediaType.APPLICATION_JSON)
public class ProcessResource {

	private static final Logger LOG = LogManager.getLogger(ProcessResource.class.getSimpleName());

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)

	public Response process(@Context ContainerRequestContext requestContext, @BeanParam ProcessRequest processRequest) {
		try {
			String response = ProcessServiceImpl.instance.process(processRequest);
			return Response.status(Status.OK).entity(response).build();
		} catch (Exception ex) {
			LOG.error("Exception ", ex);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity("").build();
		}
	}

}
