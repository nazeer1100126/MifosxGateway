package org.apache.messagegateway.sms.api;

import java.util.Collection;

import org.apache.messagegateway.sms.domain.SMSBridge;
import org.apache.messagegateway.sms.service.SMSBridgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/smsbridge")
public class SmsBridgeApiResource {

	private final SMSBridgeService smsBridgeService ;
	
	@Autowired
    public SmsBridgeApiResource(final SMSBridgeService smsBridgeService) {
		this.smsBridgeService = smsBridgeService ;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> createSMSBridgeConfig(@RequestBody final SMSBridge smsBridgeConfig) {
        String apikey = "" ;
        return new ResponseEntity<>(apikey, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tenantId}/{apiKey}", method = RequestMethod.PUT, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String>updateSMSBridgeConfig(@PathVariable("tenantId") final String tenantId, @PathVariable("apiKey") final String apiKey) {
        return new ResponseEntity<>(apiKey, HttpStatus.ACCEPTED);
    }
    
    @RequestMapping(value = "/{tenantId}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<Collection<SMSBridge>> getAllSMSBridgeConfigs(@PathVariable("tenantId") final String tenantId) {
        Collection<SMSBridge> bridges = this.smsBridgeService.retrieveProviderDetails(tenantId) ;
        return new ResponseEntity<>(bridges, HttpStatus.OK);
    }

    @RequestMapping(value = "/{tenantId}/{apiKey}", method = RequestMethod.GET, consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<String> getSMSBridgeConfig(@PathVariable("tenantId") final String tenantId, @PathVariable("apiKey") final String apiKey) {
        String apikey = "" ;
        return new ResponseEntity<>(apikey, HttpStatus.CREATED);
    }
}
