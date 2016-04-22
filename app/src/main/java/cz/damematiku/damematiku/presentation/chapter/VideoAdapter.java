package cz.damematiku.damematiku.presentation.chapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    private List<Video> data;
    private VideoClickListener listener;


    public static class VideoViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView title;

        public VideoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    VideoAdapter () {
        data = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
        VideoViewHolder vh = new VideoViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Video video = data.get(position);
        VideoViewHolder videoHolder = (VideoViewHolder) holder;
        videoHolder.title.setText(video.title());
        if (listener != null) {
            videoHolder.itemView.setOnClickListener(v -> listener.onVideoClick(video));
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
