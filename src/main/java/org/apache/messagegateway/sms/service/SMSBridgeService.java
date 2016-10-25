package org.apache.messagegateway.sms.service;

import java.util.Collection;

import org.apache.messagegateway.sms.domain.SMSBridge;
import org.apache.messagegateway.sms.repository.ProviderDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="apacheServiceBridge")
public class SMSBridgeService {

	//TODO://Do we need implement ApplicationEventPublisherAware?
	private ApplicationEventPublisher applicationEventPublisher ;
	
	private final ProviderDetailsRepository smsBridgeConfigRepository ;
	
	@Autowired
	public SMSBridgeService(final ProviderDetailsRepository smsBridgeConfigRepository) {
		this.smsBridgeConfigRepository = smsBridgeConfigRepository ;
	}
	
	/*@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}*/

	public Collection<SMSBridge> retrieveProviderDetails(final String tenantId) {
		//return this.smsBridgeConfigRepository.fetchAllProvidersBasedOnTenant(tenantId) ;
		return SMSBridgeTempUtils.getInstance().getSMSProviders(tenantId) ;
	}
	
	@Transactional
    public Long createSmsBridgeConfig(final SMSBridge smsBridgeConfig) {
        final SMSBridge newSMSmsBridge = this.smsBridgeConfigRepository.save(smsBridgeConfig);
        return newSMSmsBridge.getId();
    }
}
