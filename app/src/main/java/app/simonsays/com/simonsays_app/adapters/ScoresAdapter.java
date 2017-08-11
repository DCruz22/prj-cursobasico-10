package app.simonsays.com.simonsays_app.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.darielcruzhdez.tourismapp.R;
import com.example.darielcruzhdez.tourismapp.main.models.Score;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by darielcruzhdez on 10/8/16.
 */
public class ScoresAdapter extends ArrayAdapter<Score> {

    // Your sent context
    private Context mContext;
    // Your custom values for the spinner (User)
    private List<Score> mScores = new ArrayList<>();

    public ScoresAdapter(Context context, int textViewResourceId,
                       List<Score> values){
        super(context, textViewResourceId, values);
        this.mContext = context;
        this.mScores = values;
    }

    public void add(Score item) {

        mScores.add(item);
        notifyDataSetChanged();

    }

    // Clears the list adapter of all items.

    public void clear() {

        mScores.clear();
        notifyDataSetChanged();

    }

    // Returns the number of ToDoItems

    @Override
    public int getCount() {

        return mScores.size();

    }

    // Retrieve the number of ToDoItems

    @Override
    public Score getItem(int pos) {

        return mScores.get(pos);

    }

    // Get the ID for the ToDoItem
    // In this case it's just the position

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    public View returnSpinnerView(int position, View convertView){
        ViewHolderItem holder;

        if(convertView == null){
            LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
            convertView = inflater.inflate(R.layout.score_item, null);

            holder = new ViewHolderItem();
            holder.PlayerNameTV = (TextView) convertView.findViewById(R.id.playerNameTv);
            holder.PlayerScoreTV = (TextView) convertView.findViewById(R.id.playerScoreTv);
            holder.PlayerDateTV = (TextView) convertView.findViewById(R.id.playerDateTv);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolderItem)convertView.getTag();
        }

        Score item = mScores.get(position);

        if(item != null){
            holder.PlayerNameTV.setText(item.getName());
            holder.PlayerScoreTV.setText(item.getName());
            holder.PlayerDateTV.setText(item.getName());
            holder.PlayerNameTV.setTag(item.getId());
        }

        return convertView;
    }

    public static class ViewHolderItem{
        TextView PlayerNameTV;
        TextView PlayerScoreTV;
        TextView PlayerDateTV;
    }

}