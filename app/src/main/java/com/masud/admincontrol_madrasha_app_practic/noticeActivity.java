package com.masud.admincontrol_madrasha_app_practic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.ImageViewCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class noticeActivity extends AppCompatActivity {
    private MaterialCardView galleryImagePicking;
    private ImageView imagePost;
    private AppCompatButton submitBtn;
    private TextInputLayout textInputLayout;
    private ProgressDialog dialog;

    private DatabaseReference database;

    String postTitle;

    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        database = FirebaseDatabase.getInstance().getReference().child("Notice");

        imagePost = findViewById(R.id.postImage);
        galleryImagePicking = findViewById(R.id.pickImage);
        submitBtn = findViewById(R.id.submitBtn);
        textInputLayout = findViewById(R.id.textInputLayout);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Database");
        dialog.setMessage("Uploading...");
        dialog.setCancelable(true);



        galleryImagePicking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imagePickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(imagePickIntent, 1);
            }
        });
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postTitle = textInputLayout.getEditText().getText().toString().trim();

                if (postTitle.isEmpty()){
                    textInputLayout.getEditText().setError("Nothing here!");
                    textInputLayout.getEditText().requestFocus();
                    
                }else if (imageBitmap == null){
                    gotoDatabase();
                }
               
            }
        });
    }

    private void gotoDatabase() {
        dialog.show();
        String uniqueId = database.push().getKey();

        NoticeData noticeData = new NoticeData(postTitle,"4 sep 2022",uniqueId,"5:3 pm","");
        assert uniqueId != null;
        database.child(uniqueId).setValue(noticeData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                dialog.dismiss();
                Toast.makeText(noticeActivity.this, "Upload loading", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            try {
                imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            imagePost.setImageBitmap(imageBitmap);
        }



    }
}