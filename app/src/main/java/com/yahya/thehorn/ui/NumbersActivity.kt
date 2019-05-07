package com.yahya.thehorn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.yahya.thehorn.R

class NumbersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_numbers)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Numbers"
    }

    override fun onOptionsItemSelected(item: MenuItem?) = super.onOptionsItemSelected(item).also {
        when (item?.itemId){
            android.R.id.home -> finish()
        }
    }

}
