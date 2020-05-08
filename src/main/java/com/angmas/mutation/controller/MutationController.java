package com.angmas.mutation.controller;

import java.util.ArrayList;
import java.util.List;
import javax.xml.stream.XMLStreamException;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.angmas.mutation.domain.Policy;
import com.angmas.mutation.service.XmlToPolicyMappingService;

@RestController
public class MutationController {

	@PostMapping("/mutate")
	public List<Policy> getPolicy(@RequestBody String requestBody) {
		XmlToPolicyMappingService xml2policyObject = new XmlToPolicyMappingService();
		List<Policy> policies = new ArrayList<>();
		try {
			policies = xml2policyObject.mapPolicies(requestBody);
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return policies;
	}
}
