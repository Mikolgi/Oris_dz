package models;


public class Training {
    private int id;
    private String name;
    private String description;
    private String schedule;
    private int trainerId;


    public Training(int id, String name, String description, String schedule, int trainerId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.schedule = schedule;
        this.trainerId = trainerId;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSchedule() { return schedule; }
    public void setSchedule(String schedule) { this.schedule = schedule; }

    public int getTrainerId() { return trainerId; }
    public void setTrainerId(int trainerId) { this.trainerId = trainerId; }
}
