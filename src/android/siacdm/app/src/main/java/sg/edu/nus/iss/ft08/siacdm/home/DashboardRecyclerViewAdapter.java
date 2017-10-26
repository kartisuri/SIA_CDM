package sg.edu.nus.iss.ft08.siacdm.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.DashboardItem;

public class DashboardRecyclerViewAdapter extends RecyclerView.Adapter<DashboardItemViewHolder> {
  private final List<DashboardItem> items;

  public DashboardRecyclerViewAdapter(List<DashboardItem> items) {
    if(items!=null) {
      this.items = items;
    } else {
      this.items = new ArrayList<>();
    }
  }

  @Override
  public DashboardItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.view_holder_dashboard_item, parent, false);
    return new DashboardItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final DashboardItemViewHolder holder, int position) {
    final DashboardItem current = items.get(position);
    holder.item = current;
    holder.icon.setImageDrawable(current.getIcon());
    holder.txtName.setText(current.getName());
    holder.txtCount.setText(Integer.toString(current.getCount()));
    if(current.getName().equalsIgnoreCase("My Profile"))
    {
      holder.txtCount.setVisibility(View.INVISIBLE);
    }
    holder.view.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View v) {
        current.startUseCase();
      }
    });
  }

  @Override
  public int getItemCount() {
    return this.items.size();
  }
}
