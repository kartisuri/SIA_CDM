package sg.edu.nus.iss.ft08.siacdm.user;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UsersViewHolder extends RecyclerView.ViewHolder {
    public final View view;
    final TextView txtUserName;
    final TextView txtUserID;
    final TextView txtCount;
    public User user;

    UsersViewHolder(View itemView){
        super(itemView);
        view = itemView;
        txtUserID = (TextView) itemView.findViewById(R.id.user_ID);
        txtUserName = (TextView) itemView.findViewById(R.id.user_name);
        txtCount = (TextView) itemView.findViewById(R.id.slot_count_user);
    }
}
