package models;


public class Trainer {
    private int id;
    private String name;
    private String qualification;
    private String phone;


    public Trainer(int id, String name, String qualification, String phone) {
        this.id = id;
        this.name = name;
        this.qualification = qualification;
        this.phone = phone;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getQualification() { return qualification; }
    public void setQualification(String qualification) { this.qualification = qualification; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
