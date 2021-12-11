package com.example.sakolaapp.functional.adapters.recycleradapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sakolaapp.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng

import com.google.android.gms.maps.model.MarkerOptions




class RecyclerDetalhePedidos: RecyclerView.Adapter<RecyclerView.ViewHolder>(){


    class viewHolder(itemView: View): RecyclerView.ViewHolder(itemView), OnMapReadyCallback {

        private val layout: View = itemView
        private val mapView: MapView = layout.findViewById(R.id.map)
        private lateinit var map: GoogleMap
        private lateinit var latLng: LatLng

        init {
            with(mapView) {
                // Initialise the MapView
                onCreate(null)
                // Set the map ready callback to receive the GoogleMap object
                getMapAsync(this@viewHolder)
            }
        }

        private fun setMapLocation() {
            if (!::map.isInitialized) return
            with(map) {
                moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f))
                addMarker(MarkerOptions().position(latLng))
                mapType = GoogleMap.MAP_TYPE_NORMAL
                setOnMapClickListener {
                    Toast.makeText(itemView.context, "Clicked on",
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        val mapview: View? = itemView.findViewById(R.id.map)
        override fun onMapReady(p0: GoogleMap) {
            MapsInitializer.initialize(itemView.context)
            // If map is not initialised properly
            map = p0 ?: return
            setMapLocation()
        }

        fun bindView(position: Int) {
            listLocations[position].let {
                latLng = it.second
                mapView.tag = this

                setMapLocation()
            }

            fun clearView() {
                with(map) {
                    // Clear the map and free up resources by changing the map type to none
                    clear()
                    mapType = GoogleMap.MAP_TYPE_NONE
                }
            }
        }

        private val listLocations: List<Pair<String, LatLng>> = listOf(
            Pair("Cape Town", LatLng(-33.920455, 18.466941)),
            Pair("Beijing", LatLng(39.937795, 116.387224)),
            Pair("Bern", LatLng(46.948020, 7.448206)),
            Pair("Breda", LatLng(51.589256, 4.774396)),
            Pair("Brussels", LatLng(50.854509, 4.376678)),
            Pair("Copenhagen", LatLng(55.679423, 12.577114)),
            Pair("Hannover", LatLng(52.372026, 9.735672)),
            Pair("Helsinki", LatLng(60.169653, 24.939480)),
            Pair("Hong Kong", LatLng(22.325862, 114.165532)),
            Pair("Istanbul", LatLng(41.034435, 28.977556)),
            Pair("Johannesburg", LatLng(-26.202886, 28.039753)),
            Pair("Lisbon", LatLng(38.707163, -9.135517)),
            Pair("London", LatLng(51.500208, -0.126729)),
            Pair("Madrid", LatLng(40.420006, -3.709924)),
            Pair("Mexico City", LatLng(19.427050, -99.127571)),
            Pair("Moscow", LatLng(55.750449, 37.621136)),
            Pair("New York", LatLng(40.750580, -73.993584)),
            Pair("Oslo", LatLng(59.910761, 10.749092)),
            Pair("Paris", LatLng(48.859972, 2.340260)),
            Pair("Prague", LatLng(50.087811, 14.420460)),
            Pair("Rio de Janeiro", LatLng(-22.90187, -43.232437)),
            Pair("Rome", LatLng(41.889998, 12.500162)),
            Pair("Sao Paolo", LatLng(-22.863878, -43.244097)),
            Pair("Seoul", LatLng(37.560908, 126.987705)),
            Pair("Stockholm", LatLng(59.330650, 18.067360)),
            Pair("Sydney", LatLng(-33.873651, 151.2068896)),
            Pair("Taipei", LatLng(25.022112, 121.478019)),
            Pair("Tokyo", LatLng(35.670267, 139.769955)),
            Pair("Tulsa Oklahoma", LatLng(36.149777, -95.993398)),
            Pair("Vaduz", LatLng(47.141076, 9.521482)),
            Pair("Vienna", LatLng(48.209206, 16.372778)),
            Pair("Warsaw", LatLng(52.235474, 21.004057)),
            Pair("Wellington", LatLng(-41.286480, 174.776217)),
            Pair("Winnipeg", LatLng(49.875832, -97.150726))
        )

    }

    class viewHolder2(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detalhe_pedido_perfil_cliente, parent, false)
        val view2 = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_produtos_layout, parent, false)

        if(viewType == 0){
            return viewHolder(view)
        }else{
            return viewHolder2(view2)
        }
    }

    override fun getItemViewType(position: Int): Int {
        if(position > 0){
            return 1
        }else{
            return 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType){
            0 -> {
                val viewholder: viewHolder = holder as viewHolder
                viewholder.bindView(position)
            }
            1 -> {}
            else -> {}
        }
    }

    override fun getItemCount(): Int = 10


}