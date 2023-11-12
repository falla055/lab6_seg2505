package com.example.lab6sql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView idView;
    EditText productBox;
    EditText skuBox;

    Button add;
    Button find;
    Button delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idView = (TextView) findViewById(R.id.productID);
        productBox = (EditText) findViewById(R.id.productName);
        skuBox = (EditText) findViewById(R.id.productSku);
        add = findViewById(R.id.add);
        find = findViewById(R.id.find);
        delete = findViewById(R.id.delete);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newProduct(v);
            }
        });
    }



    public void newProduct (View view) {

        int sku = Integer.parseInt(skuBox.getText().toString());

        Product product = new Product(productBox.getText().toString(), sku);

        // actually add the product in the database

        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addProduct(product);

        productBox.setText("");

        skuBox.setText("");
    }


    public void lookupProduct (View view) {

        // call findProduct in lookupProduct method
        Product product = null;
        MyDBHandler dbHandler = new MyDBHandler(this);
        product = dbHandler.findProduct(productBox.getText().toString());

        if (product != null) {
            idView.setText(String.valueOf(product.getID()));
            skuBox.setText(String.valueOf(product.getSku()));
        } else {
            idView.setText("No Match Found");
        }
    }


    public void removeProduct (View view) {

        //Completing the removeProduct method in the main
        boolean result = false;
        MyDBHandler dbHandler = new MyDBHandler(this);
        result = dbHandler.deleteProduct(productBox.getText().toString());

        if (result) {
            idView.setText("Record Deleted");
            productBox.setText("");
            skuBox.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}