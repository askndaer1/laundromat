package com.laundromat.codesoft.laundromat;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CodeSoft on 7/4/2017.
 */

public class CustomerListAdapter extends ArrayAdapter<Customer> {
    private Activity context;
    private List<Customer>  CustomerList;

    public CustomerListAdapter(Activity context, List<Customer>  CustomerList){
        super(context,R.layout.listlayout, CustomerList);
        this.context=context;
        this. CustomerList= CustomerList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View ListViewItem = inflater.inflate(R.layout.listlayout,null,true);
        TextView textViewName = (TextView) ListViewItem.findViewById(R.id.textView200);
        TextView textViewGa = (TextView) ListViewItem.findViewById(R.id.textView00);

        Customer customer =  CustomerList.get(position);
        textViewName.setText(customer.getCustomerName());
        textViewGa.setText(customer.getCustomerPhone());
        return ListViewItem;
    }
}
