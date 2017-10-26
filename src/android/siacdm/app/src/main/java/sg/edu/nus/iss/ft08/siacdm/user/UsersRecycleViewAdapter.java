package sg.edu.nus.iss.ft08.siacdm.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import sg.edu.nus.iss.ft08.siacdm.ControlFactory;
import sg.edu.nus.iss.ft08.siacdm.R;
import sg.edu.nus.iss.ft08.siacdm.model.User;

public class UsersRecycleViewAdapter extends RecyclerView.Adapter<UsersViewHolder> {
    private final List<User> users;

    UsersRecycleViewAdapter(List<User> users){
        if(users!=null){
            this.users=users;
        }
       else {
            this.users= new ArrayList<>();
        }
    }

    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_holder_user, parent, false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final UsersViewHolder holder, int position) {
        User current = users.get(position);
        holder.user = current;
        holder.txtUserID.setText(current.getUserId());
        holder.txtUserName.setText(current.getFullName());
        holder.txtCount.setText(Integer.toString(current.getAssignedSlot()));
        holder.view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ControlFactory.getUserDetailController().startEditUseCase(holder.user);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.users.size();
    }
}
