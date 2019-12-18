 package com.pavan.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

@Path("yms")
public class InboundService {

	@POST
	@Path("appointment")
	@Produces("application/xml")
	public String handleAppointmentReq(String appointmentXml, @Context HttpServletRequest req) {
		return "";
	}

	@POST
	@Path("joborderresponse")
	@Produces("application/xml")
	public String jobOrderResponseReq(String jobOrderResponseXml, @Context HttpServletRequest req) {
		
		return "";
	}
}
