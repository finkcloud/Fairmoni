package com.philencripted.fairmoneytest.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.philencripted.fairmoneytest.R;
import com.philencripted.fairmoneytest.model.Address;
import com.philencripted.fairmoneytest.model.Company;

public class UserDetailsActivity extends AppCompatActivity {

    TextView nameTv, emailTv, phoneTv, websiteTv,companyTv,addressTv;
    String name,email,phone,website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        // get views
        addressTv = findViewById(R.id.user_address_detail);
        nameTv = findViewById(R.id.users_fullname);
        emailTv = findViewById(R.id.user_email_detail);
        phoneTv = findViewById(R.id.user_phone_detail);
        websiteTv = findViewById(R.id.user_website_detail);
        companyTv = findViewById(R.id.user_company_detail);

        // setup UI
        setupUIWithData();

    }


    @SuppressLint("SetTextI18n")
    private void setupUIWithData(){
        // get data from intent for display
        Bundle intentBundle = getIntent().getExtras();

        // get company and address from intent as object with the help of serializable
        Company company = (Company) intentBundle.getSerializable("company");
        Address address = (Address) intentBundle.getSerializable("address");


        // retrieve other values from intent
        name = intentBundle.getString("name");
        email = intentBundle.getString("email");
        phone = intentBundle.getString("phone");
        website = intentBundle.getString("website");

        try {

            // display views
            nameTv.setText(name);
            emailTv.setText(email);
            phoneTv.setText(phone);
            websiteTv.setText(website);

            assert company != null;
            companyTv.setText(company.getName());

            assert address != null;
            addressTv.setText(address.getStreet()
                    + getString(R.string.linebreak) +  address.getSuite()
                    + getString(R.string.linebreak)+  address.getCity()
                    + getString(R.string.linebreak) +  address.getZipcode());

            //debug
            System.out.println(company.getName());



        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}