package com.angmas.mutation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.service.XmlToPolicyMappingService;

@RestController
public class MutationController {

	Logger logger = LoggerFactory.getLogger(MutationController.class);
	
	@PostMapping("/mutate")
	public List<Policy> getPolicy(@RequestBody String requestBody) {
		XmlToPolicyMappingService xml2policyObject = new XmlToPolicyMappingService();
		List<Policy> policies = new ArrayList<>();
		try {
			policies = xml2policyObject.mapPolicies(requestBody);
			return policies;

		} catch (XMLStreamException e) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST, "Cannot process this XML", e);
		}
	}
}
