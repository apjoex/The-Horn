package com.yahya.thehorn.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.yahya.thehorn.R
import com.yahya.thehorn.models.NumberData
import kotlinx.android.synthetic.main.activity_numbers.*
import kotlinx.android.synthetic.main.layout_number_item.view.*

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
            if (numbers.isNotEmpty()){
                progressBar.visibility = View.GONE
                contentList.layoutManager = LinearLayoutManager(this)
                contentList.adapter = NumbersAdapter(numbers.reversed())
            }

        }
    }


    override fun onOptionsItemSelected(item: MenuItem?) = super.onOptionsItemSelected(item).also {
        when (item?.itemId){
            android.R.id.home -> finish()
        }
    }

}

class NumbersAdapter(private val numbers: List<NumberData>): RecyclerView.Adapter<NumbersVH>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = NumbersVH(LayoutInflater.from(parent.context).inflate(R.layout.layout_number_item, parent, false))

    override fun getItemCount() = numbers.size

    override fun onBindViewHolder(holder: NumbersVH, position: Int) {
       holder.bind(numbers[position])
    }

}

class NumbersVH(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(number: NumberData) {
        itemView.numberDisplay.text = "${number.number}"
        itemView.numberTranslation.text = number.translation
    }

}
