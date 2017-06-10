package uz.how.simplemvp.view.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.dagger.module.GlideApp;
import uz.how.simplemvp.databinding.ActivityProfileBinding;
import uz.how.simplemvp.model.domains.User;
import uz.how.simplemvp.presenter.impl.ProfilePresenterImpl;
import uz.how.simplemvp.view.ProfileView;
import uz.how.simplemvp.view.fragment.ReposFragment;

/**
 * Created by mirjalol on 6/9/17.
 */

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    @Inject
    ProfilePresenterImpl profilePresenter;

    private ActivityProfileBinding binding;

    public static void start(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile);
        binding.setLoading(false);
        binding.setUser(new User());
        binding.setPresenter(profilePresenter);

        profilePresenter.loadProfile();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_logout: {
                profilePresenter.logout();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
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
    public void setProfileData(User user) {
        binding.setUser(user);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.reposContainer, ReposFragment.newInstance())
                .commit();
    }

    @Override
    public void connectionError() {
        binding.tryAgain.setVisibility(View.VISIBLE);
    }

    @Override
    public void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
        finish();
    }

}
