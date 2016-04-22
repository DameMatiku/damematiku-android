package cz.damematiku.damematiku.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.presentation.chapter.ChapterActivity;

public class MainActivity extends AppCompatActivity implements MainView, SectionAdapter.ChapterClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerview)
    RecyclerView sectionList;

    private SectionAdapter sectionAdapter;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpSectionList();

        presenter = new MainPresenter();
        presenter.setView(this);
        presenter.start();
    }

    private void setUpSectionList() {
        sectionAdapter = new SectionAdapter();
        sectionAdapter.setListener(this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        sectionList.setLayoutManager(layoutManager);
        sectionList.setAdapter(sectionAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showSections(List<Section> sections) {
        sectionAdapter.setData(sections);
    }

    @Override
    public void onChapterClick(Chapter chapter) {
        Intent intent = ChapterActivity.create(this, chapter);
        startActivity(intent);
    }
}
