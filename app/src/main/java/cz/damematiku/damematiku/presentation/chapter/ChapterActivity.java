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
import android.widget.TextView;

import com.google.android.youtube.player.YouTubePlayer;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.MathApplication;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.model.Author;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Video;
import cz.damematiku.damematiku.depinject.component.DaggerActivityInjectorComponent;
import cz.damematiku.damematiku.presentation.main.SectionAdapter;
import cz.damematiku.damematiku.presentation.video.VideoActivity;

public class ChapterActivity extends AppCompatActivity implements ChapterView, VideoAdapter.VideoClickListener {

    private static final String ARG_CHAPTER = "ARG_CHAPTER";

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.chapter_title)
    TextView chapterTitle;

    @Bind(R.id.chapter_number)
    TextView chapterNuber;

    @Bind(R.id.section_title)
    TextView sectionTitle;

    @Bind(R.id.section_number)
    TextView sectionNumber;

    @Bind(R.id.recyclerview)
    RecyclerView videoList;

    @Inject
    ChapterPresenter presenter;

    private VideoAdapter videoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter);

        DaggerActivityInjectorComponent.builder()
                .baseComponent(MathApplication.getBaseComponent())
                .build()
                .inject(this);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("");

        setUpSectionList();

        presenter.setView(this);
        Chapter chapter = getIntent().getParcelableExtra(ARG_CHAPTER);
        presenter.setData(chapter);
        presenter.start();
    }

    @Override
    public void setTitle(Chapter chapter) {
        chapterTitle.setText(chapter.name());
    }

    @Override
    public void showVideos(String chapterDescription ,List<Video> videos) {
        videos.add(0, Video.create(0, "", 22, "", chapterDescription, Author.create("","")));
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
