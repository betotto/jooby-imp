package mx.javamexico;

import io.jooby.Jooby;
import io.jooby.OpenAPIModule;
import io.jooby.Route.Before;
import io.jooby.Router;
import io.jooby.ServerOptions;
import io.jooby.handlebars.HandlebarsModule;
import mx.javamexico.post.BlogRoutes;
import mx.javamexico.usuario.UsuarioController;
import mx.javamexico.utils.DataSource;

import static io.jooby.StatusCode.NOT_FOUND;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.spi.MutableTrigger;

import com.typesafe.config.Config;

import io.jooby.Environment;

public class Main {

	
	public static void main(String... args) {
		
		 try {
             // Grab the Scheduler instance from the Factory
             Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

             // and start it off
             scheduler.start();
             
             // define the job and tie it to our HelloJob class
             JobDetail job = JobBuilder.newJob(Cosa.class)
                 .withIdentity("job1")
                 .build();

			// Trigger the job to run now, and then repeat every 40 seconds
             Trigger trigger = TriggerBuilder.newTrigger()
                 .withIdentity("trigger1")
                 .startNow()
                 .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())            
                 .build();

             // Tell quartz to schedule the job using our trigger
             scheduler.scheduleJob(job, trigger);
             
             Thread.sleep(10000);

             scheduler.shutdown();

         } catch (SchedulerException se) {
             se.printStackTrace();
         } catch (InterruptedException e) {
			e.printStackTrace();
         }
	}
		
		/*
		ServerOptions options = new ServerOptions();
		
		Jooby.runApp(args, app -> {
			
			Environment env = app.getEnvironment();                
			Config conf = env.getConfig();            
				  
			DataSource.init(conf.getString("JAVA_ENV"), conf.getString("DB_USER"), conf.getString("DB_PASSWORD"));
							
			app.setServerOptions(options);
			
			app.install(new HandlebarsModule());

			app.install(UsuarioController::new);

			app.install(BlogRoutes::new);

			app.error(NOT_FOUND, (ctx, cause, statusCode) -> {
				Router router = ctx.getRouter();
				router.getLog().error(ctx.getRequestPath() + " Not Found", statusCode.value());
				ctx.setResponseCode(statusCode);
				ctx.send("Not Found");
			});
		});*/
}