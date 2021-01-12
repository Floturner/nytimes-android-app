package dev.corp.floturner.thenytimes.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.corp.floturner.thenytimes.R;
import dev.corp.floturner.thenytimes.callbacks.OnArticleClickCallback;
import dev.corp.floturner.thenytimes.models.Article;
import dev.corp.floturner.thenytimes.models.MultiMedia;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {
    private List<Article> articles;
    private OnArticleClickCallback onArticleClickCallback;

    public ArticleAdapter(List<Article> articles, OnArticleClickCallback onArticleClickCallback) {
        this.articles = articles;
        this.onArticleClickCallback = onArticleClickCallback;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_article, viewGroup, false);

        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder articleViewHolder, int i) {
        articleViewHolder.bind(this.articles.get(i));
    }

    @Override
    public int getItemCount() {
        return this.articles.size();
    }

    /**
     * ArticleViewHolder Implementation
     */
    class ArticleViewHolder extends RecyclerView.ViewHolder {
        Article article;

        @BindView(R.id.item_article_cover_img)
        ImageView coverImageView;
        @BindView(R.id.item_article_title)
        TextView titleTextView;
        @BindView(R.id.item_article_published_date)
        TextView publishedDateTextView;
        @BindView(R.id.item_article_description)
        TextView descriptionTextView;

        ArticleViewHolder(@NonNull final View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onArticleClickCallback.onClick(article);
                }
            });
        }

        void bind(Article article) {
            this.article = article;

            Glide.with(itemView)
                    .load(getCoverImageUrl(article))
                    .placeholder(R.drawable.ic_placeholder)
                    .error(R.drawable.ic_error)
                    .into(coverImageView);

            titleTextView.setText(article.getTitle() != null ? article.getTitle() : "Title");
            publishedDateTextView.setText(article.getPublishedDate() != null ? article.getPublishedDate().substring(0, 10) : "Date");
            descriptionTextView.setText(article.getDescription() != null ? article.getDescription() : "Description");
        }

        private String getCoverImageUrl(Article article) {
            String IMG_URL = "_";

            if (!article.getMultiMedias().isEmpty()) {
                for (MultiMedia media : article.getMultiMedias()) {
                    if (media.getType().equals("image") && media.getFormat().equals("thumbLarge")) {
                        IMG_URL = media.getUrl();
                    }
                }
            }

            return IMG_URL;
        }
    }
}
