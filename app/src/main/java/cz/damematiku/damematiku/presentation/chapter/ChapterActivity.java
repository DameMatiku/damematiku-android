package cz.damematiku.damematiku.presentation.chapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.google.android.youtube.player.YouTubePlayer;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.presentation.main.SectionAdapter;
import cz.damematiku.damematiku.presentation.video.VideoActivity;

public class ChapterActivity extends AppCompatActivity implements ChapterView, VideoAdapter.VideoClickListener {

    private static final String ARG_CHAPTER = "ARG_CHAPTER";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerview)
    RecyclerView videoList;

    ChapterPresenter presenter;

    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setUpSectionList();

        presenter = new ChapterPresenter();
        presenter.setView(this);
        Chapter chapter = getIntent().getParcelableExtra(ARG_CHAPTER);
        presenter.setData(chapter);
        presenter.start();
    }

    @Override
    public void setTitle(Chapter chapter) {
        toolbar.setTitle(chapter.name());
    }

    @Override
    public void showVideos(String chapterDescription ,List<Video> videos) {
        videos.add(0, Video.create(0, "", chapterDescription, ""));
        videoAdapter.setData(videos);
    }

    private void setUpSectionList() {
        videoAdapter = new VideoAdapter(this);
        videoAdapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        videoList.setLayoutManager(layoutManager);
        videoList.setAdapter(videoAdapter);
    }

    public static Intent create(Context context, Chapter chapter) {
        Intent intent = new Intent(context, ChapterActivity.class);
        intent.putExtra(ARG_CHAPTER, chapter);
        return intent;
    }

    @Override
    public void onVideoClick(Video video) {
        Intent intent = VideoActivity.create(this, video);
        startActivity(intent);
    }

}
