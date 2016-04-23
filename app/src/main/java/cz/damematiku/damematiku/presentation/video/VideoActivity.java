package cz.damematiku.damematiku.presentation.video;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatCallback;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.Toolbar;

import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.MathApplication;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.DeveloperKey;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.depinject.component.DaggerActivityInjectorComponent;

public class VideoActivity extends YouTubeFailureRecoveryActivity implements VideoView, AppCompatCallback {

    private static final String ARG_VIDEO = "ARG_VIDEO";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private AppCompatDelegate delegate;

    @Inject
    VideoPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DaggerActivityInjectorComponent.builder()
                .baseComponent(MathApplication.getBaseComponent())
                .build()
                .inject(this);

        delegate = AppCompatDelegate.create(this, this);
        delegate.onCreate(savedInstanceState);
        delegate.setContentView(R.layout.activity_video);
        ButterKnife.bind(this);

        delegate.setSupportActionBar(toolbar);
        delegate.getSupportActionBar().setDisplayShowHomeEnabled(true);

        YouTubePlayerFragment youTubePlayerFragment =
                (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
        youTubePlayerFragment.initialize(DeveloperKey.DEVELOPER_KEY, this);

        Video video = getIntent().getParcelableExtra(ARG_VIDEO);

        presenter.setData(video);
        presenter.setView(this);
        presenter.start();
    }

    @Override
    public void onSupportActionModeStarted(ActionMode mode) {
        //let's leave this empty, for now
    }

    @Override
    public void onSupportActionModeFinished(ActionMode mode) {
        // let's leave this empty, for now
    }

    @Nullable
    @Override
    public ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback) {
        return null;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo("wKJ9KzGQq0w");
        }
    }

    @Override
    protected YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
    }

    public static Intent create(Context context, Video video) {
        Intent intent = new Intent(context, VideoActivity.class);
        intent.putExtra(ARG_VIDEO, video);
        return intent;
    }
}
