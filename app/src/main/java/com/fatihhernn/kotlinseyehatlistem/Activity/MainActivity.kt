package com.fatihhernn.kotlinseyehatlistem.Activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.fatihhernn.kotlinseyehatlistem.Adapter.CustomAdapter
import com.fatihhernn.kotlinseyehatlistem.MapsActivity
import com.fatihhernn.kotlinseyehatlistem.Models.Place
import com.fatihhernn.kotlinseyehatlistem.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val placesArray=ArrayList<Place>()


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        //add_place bağla
        val menuInflater=menuInflater
        menuInflater.inflate(R.menu.add_place,menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId== R.id.add_place_option){
            val intent= Intent(applicationContext, MapsActivity::class.java)

            //options menüden'den mi mapsActivity açacağız yoksa Listededen mi açacağz
            intent.putExtra("info","new")

            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        //veriyi okuyalım
        try{
            val database = openOrCreateDatabase("Places", Context.MODE_PRIVATE,null)
            val cursor = database.rawQuery("SELECT * FROM places",null)

            val addressIndex = cursor.getColumnIndex("address")
            val latitudeIndex = cursor.getColumnIndex("latitude")
            val longitudeIndex = cursor.getColumnIndex("longitude")

            while(cursor.moveToNext()) {

                val addressFromDatabase = cursor.getString(addressIndex)
                val latitudeFromDatabase = cursor.getDouble(latitudeIndex)
                val longitudeFromDatabase = cursor.getDouble(longitudeIndex)

                val myPlace = Place(
                    addressFromDatabase,
                    latitudeFromDatabase,
                    longitudeFromDatabase
                )

                placesArray.add(myPlace)

            }

            cursor.close()

        }catch (e:Exception){
            e.printStackTrace()
        }

        val customAdapter= CustomAdapter(placesArray,this)
        listView.adapter=customAdapter

        //listeden herhangi bir kayda tıklanmıi ise
        listView.setOnItemClickListener { parent, view, position, id ->
           val intent=Intent(this@MainActivity, MapsActivity::class.java)
            intent.putExtra("info","old")//eski kayıtla gidiyorum

            intent.putExtra("selectedPlace",placesArray.get(position))

            startActivity(intent)
        }
    }




}