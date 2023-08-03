package logInfo;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

public class LogSenderChangedUrlsTest {

	@Test
	public void testChangedUrlsOutput() {
		LogSender logSender = new LogSender();
		Hashtable<String, String> todaysHtmls = new Hashtable<String, String>();
		Hashtable<String, String> yesterdaysHtmls = new Hashtable<String, String>();

		todaysHtmls.put("page1", "content1");
		todaysHtmls.put("page2", "content2");
		todaysHtmls.put("page3", "content3");
		todaysHtmls.put("page4", "updatedContent");

		yesterdaysHtmls.put("page1", "content1");
		yesterdaysHtmls.put("page3", "content3");
		yesterdaysHtmls.put("page4", "oldContent");

		Enumeration<String> yesterdaysUrls = yesterdaysHtmls.keys();
		List<String> changedUrls = logSender.changedUrlsOutput(todaysHtmls, yesterdaysHtmls, yesterdaysUrls);

		assertEquals(1, changedUrls.size());
		assertTrue(changedUrls.contains("page4"));
	}
}
