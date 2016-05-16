package com.example.ashwini.sqlliteapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
     DatabaseHelper myDb;
    EditText editName, editEmail, editMobile, editId;
    Button buttonAdd, buttonAddAll, buttonUpdate, buttonDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //myDb = new DatabaseHelper(MainActivity.this);

        editId = (EditText) findViewById(R.id.editTextId);
        editName = (EditText) findViewById(R.id.editTextName);
        editEmail = (EditText) findViewById(R.id.editTextEMail);
        editMobile = (EditText) findViewById(R.id.editTextMobile);

        buttonAdd = (Button) findViewById(R.id.buttonSubmit);
        buttonAddAll = (Button) findViewById(R.id.buttonAddall);
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);
        buttonDelete = (Button) findViewById(R.id.btnDel);
        AddData();
        viewAll();
        Update();
        Delete();

    }

    public void Delete() {
        buttonDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                boolean isDelete = myDb.deleteData(editId.getText().toString());
                if(isDelete == true)
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data deleted", Toast.LENGTH_SHORT).show();


            }
        });
    }

    public void Update() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(editId.getText().toString(),
                        editName.getText().toString(),
                        editEmail.getText().toString(),
                        editMobile.getText().toString());
                if (isUpdated = true)
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void AddData(){
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editName.getText().toString(),
                        editEmail.getText().toString(),
                        editMobile.getText().toString());
                if (isInserted = true) {
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, ThankyouActivity.class);
                    startActivity(i);
                }
                else
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();

            }
        });

   }

    public void viewAll() {
        buttonAddAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                    if(res.getCount() == 0)
                    {
                        //show message
                        showMessage("Error", "Nothing found");
                        return;
                    }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Email: " + res.getString(2) + "\n");
                    buffer.append("Phone: " + res.getString(3) + "\n\n");
                }
                //show all data
                showMessage("Data", buffer.toString());
                //return;
            }
        });
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
