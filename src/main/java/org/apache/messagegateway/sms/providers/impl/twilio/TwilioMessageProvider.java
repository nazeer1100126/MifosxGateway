package org.apache.messagegateway.sms.providers.impl.twilio;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.messagegateway.exception.MessageGatewayException;
import org.apache.messagegateway.sms.domain.SMSBridge;
import org.apache.messagegateway.sms.domain.SMSMessage;
import org.apache.messagegateway.sms.providers.SMSProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Message;

@Service(value="TwilioSMS")
public class TwilioMessageProvider implements SMSProvider {

    private static final Logger logger = LoggerFactory.getLogger(TwilioMessageProvider.class);

    TwilioMessageProvider() {
        super();
    }

    @Override
    public void sendMessage(final SMSBridge smsBridgeConfig, final SMSMessage message)
        throws MessageGatewayException {
    	//Based on message id, register call back. so that we get notification from Twilio about message status
        final List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
        messageParams.add(new BasicNameValuePair("From", smsBridgeConfig.getPhoneNo()));
        messageParams.add(new BasicNameValuePair("To", "+" + message.getMobileNumber()));
        messageParams.add(new BasicNameValuePair("Body", message.getMessage()));
        final TwilioRestClient twilioRestClient = this.get(smsBridgeConfig);
        final Account account = twilioRestClient.getAccount();
        final MessageFactory messageFactory = account.getMessageFactory();

        try {
            logger.info("Sending SMS to " + message.getMobileNumber() + " ...");
            Message m1 = messageFactory.create(messageParams);
        } catch (TwilioRestException trex) {
            logger.error("Could not send message, reason:", trex);
            throw new MessageGatewayException(trex.getMessage());
        }
    }
    
    TwilioRestClient get(final SMSBridge smsBridgeConfig) {
    	String providerAccountId = smsBridgeConfig.getConfigValue(TwilioMessageConstants.PROVIDER_ACCOUNT_ID) ;
    	String providerAuthToken = smsBridgeConfig.getConfigValue(TwilioMessageConstants.PROVIDER_AUTH_TOKEN) ;
        final TwilioRestClient client = new TwilioRestClient(providerAccountId, providerAuthToken);
        return client;
    }
}
