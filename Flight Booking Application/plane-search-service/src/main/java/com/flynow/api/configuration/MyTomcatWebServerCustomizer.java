package com.flynow.api.configuration;



import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

//This class is to handle the QueryChars issues while using the following characters ['{','}'] in the URI
@Configuration
public class MyTomcatWebServerCustomizer
        implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @SuppressWarnings("deprecation")
	@Override
    public void customize(TomcatServletWebServerFactory factory) {
    	factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "['{','}']"));
    }
}