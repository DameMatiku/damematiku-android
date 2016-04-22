package cz.damematiku.damematiku.presentation.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.DeveloperKey;
import cz.damematiku.damematiku.data.model.Video;

public class VideoActivity extends YouTubeFailureRecoveryActivity implements VideoView {

    private static final String ARG_VIDEO = "ARG_VIDEO";

//    @Bind(R.id.toolbar)
//    Toolbar toolbar;

    @Bind(R.id.youtube_view)
    YouTubePlayerView youTubePlayerView;

    VideoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_test);
        ButterKnife.bind(this);
        youTubePlayerView.initialize(DeveloperKey.DEVELOPER_KEY, this);

//        setSupportActionBar(toolbar);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        Video video = getIntent().getParcelableExtra(ARG_VIDEO);
//
//        presenter = new VideoPresenter();
//        presenter.setData(video);
//        presenter.setView(this);
//        presenter.start();
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("wKJ9KzGQq0w");
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    public static Intent create(Context context, Video video) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(ARG_VIDEO, video);
        return intent;
    }
}
