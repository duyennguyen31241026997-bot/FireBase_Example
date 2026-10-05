package vn.edu.ueh.thanhdnh.firebase_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ArticleViewAdapter extends RecyclerView.Adapter<ArticleViewHolder> {
    private Context mContext;
    private LayoutInflater mInflater;
    private List<Article> articles;

    public ArticleViewAdapter(Context context, List<Article> articles) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.articles = articles;
    }

    public void update(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.article_item, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article currentArticle = articles.get(position);
        holder.getTxtTitle().setText(currentArticle.getTitle());
        holder.getTxtContent().setText(currentArticle.getContent());

        String imageUrl = currentArticle.getImageUrl();
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            Glide.with(mContext)
                    .load(imageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(holder.getImgArticle());
        } else {
            holder.getImgArticle().setImageResource(R.drawable.ic_launcher_background);
        }
    }

    @Override
    public int getItemCount() {
        return articles != null ? articles.size() : 0;
    }
}
