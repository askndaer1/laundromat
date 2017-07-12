package com.laundromat.codesoft.laundromat;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.laundromat.codesoft.laundromat.Laundromast.laundromat;


import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {

    DatabaseReference databaseCustomer;
    ListView listvieware;
    Query query;
    List<Customer> CustomerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomerList = new ArrayList<>();
        listvieware = (ListView) findViewById(R.id.listview11);
        databaseCustomer = FirebaseDatabase.getInstance().getReference("Customer");


        Button b1 = (Button) findViewById(R.id.button2);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    AddNewCustomer();
                AddNewL();
            }
        });

        Button b2 = (Button) findViewById(R.id.button3);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSingalValue();
            }
        });

    }

    private void ShowSingalValue() {

        Query mDatabase = databaseCustomer.orderByChild("customerName").equalTo("fares").limitToFirst(1);

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot artSnapshot : dataSnapshot.getChildren()) {

                    String carid = artSnapshot.child("customerName").getValue().toString();
                    Toast.makeText(MainActivity.this, carid, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        mDatabase.addValueEventListener(postListener);

    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    public void ViewFullList() {
        databaseCustomer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CustomerList.clear();
                for (DataSnapshot artSnapshot : dataSnapshot.getChildren()) {

                    Customer customer = artSnapshot.getValue(Customer.class);
                    CustomerList.add(customer);
                }
                CustomerListAdapter adpter = new CustomerListAdapter(MainActivity.this, CustomerList);
                listvieware.setAdapter(adpter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void ViewListWitSearch() {
        query = databaseCustomer.orderByChild("customerName").equalTo("fares");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                CustomerList.clear();
                for (DataSnapshot artSnapshot : dataSnapshot.getChildren()) {

                    Customer customer = artSnapshot.getValue(Customer.class);
                    CustomerList.add(customer);
                }
                CustomerListAdapter adpter = new CustomerListAdapter(MainActivity.this, CustomerList);
                listvieware.setAdapter(adpter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //
            }
        };
        query.addValueEventListener(eventListener);
    }


    public void AddNewCustomer() {

        String id = databaseCustomer.push().getKey();
        Customer aa = new Customer("1", "fares", "01762958", "KL", "asknder");
        databaseCustomer.child(id).setValue(aa);
        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();

    }
    public void AddNewL() {
        DatabaseReference databaseCustomer1 = FirebaseDatabase.getInstance().getReference("laundromat");
        String id = databaseCustomer1.push().getKey();
        laundromat aa = new laundromat("11","dd","dd",5,4,"dd","dd","dd","dd","ds");
        databaseCustomer1.child(id).setValue(aa);
        Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();

    }
}
