package co.edu.iucesmag.quierolau.Login.model;

public class Login {
    private boolean searchUser;
    private Integer id;
    private String name;

    public Login(boolean searchUser, Integer id, String name) {
        this.searchUser = searchUser;
        this.id = id;
        this.name = name;
    }

    public boolean isSearchUser() {
        return searchUser;
    }

    public void setSearchUser(boolean searchUser) {
        this.searchUser = searchUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
