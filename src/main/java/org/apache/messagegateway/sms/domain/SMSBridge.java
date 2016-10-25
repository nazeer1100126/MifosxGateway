package org.apache.messagegateway.sms.domain;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="m_sms_bridge")
public class SMSBridge extends AbstractPersistableCustom<Long> {

	@Column(name = "tenant_id", nullable = false)
	private String tenantId;

	@Column(name = "phone_no", nullable = false)
	private String phoneNo;

	@Column(name = "provider_key", nullable = false)
	private String providerAppKey;

	@Column(name = "provider_name", nullable = false)
	private String providerName;

	@Column(name = "description", nullable = false)
	private String providerDescription;
	
	@com.fasterxml.jackson.annotation.JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tenantProvider", orphanRemoval = true, fetch = FetchType.EAGER)
	public Collection<SMSBridgeConfig> bridgeConfigurations;

	public SMSBridge() {
		
	}
	
	public SMSBridge(final String tenantId) {
		this.tenantId = tenantId ;
	}
	
	public SMSBridge(final Long id, final String tenantId, final String phoneNo, final String providerAppKey, final String providerName, final String providerDescription) {
		this.id = id ;
		this.tenantId = tenantId ;
		this.phoneNo = phoneNo ;
		this.providerAppKey = providerAppKey ;
		this.providerName = providerName ;
		this.providerDescription = providerDescription ;
	}
	
	public SMSBridge(final String tenantId, final String phoneNo, final String providerAppKey, final String providerName, final String providerDescription) {
		this.tenantId = tenantId ;
		this.phoneNo = phoneNo ;
		this.providerAppKey = providerAppKey ;
		this.providerName = providerName ;
		this.providerDescription = providerDescription ;
	}
	
	public String getTenantId() {
		return tenantId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public String getProviderAppKey() {
		return providerAppKey;
	}

	public String getProviderName() {
		return providerName;
	}
	
	public String getProviderDescription() {
		return this.providerDescription ;
	}
	
	public Collection<SMSBridgeConfig> getBridgeConfigurations() {
		return this.bridgeConfigurations ;
	}
	
	public String getConfigValue(final String configParamName) {
		if(this.bridgeConfigurations == null || this.bridgeConfigurations.size() == 0) return null ;
		String configValue = null ;
		for(SMSBridgeConfig config: this.bridgeConfigurations) {
			if(config.getConfigName().equals(configParamName)) {
				configValue = config.getConfigValue() ;
			}
		}
		return configValue ;
	}
}
