package Api_Call;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Map;

import Info_Parser.ApiRequestConfig;
public class InfoGetter {
    @SuppressWarnings("CallToPrintStackTrace")
    public static String Job_Info_Getter(String jobName) {
        try{
            jobName = URLEncoder.encode(jobName, StandardCharsets.UTF_8);
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://API1:8080/api/getJobDetail?jobName="+jobName)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if(response.statusCode() != 200) {
                throw new Exception("Failed to get all jobs from API. Status code: " + response.statusCode());
            }
            return response.body();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static String executeApi(ApiRequestConfig config) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        StringBuilder urlBuilder = new StringBuilder(config.getUrl());
        if (config.getQueryParams() != null && !config.getQueryParams().isEmpty()) {
            urlBuilder.append("?");
            for (Map.Entry<String, String> entry : config.getQueryParams().entrySet()) {
                urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8))
                          .append("=")
                          .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                          .append("&");
            }
            // remove last "&"
            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(urlBuilder.toString()))
                .timeout(Duration.ofSeconds(20));

        // Add headers if present
        if (config.getHeaders() != null) {
            config.getHeaders().forEach(requestBuilder::header);
        }

        // Add method and body
        String method = config.getMethod().toUpperCase();
        if (config.getBody() != null && !"GET".equals(method)) {
            String bodyString = config.getBody().toString(); // assumes JSON string or simple string
            requestBuilder.method(method, HttpRequest.BodyPublishers.ofString(bodyString));
        } else {
            requestBuilder.method(method, HttpRequest.BodyPublishers.noBody());
        }

        HttpRequest request = requestBuilder.build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
