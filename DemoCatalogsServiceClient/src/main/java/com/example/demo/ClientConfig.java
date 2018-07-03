/**
 * 
 */
package com.example.demo;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.grupoaval.payments.v1.FinancialEntitiesInq;


/**
 * @author diana.martinez
 *
 */
@Configuration
public class ClientConfig {
	@Value("${client.ticketagent.address}")
	  private String address;

	  @Bean(name = "catalogsProxy")
	  public FinancialEntitiesInq helloWorldProxy() {
	    JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
	    jaxWsProxyFactoryBean.setServiceClass(FinancialEntitiesInq.class);
	    jaxWsProxyFactoryBean.setAddress(address);

	    return (FinancialEntitiesInq) jaxWsProxyFactoryBean.create();
	  }

}
