package com.esaas.runner;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.eredlab.g4.bmf.util.SpringBeanLoader;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.security.SslSocketConnector;

import org.mortbay.jetty.webapp.WebAppContext;

public class JettyServer {
	
	private static Log log = LogFactory.getLog(JettyServer.class);
	
	private String FORCELOAD_Y="1";
	private String forceLoad="1";
	
		
	Server server = null;
	
	public JettyServer(){
		server = new Server();
	}
	
	
	
	public void addSelectChannelConnector(int pWebPort){
		Connector connector = new SelectChannelConnector();
		connector.setPort(pWebPort);
		server.addConnector(connector);
	}
	
	public void addSslSocketConnector(int pSslPort,String pKeyPassword,String pKeystoreFile){
		SslSocketConnector connector = new SslSocketConnector();;
		connector.setPort(pSslPort);
		connector.setKeyPassword(pKeyPassword);
		connector.setPassword(pKeyPassword);
		connector.setProtocol("SSL");
		connector.setConfidentialScheme("https");
		
		if (pKeystoreFile != "") {
			connector.setKeystore(pKeystoreFile);
		}
		server.addConnector( connector);
	}
	
	public void addWebAppContext(String pContextPath,String pResourceBase){
		WebAppContext context = new WebAppContext ();
		
		context.setContextPath(pContextPath);
		context.setResourceBase(pResourceBase);
        //context.setClassLoader(Thread.currentThread().getContextClassLoader());

		server.addHandler(context);
	}
	
	
	public void start(){
	    //PropertiesHelper pHelper = PropertiesFactory.getPropertiesHelper(PropertiesFile.G4);
	    //String forceLoad = pHelper.getValue("forceLoad", ArmConstants.FORCELOAD_N);
		/**
		 * 强制改变加载顺序
		 * 解决直接使用iBatis源码带来的初始化Spring容器报错的问题
		 */
	    if(forceLoad.equals(FORCELOAD_Y)){
		    System.out.println("********************************************");
		    System.out.println("系统正在初始化服务容器...");
		    //SpringBeanLoader.getApplicationContext();
		    //System.out.println("容器初始化成功啦，您的托管Bean已经被实例化。");
		}
					
		try {
			server.start();
            System.out.println("Start");
		} catch (Exception e) {
			log.error("Server启动出错.\n");
			e.printStackTrace();
		}

	}
	
	/**
	 * 停止Server
	 */
	public void stop() {
		try {
			server.stop();
		} catch (Exception e) {
			log.error("Server未能正常停止.\n");
			e.printStackTrace();
		}
	}
	
}
