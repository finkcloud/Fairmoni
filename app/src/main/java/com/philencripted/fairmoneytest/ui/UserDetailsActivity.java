package com.philencripted.fairmoneytest.ui;

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

        addressTv = findViewById(R.id.user_address_detail);
        nameTv = findViewById(R.id.users_fullname);
        emailTv = findViewById(R.id.user_email_detail);
        phoneTv = findViewById(R.id.user_phone_detail);
        websiteTv = findViewById(R.id.user_website_detail);
        companyTv = findViewById(R.id.user_company_detail);


        Bundle intentBundle = getIntent().getExtras();


        Company company = (Company) intentBundle.getSerializable("company");
        Address address = (Address) intentBundle.getSerializable("address");


        name = intentBundle.getString("name");
        email = intentBundle.getString("email");
        phone = intentBundle.getString("phone");
        website = intentBundle.getString("website");

        try {

            nameTv.setText(name);
            emailTv.setText(email);
            phoneTv.setText(phone);
            websiteTv.setText(website);
            companyTv.setText(company.getName());
            addressTv.setText(address.getStreet()
                    + "\n" +  address.getSuite()
                    + "\n" +  address.getCity()
                    + "\n" +  address.getZipcode());

            //debug
            System.out.println(company.getName());
            System.out.println(address.getStreet());
            System.out.println(name);


        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }
}