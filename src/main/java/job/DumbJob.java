package job;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class DumbJob implements Job {

  // DumbJob에만 해당하는 파라미터 이름 정의
  public static final String JOB_SAYS = "jobSays";
  public static final String EXECUTION_COUNT = "executionCount";

  // 작업 인스턴스(DumbJob 객체)는 execute 메소드 실행 후 계속 재생성되기 때문에
  // non-static 멤버 변수들은 상태를 유지하거나 유지하기 위해 사용할 수 없음
  float myFloatValue;


  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    System.out.println("Tutorial Lesson 3 - Hello DumbJob");

    JobKey key = context.getJobDetail().getKey();

    // JobDetail에서 전달된 데이터맵(JobDataMap)을 가져옴
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();

    // 데이터맵에서 전달된 데이터 추출
    int count = dataMap.getInt(EXECUTION_COUNT);
    String jobSays = dataMap.getString(JOB_SAYS);

    // 전달된 데이터 변경
    String newJobSays = jobSays + " ~ ";
    count++;

    // 변경된 데이터를 데이터맵에 적용
    dataMap.put(JOB_SAYS, newJobSays);
    dataMap.put(EXECUTION_COUNT, count);

    System.out.println("============================");
    System.out.println("Instance: " + key);
    System.out.println("jobSays: " + newJobSays);
    System.out.println("count: " + count);
  }
}
