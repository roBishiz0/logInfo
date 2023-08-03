package logInfo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                SpringConfig.class
        );
		
		LogSender logSender = context.getBean("logSender", LogSender.class);
		// Gone - Url1
		// New - Url3
		// Changed - Url0
		System.out.print(logSender.receiveMessage());
		context.close();
	}
}
