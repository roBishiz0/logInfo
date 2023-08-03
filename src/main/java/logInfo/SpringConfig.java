package logInfo;

import org.springframework.context.annotation.Configuration;
import java.util.Hashtable;

import org.springframework.context.annotation.Bean;

@Configuration
public class SpringConfig {
	@Bean
	public LogSender logSender() { return new LogSender(); }
	
	@Bean
	public Hashtable<String, String> yesterdaysHtmls() { 
		Hashtable<String, String> yesterdaysHtmls = new Hashtable<String, String>();
		yesterdaysHtmls.put("URL1", "HTML1");
		yesterdaysHtmls.put("URL2", "HTML2");
		yesterdaysHtmls.put("URL0", "HTML3");
		return yesterdaysHtmls; 
	}
	
	@Bean
	public Hashtable<String, String> todaysHtmls() { 
		Hashtable<String, String> todaysHtmls = new Hashtable<String, String>();
		todaysHtmls.put("URL2", "HTML2");
		todaysHtmls.put("URL3", "HTML3");
		todaysHtmls.put("URL0", "HTML4");
		return todaysHtmls; 
	}
}
