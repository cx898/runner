package com.esaas.runner;


/**
 * 系统启动
 * @author 
 * @since 2010-03-23
 */
public class Run {
	public static void main(String[] args) {
		
		//String projectDir="e:\\Projects\\001_2011_projects\\003_架构\\SaaS";
        String projectDir="E:\\2013\\projects\\005_jeecms";
		
		JettyServer server = new JettyServer();
		
		server.addSelectChannelConnector(8080);
		//server.addSslSocketConnector(8443,"password999" ,projectDir+"\\ssl\\keystore1");
		
		//server.addWebAppContext("/g4", projectDir+"\\g4\\WebRoot\\");
		//server.addWebAppContext("/cas", projectDir+"\\cas\\webroot\\");
        server.addWebAppContext("/cms", projectDir+"\\cms\\webroot\\");

		
		server.stop();
		try {
			server.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
