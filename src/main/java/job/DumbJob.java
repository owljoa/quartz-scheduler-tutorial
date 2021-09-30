package job;

import java.util.ArrayList;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

public class DumbJob implements Job {

  String jobSays;
  float myFloatValue;
  ArrayList<Date> myStateData;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("Tutorial Lesson 3 - Hello DumbJob");

    JobKey key = context.getJobDetail().getKey();

    // JobDetail과 Trigger의 JobDataMap을 병합한 형태의 객체를 가져옴
    JobDataMap dataMap = context.getMergedJobDataMap();

    myStateData.add(new Date());

    System.out.println(
        "Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue
            + ", state: " + myStateData);
  }

  public void setJobSays(String jobSays) {
    this.jobSays = jobSays;
  }

  public void setMyFloatValue(float myFloatValue) {
    this.myFloatValue = myFloatValue;
  }

  public void setMyStateData(ArrayList<Date> myStateData) {
    this.myStateData = myStateData;
  }
}
