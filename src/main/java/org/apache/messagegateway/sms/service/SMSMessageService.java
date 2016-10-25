package org.apache.messagegateway.sms.service;

import java.util.Collection;
import java.util.Date;

import org.apache.messagegateway.sms.domain.SMSMessage;
import org.apache.messagegateway.sms.providers.SMSProviderFactory;
import org.apache.messagegateway.sms.repository.SmsOutboundMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SMSMessageService {

	private final SmsOutboundMessageRepository smsOutboundMessageRepository ;
	
	private final SMSProviderFactory smsProviderFactory ;
	
	@Autowired
	public SMSMessageService(final SmsOutboundMessageRepository smsOutboundMessageRepository,
			final SMSProviderFactory smsProviderFactory) {
		this.smsOutboundMessageRepository = smsOutboundMessageRepository ;
		this.smsProviderFactory = smsProviderFactory ;
	}
	
	public void sendShortMessage(final Collection<SMSMessage> messages) {
		Date date = new Date() ;
		for(SMSMessage message: messages) {
			message.setSubmittedOnDate(date);
		}
		this.smsOutboundMessageRepository.save(messages) ;
		this.sendMessage(messages);
	}
	
	private void sendMessage(final Collection<SMSMessage> message) {
		this.smsProviderFactory.sendShortMessage(message);
	}
}
