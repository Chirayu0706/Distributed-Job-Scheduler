package Supabase_Realtime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Quartz_Handler.MaintainJobs;

public class IncomingParser {
    private static final ObjectMapper mapper = new ObjectMapper();
    
    public static void parseIncomingMessage(String message) {        
        try {
            SupabaseMessage msg = mapper.readValue(message, SupabaseMessage.class);
            
            if (!"postgres_changes".equals(msg.event)) return;
            
            String type = msg.payload.data.type;
            switch (type) {
                case "INSERT" -> {
                    String jobName = (String) msg.payload.data.record.get("job_name");
                    String jobCron = (String) msg.payload.data.record.get("job_cron");
                    MaintainJobs.Add_Job(jobName, jobCron);
                }
                case "UPDATE" -> {
                    String updatedJobName = (String) msg.payload.data.record.get("job_name");
                    String updatedJobCron = (String) msg.payload.data.record.get("job_cron");
                    MaintainJobs.Update_Job(updatedJobName, updatedJobCron);
                }
                case "DELETE" -> {
                    String deletedJobName = (String) msg.payload.data.oldRecord.get("job_name");
                    MaintainJobs.Remove_Job(deletedJobName);
                }
            }
        } catch (JsonProcessingException ignored) {}
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class SupabaseMessage {
        @JsonProperty("event") String event;
        @JsonProperty("payload") Payload payload;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Payload {
        @JsonProperty("data") PayloadData data;
    }
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class PayloadData {
        @JsonProperty("type") String type;
        @JsonProperty("record") java.util.Map<String, Object> record;
        @JsonProperty("old_record") java.util.Map<String, Object> oldRecord;
    }
}