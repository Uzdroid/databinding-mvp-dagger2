package uz.how.simplemvp.view.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import uz.how.simplemvp.R;
import uz.how.simplemvp.databinding.FragmentReposBinding;
import uz.how.simplemvp.databinding.ItemRepoBinding;
import uz.how.simplemvp.model.domains.Repo;
import uz.how.simplemvp.presenter.impl.ReposPresenterImpl;
import uz.how.simplemvp.view.ReposView;

/**
 * Created by mirjalol on 6/10/17.
 */

public class ReposFragment extends Fragment implements ReposView {

    @Inject
    ReposPresenterImpl reposPresenter;

    private FragmentReposBinding binding;

    public static ReposFragment newInstance() {
        return new ReposFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidSupportInjection.inject(this);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.bind(view);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        reposPresenter.loadRepos();
    }

    @Override
    public void showProgress() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        binding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showEmptyView() {
        binding.placeHolderText.setVisibility(View.VISIBLE);
    }

    @Override
    public void setRepoList(List<Repo> repoList) {
        binding.recyclerView.setAdapter(new ReposAdapter(reposPresenter, repoList));
    }

    class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

        private ReposPresenterImpl presenter;
        private List<Repo> repoList;

        ReposAdapter(ReposPresenterImpl presenter, List<Repo> repoList) {
            this.presenter = presenter;
            this.repoList = repoList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            ItemRepoBinding binding = ItemRepoBinding.inflate(inflater, viewGroup, false);
            return new ViewHolder(binding.getRoot());
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            viewHolder.binding.setItem(repoList.get(i));
            viewHolder.binding.setPresenter(presenter);
            viewHolder.binding.executePendingBindings();
        }

        @Override
        public int getItemCount() {
            return repoList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            ItemRepoBinding binding;

            public ViewHolder(View itemView) {
                super(itemView);
                binding = DataBindingUtil.bind(itemView);
            }
        }

    }

}
