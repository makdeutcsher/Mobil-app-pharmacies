package example.android.com.projet

import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import example.android.com.projet.entity.Pharmacie
import example.android.com.projet.viewmodel.PharmacieModel
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.bundleOf

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    var pharmacies:List<Pharmacie>? = null
    var gardePharmacies = arrayListOf<Pharmacie>()
    private lateinit var mMap: GoogleMap

    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10 * 1000).toLong()  /* 10 secs */
    private val FASTEST_INTERVAL: Long = 2000 /* 2 sec */

    private var latitude = 0.0
    private var longitude = 0.0

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

//        val pharmacieModel = ViewModelProviders.of(this@MapsActivity).get(PharmacieModel::class.java)
        val garde =getCurrentDay()
        println("current garde"+garde)
//        pharmacies = pharmacieModel.loadDatagarde(this@MapsActivity, garde)
//        println("listpharm"+pharmacies.toString())
//        pharmacies?.forEach {
//            println("it garde"+it.Garde)
//            if (it.Garde==garde){
//                println("i am here!")
//                println("success"+ gardePharmacies.add(it))
//            }
//        }
//        println("length"+ gardePharmacies.size.toString())
//        println("list"+ gardePharmacies.toString())


    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val garde =getCurrentDay()
        var mark:LatLng=LatLng(0.0,0.0)
        pharmacies?.forEach {
            if (it.Garde==garde){
                println("i am here!")
                mark = LatLng(it.latitude, it.longitude)
                mMap.addMarker(MarkerOptions().position(mark).title("Marker in"))
            }
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mark))
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun getCurrentDay(): Int {
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK)
    }

}
