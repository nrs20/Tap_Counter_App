package com.example.tap_counter_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.widget.Button
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
class MainActivity() : AppCompatActivity(), Parcelable {
    var counter = 0

    constructor(parcel: Parcel) : this() {
        counter = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(counter)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MainActivity> {
        override fun createFromParcel(parcel: Parcel): MainActivity {
            return MainActivity(parcel)
        }

        override fun newArray(size: Int): Array<MainActivity?> {
            return arrayOfNulls(size)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Grabbing text field that displays the counter
        val textView = findViewById<TextView>(R.id.textView)

        //button for reaching counter >= 100
        val upgradeButton = findViewById<Button>(R.id.upgradeBtn)

        //find button from activity_main.xml
        val button = findViewById<Button>(R.id.button)
        //set a listener for the button:
        button.setOnClickListener {
            Log.v("MainActivity", "Button Clicked")
           // Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            counter++
            //Update the text field with the incremented value
            textView.text = counter.toString()

            if (counter >= 100) {

                // Show upgrade button and set onClickListener
                upgradeButton.visibility = View.VISIBLE
                upgradeButton.setOnClickListener {
                    //button.text = "Add 2"

                    // Update original button to add 2 instead of `
                    button.setOnClickListener {
                        counter += 2
                        textView.text = counter.toString()
                    }

                    // Hide upgrade button again
                    upgradeButton.visibility = View.INVISIBLE
                }

            }

        }
    }
}
