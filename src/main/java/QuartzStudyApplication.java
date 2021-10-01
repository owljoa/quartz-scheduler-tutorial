import job.DumbJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzStudyApplication {

  public static void main(String[] args) {
    SchedulerFactory schedulerFactory = new StdSchedulerFactory();

    try {
      // Scheduler 인스턴스 생성
      Scheduler scheduler = schedulerFactory.getScheduler();

      // 작업 정의
      JobDetail dumbJob = JobBuilder.newJob(DumbJob.class)
          .withIdentity("dumbJob", "dumbGroup1")
          .build();

      // 작업이 바로 시작되고, 3초 간격으로 4회 반복하도록 트리거 정의
      SimpleTrigger dumbTrigger = TriggerBuilder.newTrigger()
          .withIdentity("dumbTrigger", "dumbGroup1")
          .startNow()
          .withSchedule(SimpleScheduleBuilder.simpleSchedule()
              .withIntervalInSeconds(3)
              .withRepeatCount(4))
          .build();

      // dumbJob에 초기화 파라미터 전달
      dumbJob.getJobDataMap().put(DumbJob.JOB_SAYS, "HELLO");
      dumbJob.getJobDataMap().put(DumbJob.EXECUTION_COUNT, 1);

      // dumbTrigger에 초기화 파라미터 전달
      dumbTrigger.getJobDataMap().put(DumbJob.JOB_SAYS, "HELLO DUMB TRIGGER");

      // job과 trigger를 이용해서 스케줄링
      scheduler.scheduleJob(dumbJob, dumbTrigger);

      // 스케줄러 시작
      scheduler.start();

      // 스케줄러 종료 이전에 job이 여러 번 트리거되고 실행되도록 메인 쓰레드 동작 일시 정지
      Thread.sleep(12000);

      // 스케줄러 종료
      scheduler.shutdown();
    } catch (SchedulerException | InterruptedException se) {
      se.printStackTrace();
    }
  }
}
