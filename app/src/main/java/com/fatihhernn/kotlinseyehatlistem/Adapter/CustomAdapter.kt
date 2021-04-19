package com.fatihhernn.kotlinseyehatlistem.Adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.fatihhernn.kotlinseyehatlistem.Models.Place
import com.fatihhernn.kotlinseyehatlistem.R
import kotlinx.android.synthetic.main.custom_list_row.view.*


//customAdapter : listView, listView'da kullanılacak custom_list_row.xml ve MainActivity'i birbirine bağlayacak bir sınıf çağırıyoruz
class CustomAdapter(private val placeList:ArrayList<Place>, private val context: Activity) :
        ArrayAdapter<Place>(context, R.layout.custom_list_row, placeList) {

            //context :  hangi activitede çalıştıracağız
            //resources : hangi kaynağı bağlıcaz
            //object : resources içine hangi listeyi koycaz


    //customViewlardan custom_list_row'u buraya değişken olarak çağırabiiliyoruz. İçerisindeki farklı görünümlere ulaşabiliriz yani biz sadece textView oluşturduk ona ulaşacağız
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //layout oluşturup kod tarafında kullanmak istersek Inflatter Kullanıyoruz
        val layoutInflater=context.layoutInflater //ArrayAdapter'te gelen parametrelerin önüne PRIVATE VAL koyalım, burada kullanabiliriz

        //custom view inflate et
        val customView=layoutInflater.inflate(R.layout.custom_list_row,null,true)

        customView.listRowTextView.text=placeList.get(position).address

        return customView
    }

}