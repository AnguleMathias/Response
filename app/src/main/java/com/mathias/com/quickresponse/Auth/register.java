package com.mathias.com.quickresponse.Auth;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mathias.com.quickresponse.Home;
import com.mathias.com.quickresponse.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class register extends AppCompatActivity {

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    Button done;
    private Uri mImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    SharedPreferences pref;
    EditText firstName, lastName, phoneNumber, address, emengencyName, emengencyNumber, idType, idNumber;
    CircleImageView circleImageView;
    String userName, password, email,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pref =  getSharedPreferences("mypref",MODE_PRIVATE);
       String userName = pref.getString("keyname1",null);
        String password = pref.getString("keyname1",null);
        String email = pref.getString("keyname1",null);
        String phone = pref.getString("keyname1",null);

        mStorageRef = FirebaseStorage.getInstance().getReference("profile");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("profile");
        firstName = findViewById(R.id.fist_name);
       lastName = findViewById(R.id.last_name);
        phoneNumber = findViewById(R.id.phone_number);
        address = findViewById(R.id.address);
        emengencyNumber = findViewById(R.id.emengency_number);
        idNumber = findViewById(R.id.ID_number);

       done =  findViewById(R.id.done);
        circleImageView =  findViewById(R.id.circleimage);
        TextView textView = findViewById(R.id.edit);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

uploadFile();
        }
        });

    }

    // image chooser
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            mImageUri = data.getData();

            Picasso.get().load(mImageUri).into(circleImageView);

        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

     private void uploadFile() {
         if (mImageUri != null && firstName != null && lastName != null) {
             done.setClickable(false);
             done.setText("processing...");

             StorageReference fileReference = mStorageRef.child(System.currentTimeMillis()
                     + "." + getFileExtension(mImageUri));

             StorageTask<UploadTask.TaskSnapshot> mUploadTask = fileReference.putFile(mImageUri)
                     .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                         @Override
                         public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                             Handler handler = new Handler();
                             handler.postDelayed(new Runnable() {
                                 @Override
                                 public void run() {

                                 }
                             }, 500);

                             Toast.makeText(register.this, "profile successful", Toast.LENGTH_LONG).show();


                             profile profile = new profile(firstName.getText().toString().trim(),
                                     lastName.getText().toString().trim(),
                                     phoneNumber.getText().toString().trim(),
                                     address.getText().toString().trim(),
                                    emengencyName.getText().toString().trim(),
                                    emengencyNumber.getText().toString().trim(),
                                     idType.getText().toString().trim(),
                                     idNumber.getText().toString().trim(),
                                     taskSnapshot.getDownloadUrl().toString());

                             String uploadId = mDatabaseRef.push().getKey();
                             mDatabaseRef.child(uploadId).setValue(profile);



                             user pprofile = new user(
                                     userName,
                                      password,
                                      email,
                                     phone);


                             String uploadIdv = mDatabaseRef.push().getKey();
                             mDatabaseRef.child(uploadIdv).setValue(pprofile);



                             openImagesActivity();





                         }
                     })
                     .addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {
                             Toast.makeText(register.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                             done.setClickable(true);
                             done.setText("done");
                         }
                     });


         } else {
             Toast.makeText(this, "select image", Toast.LENGTH_SHORT).show();
         }
     }

    private void openImagesActivity() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }




    }

