package Quartz_Handler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class MaintainJobs {
    public static Scheduler scheduler;
    public MaintainJobs(){}

    public MaintainJobs(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void Add_Job(String job_name, String job_cron){
        System.out.println("Adding Job: " + job_name + " with cron: " + job_cron);
        JobDetail job = JobBuilder.newJob(JobHandler.class)
                                  .withIdentity(job_name, "group1")
                                  .build();
        Trigger trigger = TriggerBuilder.newTrigger()
                                  .withIdentity(job_name + "Trigger", "group1")
                                  .withSchedule(CronScheduleBuilder.cronSchedule(job_cron))
                                  .build();
        try {
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void Remove_Job(String job_name){
        System.out.println("Removing Job: " + job_name);
        try {
            scheduler.deleteJob(new JobKey(job_name,"group1"));
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public static void Update_Job(String job_name, String job_cron){
        System.out.println("Updating Job: " + job_name + " with cron: " + job_cron);
        try {
            Trigger newTrigger = TriggerBuilder.newTrigger()
                    .withIdentity(job_name + "Trigger", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(job_cron))
                    .build();
            scheduler.rescheduleJob(new TriggerKey(job_name + "Trigger", "group1"), newTrigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
