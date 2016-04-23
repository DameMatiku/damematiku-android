package cz.damematiku.damematiku.presentation.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.damematiku.damematiku.MathApplication;
import cz.damematiku.damematiku.R;
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;
import cz.damematiku.damematiku.data.model.Tag;
import cz.damematiku.damematiku.depinject.component.DaggerActivityInjectorComponent;
import cz.damematiku.damematiku.presentation.chapter.ChapterActivity;

public class MainActivity extends AppCompatActivity implements MainView, SectionAdapter.ChapterClickListener {


    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.recyclerview)
    RecyclerView sectionList;

    @Bind(R.id.search)
    EditText search;

    @Bind(R.id.tag_spinner)
    Spinner tagSpinner;

    @Bind(R.id.subtag_spinner)
    Spinner subTagSpinner;

    private SectionAdapter sectionAdapter;

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        DaggerActivityInjectorComponent.builder()
                .baseComponent(MathApplication.getBaseComponent())
                .build()
                .inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        setUpSectionList();
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
    public void showTags(List<Tag> tags) {

        List<String> tagTitle = new ArrayList<>();
        tagTitle.add("Všechno");
        for (Tag t : tags) {
            tagTitle.add(t.name());
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item, tagTitle);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        tagSpinner.setAdapter(adapter);

        tagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    presenter.tagSelected(tags.get(position - 1));
                } else {
                    subTagSpinner.setVisibility(View.GONE);
                    presenter.tagSelected(null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public void showSubTag(List<Tag> subTags) {


        List<String> tagTitle = new ArrayList<>();
        tagTitle.add("Všechno");
        for (Tag t : subTags) {
            tagTitle.add(t.name());
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter (this, android.R.layout.simple_spinner_item, tagTitle);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        subTagSpinner.setAdapter(adapter);

        subTagSpinner.setVisibility(View.VISIBLE);

        subTagSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    presenter.subTagSelected(subTags.get(position - 1));
                } else {
                    presenter.subTagSelected(null);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void hideSubTags() {
        subTagSpinner.setVisibility(View.GONE);
    }

    @Override
    public void onChapterClick(Chapter chapter, Section section, int chapterNum, int sectionNum) {
        Intent intent = ChapterActivity.create(this, chapter, section, chapterNum, sectionNum);
        startActivity(intent);
    }


}
