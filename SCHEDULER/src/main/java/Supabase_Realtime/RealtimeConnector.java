package Supabase_Realtime;

public class RealtimeConnector {
    public static void RealTime_Connection_Start() throws InterruptedException {
        // Replace with your Supabase URL and anon key
        String supabaseUrl = System.getenv("SUPABASE_URL");
        String apiKey = System.getenv("SUPABASE_API_KEY");
        
        SupabaseRealtimeClient client = new SupabaseRealtimeClient(supabaseUrl, apiKey);
        
        Thread.sleep(2000); // Wait for connection
        client.subscribe("job_details");

        
       try {
            while (true) {
                Thread.sleep(1000); // Check every second
            }
        } catch (InterruptedException e) {
            System.out.println("Application interrupted, shutting down...");
        }
        client.disconnect();
        System.out.println("Application exiting.");
    }
}