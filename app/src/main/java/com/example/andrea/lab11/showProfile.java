package com.example.andrea.lab11;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import static android.graphics.drawable.Drawable.createFromPath;


public class showProfile extends AppCompatActivity {

    private MyUser myUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //+++++++++++++set fields+++++++++++++
        setContentView(R.layout.show_profile);

        findViewById(R.id.sign_out_button).setOnClickListener(v->{
            Utilities.signOut();
            Intent intent = new Intent(
                    getApplicationContext(),
                    login.class
            );
            startActivity(intent);
        });

        //set editButton
        ImageView editButton = findViewById(R.id.imageViewEditButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String caller = getIntent().getStringExtra("caller");

                if(caller!="editProfile") {
                    Intent intent = new Intent(
                            getApplicationContext(),
                            editProfile.class
                    );
                    intent.putExtra("caller", "showProfile");
                    startActivity(intent);
                }
                else{
                    onBackPressed();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        //create MyUser
        myUser = new MyUser(getApplicationContext());

        //TODO change value if myUser's elements are null (to delete in final release of app)
        String name;
        String surname;
        String email;
        String biography;
        if(myUser.getName() ==null){
            name = getResources().getString(R.string.name);
        }else{
            name = myUser.getName();
        }
        if(myUser.getSurname()==null){
            surname = getResources().getString(R.string.surname);
        }else{
            surname = myUser.getSurname();
        }
        if(myUser.getEmail()==null){
                email = getResources().getString(R.string.email);
            }else{
            email = myUser.getEmail();
        }if(myUser.getBiography()==null){
            biography = getResources().getString(R.string.bio);
        }else{
            biography = myUser.getBiography();
        }

        //set name and surname
        TextView nameSurnameView = findViewById(R.id.nameSurnameShow);
        nameSurnameView.setText(getString(R.string.nameSurname,name,surname));

        //set email
        TextView emailView = findViewById(R.id.emailShow);
        emailView.setText(email);

        //set biography
        TextView biographyView = findViewById(R.id.showProfileBio);
        biographyView.setText(biography);

        //set image
        ImageView profileView = findViewById(R.id.imageViewShow);
        if( myUser.getImage() == null){

            //if there is not a profile image load the default one
            profileView.setImageDrawable(getResources().getDrawable(R.drawable.ic_person_black_40dp));
        }else{

            //load the profile image
            Drawable bd = createFromPath(myUser.getImage());
            profileView.setImageDrawable(bd);
        }
    }
}
