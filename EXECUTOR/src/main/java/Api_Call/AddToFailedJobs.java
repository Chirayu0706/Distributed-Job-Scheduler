package Api_Call;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AddToFailedJobs {
    public static String Add_To_Failed_Jobs(String failedJobJson) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://API1:8080/api/addFailedJob"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(failedJobJson))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) {
            throw new Exception("Failed to add to failed jobs in API. Status code: " + response.statusCode());
        }
        return response.body();
    }    
}
