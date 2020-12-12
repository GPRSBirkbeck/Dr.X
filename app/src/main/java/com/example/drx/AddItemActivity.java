package com.example.drx;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.drx.models.ListedItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddItemActivity extends AppCompatActivity {
    EditText mID, mPrice, mQuantity, mDescription, mSpecs;
    //TODO build functionality for images
    //int Image;
    private FirebaseFirestore db;
    //
    private FirebaseAuth mAuth;
    private static final String TAG = "AddItemActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mID = (EditText) findViewById(R.id.item_id_input);
        mPrice = (EditText) findViewById(R.id.item_id_input);
        mQuantity = (EditText) findViewById(R.id.item_id_input);
        mDescription = (EditText) findViewById(R.id.item_id_input);
        mSpecs = (EditText) findViewById(R.id.item_id_input);

        //instantiate the DB
        db = FirebaseFirestore.getInstance();

        //userID
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        String uid = currentUser.getUid();

    }

    public void submitItem(View view) {
        String id = mID.getText().toString();
        double price = Double.parseDouble(mPrice.getText().toString());
        double quantity = Double.parseDouble(mQuantity.getText().toString());
        String description = mDescription.getText().toString();
        String specs = mSpecs.getText().toString();

        ListedItem currentItemToBeAdded = new ListedItem(id,price,quantity,description,specs);

        db.collection("listedItems").document("LA")
                .set(currentItemToBeAdded)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

        Intent intent = new Intent(this, MarketActivity.class);
        startActivity(intent);
    }
}