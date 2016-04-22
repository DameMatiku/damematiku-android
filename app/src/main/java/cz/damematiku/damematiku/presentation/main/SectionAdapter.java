package cz.damematiku.damematiku.presentation.main;

import android.content.Context;
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
import cz.damematiku.damematiku.data.model.Chapter;
import cz.damematiku.damematiku.data.model.Section;

/**
 * Created by semanticer on 22. 4. 2016.
 */
public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Section> data;
    private ChapterClickListener listener;

    public static class SectionViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.title)
        TextView title;

        @Bind(R.id.number)
        TextView number;

        @Bind(R.id.top_layout)
        ViewGroup topLayout;

        @Bind(R.id.chapters)
        ViewGroup chapters;

        public SectionViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public SectionAdapter() {
        data = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_item, parent, false);
        SectionViewHolder vh = new SectionViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Section section = data.get(position);
        SectionViewHolder sectionHolder = (SectionViewHolder) holder;
        sectionHolder.title.setText(section.title());
        sectionHolder.number.setText((position+1)+"");
        bindChapters(position+1, section, sectionHolder);

        sectionHolder.topLayout.setOnClickListener(v -> {
            if (sectionHolder.chapters.getVisibility() == View.GONE) {
                sectionHolder.chapters.setVisibility(View.VISIBLE);
            } else {
                sectionHolder.chapters.setVisibility(View.GONE);
            }
        });
    }

    private void bindChapters(int position, Section section, SectionViewHolder sectionHolder) {
        for (int i = 0; i < sectionHolder.chapters.getChildCount(); i++) {
            ViewGroup viewGroup = (ViewGroup) sectionHolder.chapters.getChildAt(i);
            if (i < section.chapters().size()) {
                Chapter chapter = section.chapters().get(i);
                viewGroup.setVisibility(View.VISIBLE);
                TextView title = ButterKnife.findById(viewGroup, R.id.chapter_title);
                TextView number = ButterKnife.findById(viewGroup, R.id.chapter_number);
                title.setText(chapter.title());
                number.setText(position + "." + (i+1));
                if (listener != null) {
                    viewGroup.setOnClickListener(v -> listener.onChapterClick(chapter));
                }
            } else {
                viewGroup.setVisibility(View.GONE);
            }

        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Section> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void setListener(ChapterClickListener listener) {
        this.listener = listener;
    }

    public interface ChapterClickListener {
        void onChapterClick(Chapter chapter);
    }
}
