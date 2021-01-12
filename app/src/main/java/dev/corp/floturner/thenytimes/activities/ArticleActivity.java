package dev.corp.floturner.thenytimes.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.corp.floturner.thenytimes.R;
import dev.corp.floturner.thenytimes.models.Article;

public class ArticleActivity extends AppCompatActivity {
    public static String ARTICLE_SELECTED = "article_selected";
    Article article;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.article_webview)
    WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        ButterKnife.bind(this);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);

        progressBar.setIndeterminate(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                webView.setVisibility(View.VISIBLE);
                super.onPageFinished(view, url);
            }
        });

        article = (Article) getIntent().getSerializableExtra(ARTICLE_SELECTED);
        webView.loadUrl(article.getUrl());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_article, menu);

        MenuItem item = menu.findItem(R.id.action_article_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(item);
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, article.getUrl());
        shareActionProvider.setShareIntent(shareIntent);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }
}
