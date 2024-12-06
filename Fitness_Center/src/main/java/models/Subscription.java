package models;


public class Subscription {
    private int id;
    private int clientId;
    private String type;
    private String startDate;
    private String endDate;


    public Subscription(int id, int clientId, String type, String startDate, String endDate) {
        this.id = id;
        this.clientId = clientId;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getClientId() { return clientId; }
    public void setClientId(int clientId) { this.clientId = clientId; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }
}

