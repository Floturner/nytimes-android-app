package dev.corp.floturner.thenytimes.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.corp.floturner.thenytimes.R;
import dev.corp.floturner.thenytimes.activities.ArticleActivity;
import dev.corp.floturner.thenytimes.activities.MainActivity;
import dev.corp.floturner.thenytimes.adapters.ArticleAdapter;
import dev.corp.floturner.thenytimes.callbacks.OnArticleClickCallback;
import dev.corp.floturner.thenytimes.models.Article;
import dev.corp.floturner.thenytimes.models.ArticlesResponse;
import dev.corp.floturner.thenytimes.server.APIClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScienceFragment extends Fragment {
    Activity context;
    ArticlesResponse articlesResponse;

    @BindView(R.id.science_swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.science_recycler_view)
    RecyclerView recyclerView;

    public static ScienceFragment newInstance() {
        return new ScienceFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        context = getActivity();
        if (context != null) {
            context.setTitle(R.string.nav_science);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_science, container, false);

        ButterKnife.bind(this, v);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getArticles();
            }
        });

        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));

        getArticles();

        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_refresh) {
            getArticles();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void getArticles() {
        swipeRefreshLayout.setRefreshing(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                MainActivity.apiService.getStoriesBySection(getResources().getStringArray(R.array.sections)[3], APIClient.API_KEY).enqueue(callback);
            }
        }, 1500);
    }

    OnArticleClickCallback onArticleClickCallback = new OnArticleClickCallback() {
        @Override
        public void onClick(Article article) {
            Intent intent = new Intent(context, ArticleActivity.class);
            intent.putExtra(ArticleActivity.ARTICLE_SELECTED, article);
            context.startActivity(intent);
            context.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }
    };

    Callback<ArticlesResponse> callback = new Callback<ArticlesResponse>() {
        @Override
        public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
            if (response.isSuccessful()) {
                articlesResponse = response.body();
                if (articlesResponse != null && !articlesResponse.getArticles().isEmpty()) {
                    ArticleAdapter articleAdapter = new ArticleAdapter(articlesResponse.getArticles(), onArticleClickCallback);
                    recyclerView.setAdapter(articleAdapter);
                }
                else {
                    Snackbar.make(swipeRefreshLayout, R.string.nothing_to_display, Snackbar.LENGTH_SHORT).show();
                }
            }
            swipeRefreshLayout.setRefreshing(false);
        }

        @Override
        public void onFailure(@NonNull Call<ArticlesResponse> call, @NonNull Throwable t) {
            Snackbar.make(swipeRefreshLayout, R.string.network_troubleshoot, Snackbar.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    };
}
