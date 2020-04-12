package com.example.studentfirebasedb

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

   // private  val TAG = "MainActivity"

    private lateinit var stuName: EditText
    private lateinit var stuAdmission: EditText
    private lateinit var stuAge: EditText
    private lateinit var stuEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stuName=findViewById(R.id.edt_name)
        stuAdmission=findViewById(R.id.edt_admission)
        stuAge=findViewById(R.id.edt_age)
        stuEmail=findViewById(R.id.edt_email)

        submit_info.setOnClickListener {
            saveInfo()
        }
    }

    private fun saveInfo() {
        val name = stuName.text.toString().trim()
        val admission = stuAdmission.text.toString()
        val age = stuAge.text.toString()
        val email = stuEmail.text.toString()

        if (name.isEmpty() || admission.isEmpty() || age.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please enter all fields", Toast.LENGTH_LONG).show()
        }
        //  val database : FirebaseDatabase = FirebaseDatabase.getInstance()
        // val myRef :DatabaseReference = database.getReference("Student")
        val myRef = FirebaseDatabase.getInstance().getReference("students")

        val stuId = myRef.push().key
        val student = stuId?.let { Student(it, name, admission, age, email) }
        if (stuId != null) {
            myRef.child(stuId).setValue(student).addOnCompleteListener {
                Toast.makeText(this, "Student details are saved successfully ", Toast.LENGTH_SHORT)
                    .show()
            }
        }


    }

    }
