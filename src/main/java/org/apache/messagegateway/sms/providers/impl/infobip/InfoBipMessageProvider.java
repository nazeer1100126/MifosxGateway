package org.apache.messagegateway.sms.providers.impl.infobip;

import org.apache.messagegateway.exception.MessageGatewayException;
import org.apache.messagegateway.sms.domain.SMSBridge;
import org.apache.messagegateway.sms.domain.SMSMessage;
import org.apache.messagegateway.sms.providers.SMSProvider;
import org.springframework.stereotype.Service;

@Service(value="InfoBipSMS")
public class InfoBipMessageProvider implements SMSProvider{

	@Override
	public void sendMessage(final SMSBridge smsBridgeConfig, final SMSMessage message) throws MessageGatewayException {
		
	}
}
