package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoOperations;

@SpringBootApplication(scanBasePackages = { "com" })
public class Application {
	
	public static void main(String[] args) {
		/*ApplicationContext ctx = new AnnotationConfigApplicationContext(MongoDbConfig.class);
        MongoOperations mongoOperation = (MongoOperations) ctx.getBean("mongoTemplate");
        Application app=ctx.getBean(Application.class);*/
        
		/*ConfigurableApplicationContext context=SpringApplication.run(Application.class, args);
		Application app=context.getBean(Application.class);
		MongoOperations mongoOperations=(MongoOperations) context.getBean("mongoTemplate");*/
		//app.execute(mongoOperation);
		
		SpringApplication.run(Application.class, args);
		System.out.println("Hello world");
	}

	/*private void execute(MongoOperations mongoOperations) {
		User user=new User(1,"Nikhil",1000);
		//mongoDBOperation.save(mongoOperations, user);
	}*/

}
