package Job_Executor;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import Api_Call.InfoGetter;
import Info_Parser.ApiRequestConfig;
import Info_Parser.ParseAPIDetail;
public class ExecuteJob {
    public static void Execute(String jobWork, int retries) {
        System.out.println("Executing job work: " + jobWork);
        System.out.println("With retries: " + retries);
        try {
            runWithTimeout(() -> {
                try {
                    RunTask(jobWork);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }, 5); 
        } catch (Exception e) {
            System.err.println("Job execution failed: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    public static void runWithTimeout(Runnable task, long timeoutMinutes) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        
        try {
            Future<?> future = executor.submit(task);
            future.get(timeoutMinutes, TimeUnit.MINUTES);
        } catch (TimeoutException e) {
            throw new RuntimeException("Task timed out after " + timeoutMinutes + " minutes");
        } catch (ExecutionException e) {
            throw new RuntimeException("Task execution failed", e.getCause());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Task was interrupted", e);
        } finally {
            executor.shutdownNow(); // This will interrupt the task if still running
        }
    }
    @SuppressWarnings("CallToPrintStackTrace")
    public static void RunTask(String command) throws Exception {
        try {
            ApiRequestConfig config = ParseAPIDetail.loadApiRequestConfig(command);
            InfoGetter.executeApi(config);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}