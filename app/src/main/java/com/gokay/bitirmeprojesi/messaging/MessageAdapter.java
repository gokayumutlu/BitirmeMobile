package com.gokay.bitirmeprojesi.messaging;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gokay.bitirmeprojesi.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {

    private List<Messages> messageList;
    private DatabaseReference userDb;
    private FirebaseAuth mAuth;

    public MessageAdapter(List<Messages> mMessageList){
        this.messageList=mMessageList;
    }




    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.message_list ,parent, false);
        return new MessageViewHolder(v);
    }





    public class MessageViewHolder extends RecyclerView.ViewHolder{
        public TextView mText;
        public CircleImageView profileImage;
        public TextView displayName;
        public ImageView messageImagee;

        public MessageViewHolder(View view){
            super(view);

            mText=view.findViewById(R.id.message_text_layout);
            profileImage=view.findViewById(R.id.message_profile);
            displayName=view.findViewById(R.id.nameText);
            //messageImagee=view.findViewById(R.id.messageImagee);

        }
    }

    @Override
    public void onBindViewHolder(final MessageAdapter.MessageViewHolder holder, int position) {
        mAuth=FirebaseAuth.getInstance();
        String current_user_id=mAuth.getCurrentUser().getUid();
        Messages chat=messageList.get(position);
        String mFrom= chat.getFrom();

        if(mFrom.equals(current_user_id)){
            holder.mText.setBackgroundColor(Color.WHITE);
            holder.mText.setTextColor(Color.BLACK);
        }
        else{
            holder.mText.setBackgroundResource(R.drawable.message_text_background);
            holder.mText.setTextColor(Color.WHITE);
        }

        holder.mText.setText(chat.getMessage());

        //

        String mType=chat.getType();

        userDb=FirebaseDatabase.getInstance().getReference().child("Users").child(mFrom);

        userDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("ad").getValue().toString();

                holder.displayName.setText(name);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if(mType.equals("text")){
            holder.mText.setText(chat.getMessage());
            //holder.messageImagee.setVisibility(View.INVISIBLE);
        }
        else{
            holder.mText.setVisibility(View.INVISIBLE);

        }
        //
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }

}



