package Back.models;

import front.observer.Observer;

public class User implements Observer {
    private String mail;
    private String name;


    public User(String mail, String name) {

        this.mail = mail;
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void update() {
    }
}
