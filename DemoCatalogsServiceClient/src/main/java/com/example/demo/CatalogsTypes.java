/**
 * 
 */
package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.grupoaval.payments.v1.FinancialEntitiesInq;
import com.grupoaval.payments.v1.GetFinancialEntitiesRequest;
import com.grupoaval.payments.v1.GetFinancialEntitiesResponse;
import com.grupoaval.payments.v1.GetFinancialEntitiesRqType;
import com.grupoaval.xsd.ifx.CatalogueType;
import com.grupoaval.xsd.ifx.RefInfoType;

/**
 * @author diana.martinez
 *
 */
@Component
public class CatalogsTypes {
	
	@Autowired
	private FinancialEntitiesInq catalogsTypesProxy;
	
	public List<CatalogueType> getCatalogs() {
		
		GetFinancialEntitiesRequest getFinancialEntitiesRq = new GetFinancialEntitiesRequest();
		GetFinancialEntitiesRqType request = new GetFinancialEntitiesRqType();
		RefInfoType refInfoType = new RefInfoType();
		refInfoType.setRefId("prueba1");
		refInfoType.setRefType("prueba");
		request.setRefInfo(refInfoType);
		getFinancialEntitiesRq.setGetFinancialEntitiesRq(request);
		GetFinancialEntitiesResponse response = catalogsTypesProxy.getFinancialEntities(getFinancialEntitiesRq);
		
		
		List<CatalogueType> catalogList=response.getGetFinancialEntitiesRs().getCatalogue();
		for (CatalogueType catalog: catalogList) {		
			System.out.println("Catalog key");
			System.out.println(catalog.getKey());
		}
		
		System.out.println("ApprovalId - "+response.getGetFinancialEntitiesRs().getApprovalId());
		
		return catalogList;
		
		
	}
	
	
	
}
