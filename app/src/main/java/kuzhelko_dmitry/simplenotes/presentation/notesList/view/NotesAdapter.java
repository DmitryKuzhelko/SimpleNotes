package kuzhelko_dmitry.simplenotes.presentation.notesList.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kuzhelko_dmitry.simplenotes.R;
import kuzhelko_dmitry.simplenotes.domain.entities.Note;

/**
 * Created by kuzhe on 27.01.2018.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notes;
    private static ClickListener clickListener;

    public NotesAdapter() {
        notes = new ArrayList<>();
    }

    public interface ClickListener {
        void onItemClick(Note note);

        void onEditClick(Note note, int position);

        void onDeleteClick(Note note, int position);
    }

    public void updateAdapter(List<Note> notes) {
//        if (this.notes == null) {
            this.notes = notes;
//        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void addNote() {
        notifyItemInserted(getItemCount() - 1);
    }

    public void removeNote(int position) {
        notifyItemRemoved(position);
    }

    public void updateNote(int position, Note note) {
        notifyItemChanged(position, note);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_notes_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindNote(notes.get(position));
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Note note;
        private TextView tvNoteTitle;
        private TextView tvNoteDescription;
        private ImageButton editBtn;
        private ImageButton deleteBtn;

        public void bindNote(Note note) {
            this.note = note;
            tvNoteTitle.setText(note.getTitle());
            tvNoteDescription.setText(note.getDescription());
        }

        public ViewHolder(View itemView) {
            super(itemView);
            tvNoteTitle = (TextView) itemView.findViewById(R.id.noteTitle);
            tvNoteDescription = (TextView) itemView.findViewById(R.id.noteDescription);
            editBtn = (ImageButton) itemView.findViewById(R.id.editNote);
            deleteBtn = (ImageButton) itemView.findViewById(R.id.deleteNote);

            editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NotesAdapter.clickListener != null) {
                        NotesAdapter.clickListener.onEditClick(notes.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (NotesAdapter.clickListener != null) {
                        NotesAdapter.clickListener.onDeleteClick(notes.get(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (NotesAdapter.clickListener != null) {
                        NotesAdapter.clickListener.onItemClick(notes.get(getAdapterPosition()));
                    }
                }
            });
        }
    }
}
