/**
 * 
 */
package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;

import com.grupoaval.payments.v1.FinancialEntitiesInq;
import com.grupoaval.payments.v1.GetFinancialEntitiesRequest;
import com.grupoaval.payments.v1.GetFinancialEntitiesResponse;
import com.grupoaval.payments.v1.GetFinancialEntitiesRqType;
import com.grupoaval.xsd.ifx.AdditionalDataType;
import com.grupoaval.xsd.ifx.CatalogueType;


/**
 * @author diana.martinez
 *
 */

public class DemoClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		String serviceUrl = "https://localhost:443/catalog/financialEntities?WSDL";// 192.168.135.28 ---- 10.5.14.39
		String serviceUrl = "https://192.168.135.28:442/PJBA_Multiaplicacion-consultarDatosCatalogos/FinancialEntitiesInqPort?wsdl";// 192.168.135.28 ---- 10.5.14.39
		String keyStorePath = "./src/main/resources/dp-sign-avaldigitallabs-villas.p12";
		String keyStorePassword = "4v4l!2018";
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(FinancialEntitiesInq.class);
		factory.setAddress(serviceUrl);
		FinancialEntitiesInq catalogsTypesProxy = (FinancialEntitiesInq) factory.create();
		
		//certificado SSL
		
		ConfigureSSL confssl= new ConfigureSSL();
		confssl.configureSSLOnTheClient(catalogsTypesProxy, keyStorePath, keyStorePassword);

		GetFinancialEntitiesRequest getFinancialEntitiesRq = new GetFinancialEntitiesRequest();
		GetFinancialEntitiesRqType request = new GetFinancialEntitiesRqType();
		List<CatalogueType> catalogueList = request.getCatalogue();

		CatalogueType catalogue = new CatalogueType();
		AdditionalDataType additionalData = new AdditionalDataType();
		additionalData.setValue("GE");
		catalogue.setAdditionalData(additionalData);
		catalogue.setKey("100");
		catalogueList.add(catalogue);
		
		getFinancialEntitiesRq.setGetFinancialEntitiesRq(request);
		System.out.println("mi catalogo size:"+request.getCatalogue().size());
		GetFinancialEntitiesResponse response = catalogsTypesProxy.getFinancialEntities(getFinancialEntitiesRq);

		System.out.println(response.toString());
		List<CatalogueType> catalogList = response.getGetFinancialEntitiesRs().getCatalogue();
		for (CatalogueType catalog : catalogList) {
			System.out.println("Catalog key - " + catalog.getKey() + " - AddionalData - "+catalog.getAdditionalData().getValue());
			// }
//			System.out.println("ApprovalId - " + response.getGetFinancialEntitiesRs().getApprovalId());

		}

	}
	
	
}
