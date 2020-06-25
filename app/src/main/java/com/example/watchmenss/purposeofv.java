package com.example.watchmenss;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class purposeofv extends AppCompatActivity {

    int preSelectedIndex = -1;
    Uri uri1;
    static final int CAMERA_REQUEST_CODE = 1;
    String currentPhotoPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purposeofv);



        ListView listView = (ListView)findViewById(R.id.listView);
        final List<UserModel> users = new ArrayList<>();
        users.add(new UserModel(false,"Friend"));
        users.add(new UserModel(false,"Relative"));
        users.add(new UserModel(false,"Repair & Services"));
        users.add(new UserModel(false,"Delivery Person"));
        users.add(new UserModel(false,"Postman"));
        users.add(new UserModel(false,"Others"));

        final CustomAdapter adapter = new CustomAdapter(this,users);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                UserModel model = users.get(i);
                model.setSelected(true);
                users.set(i,model);

                if(preSelectedIndex > -1 && preSelectedIndex!=i)
                {
                    UserModel preRecord = users.get(preSelectedIndex);
                    preRecord.setSelected(false);

                    users.set(preSelectedIndex,preRecord);
                }
                preSelectedIndex = i;
                adapter.updateRecords(users);
            }
        });
    }

    public void OpenCamera(View view) {

        //dispatchTakePictureIntent();
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST_CODE)
        {
            startActivity(new Intent(purposeofv.this, verifyotp.class));

        }
    }


    /*private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                /*".jpg",         /* suffix */
                //storageDir      /* directory */
        //);

        // Save a file: path for use with ACTION_VIEW intents
        /*currentPhotoPath = image.getAbsolutePath();
        return image;
    }*/



    /*private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File

            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                uri1 = photoURI;
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, CAMERA_REQUEST_CODE);
                takePictureIntent.setType("image/*");
            }
        }
    }*/
}