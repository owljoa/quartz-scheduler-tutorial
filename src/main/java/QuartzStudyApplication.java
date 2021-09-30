import job.DumbJob;
import job.HelloJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzStudyApplication {

  public static void main(String[] args) {
    try {
      // Scheduler 인스턴스 생성
      System.out.println("스케줄러 생성 시작");
      Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
      System.out.println("스케줄러 생성 완료");

      // 스케줄러 시작
      System.out.println("스케줄러 시작");
      scheduler.start();
      System.out.println("스케줄러 시작 완료");

      // 작업(Job)을 정의하고 HelloJob class에 바인딩
      JobDetail job = JobBuilder.newJob(HelloJob.class)
          .withIdentity("job1", "group1")
          .build();

      // 작업 정의 시점에 JobDataMap에 데이터 입력
      JobDetail dumbJob = JobBuilder.newJob(DumbJob.class)
          .withIdentity("dumbJob", "group1")
          .usingJobData("jobSays", "Hello World!") // jobSays를 key로 Hello World!를 값으로 데이터 입력
          .usingJobData("myFloatValue", 3.141f)
          .build();

      // 작업이 바로 시작되고, 매 20초마다 반복되도록 트리거 정의
      Trigger trigger = TriggerBuilder.newTrigger()
          .withIdentity("trigger1", "group1")
          .startNow()
          .withSchedule(SimpleScheduleBuilder.simpleSchedule()
              .withIntervalInSeconds(20)
              .repeatForever())
          .build();

      // job과 trigger를 이용해서 스케줄링
      scheduler.scheduleJob(dumbJob, trigger);

      // 스케줄러 종료 이전에 job이 여러 번 트리거되고 실행되도록 메인 쓰레드 동작 60초간 정지
      Thread.sleep(60000);

      // 스케줄러 종료
      System.out.println("스케줄러 종료");
      scheduler.shutdown();
      System.out.println("스케줄러 종료 완료");
    } catch (SchedulerException | InterruptedException se) {
      se.printStackTrace();
    }
  }
}
