package Quartz_Handler;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import Rabbit_MQ_Handler.AddToMQ;
//Completely remove this file to add job to MQ in a diffrent package
public class JobHandler implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String jobName = context.getJobDetail().getKey().getName();
        AddToMQ.AddJobToMQ(jobName);
    }
}