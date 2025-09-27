import RabbitMQ_Handler.JobListener;

public class ExecutorStart {
    public static void main(String[] args) throws Exception {
        System.out.println("Executor Started");
        Thread.sleep(10000);
        JobListener.ListenToJobMQ();
    }
}
