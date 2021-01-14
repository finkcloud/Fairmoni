package com.philencripted.fairmoneytest.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.philencripted.fairmoneytest.R;
import com.philencripted.fairmoneytest.model.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> userList;
     Context context;

    public  UserAdapter(Context context, List<User> userList){
        this.context = context;
        this.userList = userList;
    }



    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create view for binding data to UI inflated from our listitem
        View view = LayoutInflater.from(context).inflate(R.layout.user_list_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        // get reference to a single user
        User user = userList.get(position);

        // bind user to UI
        holder.companyTv.setText(user.getCompany().getName());
        holder.userNameTv.setText(user.getName());

        // split name for initials
        String[] initials =  user.getName().split(" ");

        // display first initial on the textview
        holder.userInitialsTV.setText(""+ initials[0].charAt(0) + initials[1].charAt(0));

    }


    // get the no of items in the list
    @Override
    public int getItemCount() {

        if(userList.size() > 0){
            return userList.size();
        }else {
            return 0;
        }

    }

    // most extend viewholder from recyclerview  for binding view to the inflated list item in recycle view
    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView userInitialsTV;
        private TextView userNameTv;
        private TextView companyTv;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            this.userNameTv = itemView.findViewById(R.id.user_name);
            this.userInitialsTV = itemView.findViewById(R.id.user_short_initials);
            this.companyTv = itemView.findViewById(R.id.user_company);

        }
    }
}
