package API_Call;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AllJobsGetter {
    public static String All_Jobs_Getter() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://API1:8080/api/getAllJobs")).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) {
            throw new Exception("Failed to get all jobs from API. Status code: " + response.statusCode());
        }
        return response.body();
    }
}
