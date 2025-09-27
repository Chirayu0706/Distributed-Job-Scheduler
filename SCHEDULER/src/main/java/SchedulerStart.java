import java.util.HashMap;
import java.util.Map;

import org.quartz.Scheduler;
import org.quartz.impl.StdSchedulerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import API_Call.AllJobsGetter;
import Quartz_Handler.MaintainJobs;
import Supabase_Realtime.RealtimeConnector;

public class SchedulerStart {
    // First get all the jobs from DB using API
    // Schedule them using Quartz
    // Listen for changes in the DB using Supabase Realtime
    // If a new job is added, schedule it
    // If a job is deleted, unschedule it
    // If a job is updated, reschedule it


    public static void main(String[] args) throws Exception {
        System.out.println("Starting Scheduler...");
        Thread.sleep(10000);
        String all_jobs = AllJobsGetter.All_Jobs_Getter();
        System.out.println("All Jobs: " + all_jobs);
        Map<String,String>JobList =  parseJob(all_jobs);
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();
        MaintainJobs.scheduler = scheduler;
        for (Map.Entry<String, String> entry : JobList.entrySet()) {
            String jobName = entry.getKey();
            String jobCron = entry.getValue();
            MaintainJobs.Add_Job(jobName, jobCron);
        }
        RealtimeConnector.RealTime_Connection_Start();
    }
    public static Map<String, String> parseJob(String jobJson) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(jobJson);
        Map<String, String> result = new HashMap<>();
        for (JsonNode node : root) {
            String key = node.get("jobName").asText();
            String value = node.get("jobCron").asText();
            result.put(key, value);
        }
        return result;

    }
}
