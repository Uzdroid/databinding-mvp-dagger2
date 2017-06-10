package uz.how.simplemvp.model.domains;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by mirjalol on 6/10/17.
 */

public class Repo extends BaseObservable {

    private String name;
    private String fullName;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
