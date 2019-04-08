package model;

public class User {
    private String number;
    private String name;
    private String password;
    private String money;

    public String getNumber(){
        return number;
    }

    public void setNumber(String number){
        this.number = number;
    }

    public String  getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getMoney(){
        return money;
    }

    public void setMoney(String money){
        this.money = money;
    }

}
