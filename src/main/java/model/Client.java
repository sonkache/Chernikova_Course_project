package model;

 class Client {
    private int id;
    private String name;
    private String propertyType;
    private String address;
    private String phoneNumber;
    private String contactPerson;

    public Client(int id, String name, String propertyType, String address, String phoneNumber, String contactPerson) {
        this.id = id;
        this.name = name;
        this.propertyType = propertyType;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.contactPerson = contactPerson;
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

    public String getPropertyType(){
        return propertyType;
    }
    public void setPropertyType(String propertyType){
        this.propertyType = propertyType;
    }

    public String getAddress(){
        return address;
    }
    public void setAddress(String address){
        this.address = address;
    }

    public String getPhoneNumber(){
        return phoneNumber;

    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getContactPerson(){
        return contactPerson;
    }
    public void setContactPerson(String contactPerson){
        this.contactPerson = contactPerson;
    }
}
