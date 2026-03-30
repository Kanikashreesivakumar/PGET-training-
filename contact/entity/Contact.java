package com.examly.entity;

public class Contact{
    private int contactid;
    private String contactname;
    private String contactnumber;
    private String status;
    private String lastseen;
    private Boolean blocked;


    public Contact(){}

    public Contact( int contactId,String contactName, String contactNumber, String lastSeen, String status,String lastSeen,boolean blocked)
    {
        this.contactId=contactId;
        this.contactName=contactName;
        this.contactNumber= contactNumber;
        this.status = status;
        this.lastSeen = lastSeen ;
        this.blocked = blocked;
    }

    public int getContactId(){return contactId;}
    public void setContactId(int contactId){
        this.contactId=contactId;
    }


    public String getContactName(){return contactName;}
    public void setContactName(String contactName){this.contactName=contactName;}

    public String getContactNumber(){return contactNumber;}
    public void setContactNumber (String contactName){this.contactName=contactName;}

    public String getStatus (){return status;}
    public void setStatus(String status){ this.status = status;}

    public String getLastSeen(){return lastSeen;}
    public void setLastSeen(String lastSeen ){this.lastSeen = lastSeen;}

    public boolean isBlocked(){return blocked;}
    public void SetBlocked(boolean blocked){this.blocked= blocked;}
}