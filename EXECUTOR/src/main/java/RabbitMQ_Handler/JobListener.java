package RabbitMQ_Handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import Api_Call.AddToFailedJobs;
import Api_Call.InfoGetter;
import Job_Executor.ExecuteJob;
public class JobListener {
    private final static String QUEUE_NAME = "JOBS_QUEUE";
    private final static ConnectionFactory factory = new ConnectionFactory();

    @SuppressWarnings("CallToPrintStackTrace")
    public static void ListenToJobMQ() throws Exception {
        factory.setHost("RABBITMQ1");  // RabbitMQ is running locally
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            int retries = Character.getNumericValue(message.charAt(0));
            String jobName = message.substring(1);
            String INFO = InfoGetter.Job_Info_Getter(jobName);
            String execute = "";
            try {
                execute =  Detail_Extractor(INFO);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            try{
            ExecuteJob.Execute(execute, retries);
            }
            catch(Exception e){
                System.out.println("Job execution failed, requeuing if retries left");
                if(retries<4){
                    String requeueMessage = (retries+1) + jobName;
                    channel.basicPublish("", QUEUE_NAME, null, requeueMessage.getBytes("UTF-8"));
                    System.out.println("Requeued job with retries left: " + (4-retries));
                } else {
                    System.out.println("No retries left, discarding job: " + jobName + "Adding to failed jobs");
                    String failedJobJson = "{\"jobName\":\""+jobName+"\",\"executor\":\""+ System.getenv("INSTANCE_NAME") +"\"}";
                    try {
                        String response = AddToFailedJobs.Add_To_Failed_Jobs(failedJobJson);
                        System.out.println("Added to failed jobs: " + response);
                    } catch (Exception apiEx) {
                        System.out.println("Failed to add to failed jobs via API");
                        apiEx.printStackTrace();
                    }
                }
            }
        };

        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        Thread.sleep(Long.MAX_VALUE); 

    }
    public static String Detail_Extractor(String jsonString) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonString);
        return rootNode.get("jobWork").asText();
    }
}
