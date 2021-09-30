package job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DumbJob implements Job {

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("Tutorial Lesson 3 - Hello DumbJob");

    JobKey key = context.getJobDetail().getKey();

    JobDataMap dataMap = context.getJobDetail().getJobDataMap();

    String jobSays = dataMap.getString("jobSays");
    float myFloatValue = dataMap.getFloat("myFloatValue");

    System.out.println(
        "Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue);
  }
}
