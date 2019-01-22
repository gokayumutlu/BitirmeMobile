package com.gokay.bitirmeprojesi.messaging;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.gokay.bitirmeprojesi.R;
import com.gokay.bitirmeprojesi.messaging.MessageAdapter;
import com.gokay.bitirmeprojesi.messaging.Messages;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String gonderenUser;
    private String aliciUser;
    private Toolbar toolbar;
    private String alici_ad;

    private ImageButton chatAddButton;
    private ImageButton chatSendButton;
    private EditText chatMessage;

    private RecyclerView mMessagesList;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private DatabaseReference mRef;

    private List<Messages> messagesList =new ArrayList<>();
    private LinearLayoutManager linearLayoutM;
    private MessageAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        gonderenUser=getIntent().getStringExtra("gonderen_id");
        aliciUser=getIntent().getStringExtra("alici_id");
        alici_ad=getIntent().getStringExtra("alici_ad");
        toolbar=findViewById(R.id.chat_app_bar);
        chatAddButton=findViewById(R.id.chat_add_btn);
        chatSendButton=findViewById(R.id.chat_send_btn);
        chatMessage=findViewById(R.id.chat_message);
        mAdapter=new MessageAdapter(messagesList);
        mMessagesList=findViewById(R.id.messages_list);
        linearLayoutM=new LinearLayoutManager(this);
        mMessagesList.setHasFixedSize(true);
        mMessagesList.setLayoutManager(linearLayoutM);
        mMessagesList.setAdapter(mAdapter);

        //mRef=FirebaseDatabase.getInstance().getReference();
        reference=FirebaseDatabase.getInstance().getReferenceFromUrl("https://anaokulubitirme.firebaseio.com/");
        mRef=reference.child("Messages");

        loadMessages();


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        mAuth=FirebaseAuth.getInstance();


        getSupportActionBar().setTitle(alici_ad);

        reference.child("Chat").child(gonderenUser).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(!dataSnapshot.hasChild(aliciUser)){
                    Map chatAddM=new HashMap();
                    chatAddM.put("timestamp",ServerValue.TIMESTAMP);

                    Map chatUserM=new HashMap();
                    chatUserM.put("Chat/"+gonderenUser+"/"+aliciUser,chatAddM);
                    chatUserM.put("Chat/"+aliciUser+"/"+gonderenUser,chatAddM);

                    reference.updateChildren(chatUserM, new DatabaseReference.CompletionListener() {
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                            if(databaseError !=null){
                                Log.d("chat_log",databaseError.getMessage().toString());
                            }
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        chatSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
            }
        });



    }

    private void sendMessage(){
        String message=chatMessage.getText().toString();
        if(!TextUtils.isEmpty(message)){

            String gonderenUserRef="Messages/"+gonderenUser+"/"+aliciUser;
            String aliciUserRef="Messages/"+aliciUser+"/"+gonderenUser;

            DatabaseReference userMessagePush=reference.child("Messages").child(gonderenUser).child(aliciUser).push();
            String push_id=userMessagePush.getKey();

            Map messageMap = new HashMap();
            messageMap.put("message",message);
            messageMap.put("seen",false);
            messageMap.put("type","text");
            messageMap.put("time",ServerValue.TIMESTAMP);
            messageMap.put("from",gonderenUser);

            chatMessage.setText("");

            Map messageUserMap=new HashMap();
            messageUserMap.put(gonderenUserRef+"/"+push_id,messageMap);
            messageUserMap.put(aliciUserRef+"/"+push_id,messageMap);

            reference.updateChildren(messageUserMap, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                    if(databaseError!=null){
                        Log.d("chat_log2",databaseError.getMessage().toString());
                    }
                }
            });

        }
    }


    private void loadMessages(){
        mRef.child(gonderenUser).child(aliciUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Messages message=dataSnapshot.getValue(Messages.class);
                messagesList.add(message);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
