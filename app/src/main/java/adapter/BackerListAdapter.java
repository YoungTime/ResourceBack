package adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DuZeming on 2018/3/9.
 */
public class BackerListAdapter  extends RecyclerView.Adapter<ListViewHolder>{
    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ListViewHolder extends RecyclerView.ViewHolder{

    public ListViewHolder(View itemView) {
        super(itemView);
    }
}
