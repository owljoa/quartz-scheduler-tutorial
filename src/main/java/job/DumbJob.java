package job;

import java.util.ArrayList;
import java.util.Date;
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

    // JobDetail과 Trigger의 JobDataMap을 병합한 형태의 객체를 가져옴
    JobDataMap dataMap = context.getMergedJobDataMap();

    String jobSays = dataMap.getString("jobSays");
    float myFloatValue = dataMap.getFloat("myFloatValue");

    // Trigger에 입력했던 myStateData 데이터를 불러와서 본래 타입에 맞게 캐스팅
    ArrayList<Date> state = (ArrayList<Date>) dataMap.get("myStateData");
    state.add(new Date());

    System.out.println(
        "Instance " + key + " of DumbJob says: " + jobSays + ", and val is: " + myFloatValue
            + ", state: " + state);
  }
}
