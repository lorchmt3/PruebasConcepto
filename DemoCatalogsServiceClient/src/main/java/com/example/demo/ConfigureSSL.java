/**
 * 
 */
package com.example.demo;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.security.KeyStore;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;

/**
 * @author diana.martinez
 *
 */
public class ConfigureSSL {
	
	/**
	 * 
	 * @param c
	 * @param keyStorePath
	 * @param keyStorePassword
	 */
	public  void configureSSLOnTheClient(Object c, String keyStorePath, String keyStorePassword) {
		Client client = ClientProxy.getClient(c);
		HTTPConduit httpConduit = (HTTPConduit) client.getConduit();
		File truststore = new File(keyStorePath); //getClass
		
		try {
			TLSClientParameters tlsParams = new TLSClientParameters();
			tlsParams.setDisableCNCheck(true);
			KeyStore keyStore = KeyStore.getInstance("PKCS12"); // PKCS12 o JKS
			keyStore.load(new FileInputStream(truststore), keyStorePassword.toCharArray());
			TrustManagerFactory trustFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			trustFactory.init(keyStore);
			TrustManager[] tm = trustFactory.getTrustManagers();
			tlsParams.setTrustManagers(tm);
			keyStore.load(new FileInputStream(truststore), keyStorePassword.toCharArray());
			KeyManagerFactory keyFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			keyFactory.init(keyStore, keyStorePassword.toCharArray());
			KeyManager[] km = keyFactory.getKeyManagers();
			tlsParams.setKeyManagers(km);
			httpConduit.setTlsClientParameters(tlsParams);
			System.out.println("todo exitoso");
		} catch (Exception e) {
			System.err.println("configureSSLOnTheClient "+e.getMessage());
		}
	}

}
