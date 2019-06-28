package org.androidnetworkingexample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //We call the JSON we created from jsonstore.io with the postURL parameter.
        sendGetRequest("https://www.jsonstore.io/10afead37314c082fd833a0496a8345871077d9d84b640d78e009888ceefc266/city/1")
        recylerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL,false);

    }

    fun sendGetRequest(postURL: String)
    {
        // There are 4 different Priorty for requests, you can choose according to your speed preference. I chose Medium.
        AndroidNetworking.get(postURL)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {

                // This Response Listener Function

                  val JsonObjectResult = response.getJSONObject("result");
                    val JsonArrayCities =JsonObjectResult.getJSONArray("cities");
                    val citiesMutable = mutableListOf(

                        Cities("İstanbul","34","https://www.ibb.istanbul/Uploads/2016/12/basin_materyalleri_ibb_logo.jpg")
                    )


                    for (i in 0..JsonArrayCities!!.length() - 1) {

                        val cityName = JsonArrayCities.getJSONObject(i).getString("cityName");
                        val plateCode=JsonArrayCities.getJSONObject(i).getString("plateCode");
                        val imageUrl=JsonArrayCities.getJSONObject(i).getString("cityImage");
                        citiesMutable.add(i+1, Cities(cityName,plateCode,imageUrl));

                    }

                    recylerView.adapter = CityAdapter(citiesMutable)
                    Log.i("response",response.toString());

                }

                override fun onError(error: ANError) {
                  //  If you encounter an error, it falls into this function


                    Log.e("sendGetRequest Error",error.errorBody);


                }

            })




    }






}
