package com.example.maptiler

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView

class MainActivity : AppCompatActivity() {
    private lateinit var mapView: MapView
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val key = "XJSkBtJbUceuoVSHYlNl"

        val mapId = "c3a6d243-2fec-4ae0-ab94-7c7db007ad7d"

        val styleUrl = "https://api.maptiler.com/maps/$mapId/style.json?key=$key"

        Mapbox.getInstance(this)

        val inflater = LayoutInflater.from(this)
        val rootView = inflater.inflate(R.layout.activity_main, null)
        setContentView(rootView)
//        button = rootView.findViewById(R.id.button2)
//        button.setOnClickListener(View.OnClickListener(){
//            val intent = Intent(this, MainPage::class.java)
//            startActivity(intent)
//        })

        mapView = rootView.findViewById(R.id.mapView)
        mapView.getMapAsync { map ->
            map.setStyle(styleUrl)
            map.cameraPosition = CameraPosition.Builder().target(LatLng(0.0,0.0)).zoom(1.0).build()
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    public fun startNewActivity(view: View) {
        val intent = Intent(this, MainPage::class.java)
        startActivity(intent)
    }
    
    private fun getNewsData() {
        val url = "https://api.prodamgaraj.ru/travel/RU/tours?size=5&page=0";
        val queue = Volley.newRequestQueue(this)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                result -> Log.d("Result", "Result: $result")
            },
            {
                error -> Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}