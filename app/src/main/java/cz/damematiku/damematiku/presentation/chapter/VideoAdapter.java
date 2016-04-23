package cz.damematiku.damematiku.presentation.chapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.model.Video;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int VIDEO_VIEW_TYPE = 1;
    public static final int HEADER_VIEW_TYPE = 2;

    private List<Video> data;
    private VideoClickListener listener;
    private Context context;

    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.author)
        TextView author;

        @Bind(R.id.thumbnail)
        ImageView thumbnail;

        @Bind(R.id.description)
        TextView description;

        @Bind(R.id.reputation)
        TextView reputation;

        public VideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.description)
        TextView description;

        public HeaderViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }



    VideoAdapter (Context context) {
        data = new ArrayList<>();
        this.context = context;
    }


    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER_VIEW_TYPE : VIDEO_VIEW_TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIDEO_VIEW_TYPE) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
            return new VideoViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chapter_description_item, parent, false);
            return new HeaderViewHolder(v);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Video video = data.get(position);

        if (getItemViewType(position) == HEADER_VIEW_TYPE) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.description.setText(video.description());
        } else {
            VideoViewHolder videoHolder = (VideoViewHolder) holder;
            videoHolder.author.setText(video.title());
            videoHolder.description.setText(video.description());

            Glide
                .with(context)
                .load("https://i.ytimg.com/vi/" + video.youtubeId() + "/hqdefault.jpg")
                .centerCrop()
                .placeholder(R.drawable.no_thumbnail)
                .crossFade()
                .into(videoHolder.thumbnail);

            if (listener != null) {
                videoHolder.itemView.setOnClickListener(v -> listener.onVideoClick(video));
            }
        }
    }

    public void setData(List<Video> videos) {
        data = videos;
        notifyDataSetChanged();
    }

    public void setListener(VideoClickListener listener) {
        this.listener = listener;
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public interface VideoClickListener {
        void onVideoClick(Video video);
    }
}
