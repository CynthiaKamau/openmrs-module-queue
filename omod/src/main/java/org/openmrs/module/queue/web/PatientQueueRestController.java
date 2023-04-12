/*
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.queue.web;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openmrs.module.queue.api.digitalSignage.QueueTicketAssignments;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.BaseRestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The main controller that exposes additional end points for order entry
 */
@Controller
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/queueutil")
public class PatientQueueRestController extends BaseRestController {
	
	@RequestMapping(method = RequestMethod.POST, value = "/assignticket")
	@ResponseBody
	public Object assignTicketToServicePoint(HttpServletRequest request) {
		String requestBody = null;
		try {
			
			requestBody = QueueTicketAssignments.fetchRequestBody(request.getReader());
			
		}
		catch (IOException e) {
			e.printStackTrace();
			String msg = e.getMessage();
			
			return "Error extracting request body" + msg;
		}
		
		if (requestBody != null) {
			
			ObjectMapper mapper = new ObjectMapper();
			JsonNode actualObj = null;
			try {
				actualObj = mapper.readTree(requestBody);
			}
			catch (IOException e) {
				throw new RuntimeException(e);
			}
			
			String servicePointName = actualObj.get("servicePointName").textValue();
			String ticketNumber = actualObj.get("ticketNumber").textValue();
			String status = actualObj.get("status").textValue();
			
			QueueTicketAssignments.updateTicketAssignment(servicePointName, ticketNumber, status);
			
			if (servicePointName.isEmpty() || ticketNumber.isEmpty() || status.isEmpty()) {
				return new ResponseEntity<Object>("One of the required fields is empty", new HttpHeaders(),
				        HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<Object>("Ticket successfully assigned!", new HttpHeaders(), HttpStatus.OK);
		}
		return new ResponseEntity<Object>("The request could not be interpreted!", new HttpHeaders(),
		        HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/active-tickets")
	public Object getActiveTickets() {
		return new ResponseEntity<Object>(QueueTicketAssignments.getActiveTicketAssignments(), new HttpHeaders(),
		        HttpStatus.OK);
	}
	
	/**
	 * @see BaseRestController#getNamespace()
	 */
	
	@Override
	public String getNamespace() {
		return "v1/queueutil";
	}
}
