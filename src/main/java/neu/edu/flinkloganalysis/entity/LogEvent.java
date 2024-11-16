package neu.edu.flinkloganalysis.entity;

public class LogEvent {
    private String ip;
    private String identity;
    private String username;
    private String timestamp;
    private String request;
    private int status;
    private int size;

    public LogEvent(String ip, String identity, String username, String timestamp, String request, int status, int size) {
        this.ip = ip;
        this.identity = identity;
        this.username = username;
        this.timestamp = timestamp;
        this.request = request;
        this.status = status;
        this.size = size;
    }

    // Getters and setters
    public String getIp() { return ip; }
    public String getIdentity() { return identity; }
    public String getUsername() { return username; }
    public String getTimestamp() { return timestamp; }
    public String getRequest() { return request; }
    public int getStatus() { return status; }
    public int getSize() { return size; }
}
