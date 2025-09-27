package Supabase_Realtime;
import java.net.URI;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SupabaseRealtimeClient {
    private WebSocketClient client;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private boolean connected = false;
    private int refCounter = 1;

    
    public SupabaseRealtimeClient(String supabaseUrl, String apiKey) {
        try {
            String wsUrl = supabaseUrl.replace("https://", "wss://") + "/realtime/v1/websocket?apikey=" + apiKey + "&vsn=1.0.0";
            
            client = new WebSocketClient(new URI(wsUrl)) {
                @Override
                public void onOpen(ServerHandshake handshake) {
                    System.out.println("Connected");
                    connected = true;
                }

                @Override
                public void onMessage(String message) {
                    IncomingParser.parseIncomingMessage(message);
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    System.out.println("Closed: " + reason);
                    connected = false;
                    startHeartbeat();

                }

                @Override
                public void onError(Exception ex) {
                    ex.printStackTrace();
                }
            };
            
            client.connect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startHeartbeat() {
        Thread heartbeatThread = new Thread(() -> {
            while (connected) {
                try {
                    sendHeartbeat();
                    Thread.sleep(25000); // 25 seconds
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        heartbeatThread.setDaemon(true);
        heartbeatThread.start();
    }

    private void sendHeartbeat() {
        if (client != null && client.isOpen()) {
            String heartbeat = """
                {
                    "topic": "phoenix",
                    "event": "heartbeat",
                    "payload": {},
                    "ref": %d
                }
                """.formatted(++refCounter);
            
            client.send(heartbeat);
            System.out.println("Heartbeat sent");
        }
    }

    public void subscribe(String tableName) {
        String message = """
            {
                "topic": "realtime:*",
                "event": "phx_join",
                "payload": {
                    "config": {
                        "postgres_changes": [{
                            "event": "*",
                            "schema": "public",
                            "table": "%s"
                        }]
                    }
                },
                "ref": 1
            }
            """.formatted(tableName);
        
        client.send(message);
    }
    public void disconnect() {
        if (client != null) {
            client.close();
        }
    }
}