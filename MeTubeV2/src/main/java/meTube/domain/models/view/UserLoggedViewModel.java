package meTube.domain.models.view;

public class UserLoggedViewModel {
    String name;

    public UserLoggedViewModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

