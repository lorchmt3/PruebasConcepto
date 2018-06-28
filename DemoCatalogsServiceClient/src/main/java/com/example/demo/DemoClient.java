/**
 * 
 */
package com.example.demo;

import java.util.List;


import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

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
		
//		String serviceUrl = "http://localhost:8084/catalog/financialEntities?WSDL";// 192.168.135.28 ---- 10.5.14.39
		String serviceUrl = "https://192.168.135.28:442/PJBA_Multiaplicacion-consultarDatosCatalogos/FinancialEntitiesInqPort?wsdl";// 192.168.135.28 ---- 10.5.14.39
		// String serviceUrl =
		// "https://1/PJBA_Multiaplicacion-consultarDatosCatalogos/FinancialEntitiesInqPort?WSDL";//
		
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		factory.setServiceClass(FinancialEntitiesInq.class);
		factory.setAddress(serviceUrl);
		FinancialEntitiesInq catalogsTypesProxy = (FinancialEntitiesInq) factory.create();

		GetFinancialEntitiesRequest getFinancialEntitiesRq = new GetFinancialEntitiesRequest();
		GetFinancialEntitiesRqType request = new GetFinancialEntitiesRqType();
		List<CatalogueType> catalogueList = request.getCatalogue();

		CatalogueType catalogue = new CatalogueType();
		AdditionalDataType additionalData = new AdditionalDataType();
		additionalData.setValue("GE");
		catalogue.setAdditionalData(additionalData);
		catalogue.setKey("100");
		catalogueList.add(catalogue);

		// RefInfoType refInfoType = new RefInfoType();
		// refInfoType.setRefId("prprueba");
		// refInfoType.setRefType("prueba");
		// request.setRefInfo(refInfoType);
		
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
