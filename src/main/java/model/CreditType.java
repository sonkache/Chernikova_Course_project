package model;

public class CreditType {
    private int id;
    private String name;
    private String conditions;
    private double percent;
    private int returnPeriod;  // в месяцах

    public CreditType(int id, String name, String conditions, double percent, int returnPeriod) {
        this.id = id;
        this.name = name;
        this.conditions = conditions;
        this.percent = percent;
        this.returnPeriod = returnPeriod;
    }

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getConditions(){
        return conditions;
    }
    public void setConditions(String conditions){
        this.conditions = conditions;
    }

    public double getPercent(){
        return percent;
    }
    public void setPercent(double percent){
        this.percent = percent;
    }

    public int getReturnPeriod(){
        return returnPeriod;
    }
    public void setReturnPeriod(int returnPeriod){
        this.returnPeriod = returnPeriod;
    }
}
