package logInfo;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;

@Scope("singleton")
public class LogSender {
	@Autowired
	@Qualifier("yesterdaysHtmls")
	private Hashtable<String, String> yesterdaysHtmls;
	@Autowired
	@Qualifier("todaysHtmls")
	private Hashtable<String, String> todaysHtmls;

	public List<String> goneUrlsOutput(Hashtable<String, String> todaysHtmls, Hashtable<String, String> yesterdaysHtmls, Enumeration<String> yesterdaysUrls) {
		List<String> goneUrls = new ArrayList<String>();
		yesterdaysUrls = yesterdaysHtmls.keys();
		while (yesterdaysUrls.hasMoreElements()) {
			String yesterdaysKey = yesterdaysUrls.nextElement();
			if (!todaysHtmls.containsKey(yesterdaysKey))
				goneUrls.add(yesterdaysKey);
		}
		return goneUrls;
	}

	public List<String> newUrlsOutput(Hashtable<String, String> yesterdaysHtmls, Hashtable<String, String> todaysHtmls, Enumeration<String> todaysUrls) {
		List<String> newUrls = new ArrayList<String>();
		while (todaysUrls.hasMoreElements()) {
			String todaysKey = todaysUrls.nextElement();
			if (!yesterdaysHtmls.containsKey(todaysKey))
				newUrls.add(todaysKey);
		}
		return newUrls;
	}

	public List<String> changedUrlsOutput(Hashtable<String, String> todaysHtmls, Hashtable<String, String> yesterdaysHtmls, Enumeration<String> yesterdaysUrls) {
		List<String> changedUrls = new ArrayList<String>();
		while (yesterdaysUrls.hasMoreElements()) {
			String yesterdaysKey = yesterdaysUrls.nextElement();
			if (todaysHtmls.containsKey(yesterdaysKey)) {
				if (!yesterdaysHtmls.get(yesterdaysKey).equals(todaysHtmls.get(yesterdaysKey)))
					changedUrls.add(yesterdaysKey);
			}
		}
		return changedUrls;
	}
	
	private String getMessage(List<String> goneUrls, List<String> newUrls, List<String> changedUrls) {
		StringBuilder sb = new StringBuilder("Здравствуйте, дорогая и.о. секретаря\r\n"
				+ "\r\n"
				+ "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n\n");
		sb.append("Исчезли следующие страницы: {" + goneUrls.toString().replaceAll("([\\[\\]])", "") + "}\n");
		sb.append("Появились следующие новые страницы: {" + newUrls.toString().replaceAll("([\\[\\]])", "") + "}\n");
		sb.append("Изменились следующие страницы: {" + changedUrls.toString().replaceAll("([\\[\\]])", "") + "}\n");
		sb.append("\r\n"
				+ "С уважением,\r\n"
				+ "автоматизированная система\r\n"
				+ "мониторинга.\n");
		return sb.toString();
	}

	public String receiveMessage() {
		Enumeration<String> yesterdaysUrls = yesterdaysHtmls.keys();
		Enumeration<String> todaysUrls = todaysHtmls.keys();
		List<String> goneUrls = goneUrlsOutput(todaysHtmls, yesterdaysHtmls, yesterdaysUrls);
		List<String> newUrls = newUrlsOutput(yesterdaysHtmls, todaysHtmls, todaysUrls);
		List<String> changedUrls = changedUrlsOutput(todaysHtmls, yesterdaysHtmls, yesterdaysUrls);
		
		return getMessage(goneUrls, newUrls, changedUrls);
	}
}
