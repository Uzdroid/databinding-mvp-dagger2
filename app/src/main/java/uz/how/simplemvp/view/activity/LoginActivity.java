package uz.how.simplemvp.view.activity;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.databinding.ActivityLoginBinding;
import uz.how.simplemvp.model.Constants;
import uz.how.simplemvp.model.domains.User;
import uz.how.simplemvp.presenter.impl.LoginPresenterImpl;
import uz.how.simplemvp.view.LoginView;

/**
 * Created by mirjalol on 6/9/17.
 */

public class LoginActivity extends AppCompatActivity implements LoginView {

    @Inject
    LoginPresenterImpl loginPresenter;

    @Inject
    SharedPreferences prefs;

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        if (prefs.contains(Constants.LOGIN)) {
            ProfileActivity.start(this);
            return;
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setLoading(false);
        binding.setUser(new User());
        binding.setPresenter(loginPresenter);
    }

    @Override
    public void validationError() {
        if (binding.getUser().getLogin().isEmpty()) {
            binding.username.setError("Fill username");
        }
        if (binding.getUser().getPassword().isEmpty()) {
            binding.password.setError("Fill username");
        }
    }

    @Override
    public void authError() {
        Toast.makeText(this, R.string.auth_error_message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void authSuccess() {
        ProfileActivity.start(this);
    }

    @Override
    public void showProgress() {
        binding.setLoading(true);
    }

    @Override
    public void hideProgress() {
        binding.setLoading(false);
    }

    @Override
    public void connectionError() {
        Toast.makeText(this, R.string.connection_error, Toast.LENGTH_SHORT).show();
    }

}
