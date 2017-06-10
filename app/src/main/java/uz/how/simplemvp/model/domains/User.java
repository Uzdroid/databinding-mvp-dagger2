package uz.how.simplemvp.model.domains;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import uz.how.simplemvp.BR;

/**
 * Created by mirjalol on 6/10/17.
 */

public class User extends BaseObservable {

    private Long id;
    private String avatarUrl;
    private String name;
    private String login;
    private String password;

    public User() {
        this.avatarUrl = "";
        this.name = "";
        this.login = "";
        this.password = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Bindable
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        notifyPropertyChanged(BR.avatarUrl);
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.name);
    }

    @Bindable
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        notifyPropertyChanged(BR.login);
    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
