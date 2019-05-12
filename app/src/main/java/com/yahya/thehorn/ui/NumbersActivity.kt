package com.yahya.thehorn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.google.firebase.firestore.FirebaseFirestore
import com.yahya.thehorn.R
import com.yahya.thehorn.models.NumberData

class NumbersActivity : AppCompatActivity() {

    private val numbersCollectionRef = FirebaseFirestore.getInstance().collection("numbers")
    var numbers =  mutableListOf<NumberData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Numbers"

        fetchNumbers()
    }

    private fun fetchNumbers() {
        numbersCollectionRef.get().addOnCompleteListener {
            it.result?.documents?.forEach { snapshot ->
                numbers.add(snapshot.toObject(NumberData::class.java)!!)
                return@forEach
            }
            println(numbers)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem?) = super.onOptionsItemSelected(item).also {
        when (item?.itemId){
            android.R.id.home -> finish()
        }
    }

}
