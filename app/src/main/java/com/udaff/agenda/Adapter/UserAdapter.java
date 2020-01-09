package com.udaff.agenda.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.udaff.agenda.Entity.User;
import com.udaff.agenda.R;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> userList;
    private UserCallback userListener;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    public void setUserListener(UserCallback userCallback) {
        this.userListener = userCallback;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int position) {

        final User user = userList.get(position);

        String userName = String.valueOf(user.getName());

        userViewHolder.textUserName.setText(userName);

        userViewHolder.constraintUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.constraint_user_row) {
                    userListener.showUserDetail(user);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintUser;
        TextView textUserName;


        UserViewHolder(View itemView) {
            super(itemView);
            this.constraintUser = itemView.findViewById(R.id.constraint_user_row);
            this.textUserName = itemView.findViewById(R.id.text_item_user_name);
        }
    }

    public interface UserCallback {
        void showUserDetail(User user);
    }

}
