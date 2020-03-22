package de.wirvsvirus.abee.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.input.input
import com.afollestad.materialdialogs.list.listItems
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import de.wirvsvirus.abee.DetailActivity
import de.wirvsvirus.abee.R
import de.wirvsvirus.abee.data.MockData

class MapFragment : Fragment() {
    var mapFragment: SupportMapFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_map, container, false)
        mapFragment =
            childFragmentManager.findFragmentById(R.id.frg) as SupportMapFragment? //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            val googlePlex = CameraPosition.builder()
                .target(LatLng(52.6127434, 13.390723))
                .zoom(15f)
                .bearing(0f)
                .tilt(45f)
                .build()
            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null)

            mMap.setOnInfoWindowClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                startActivity(intent)
            }
            mMap.clear() //clear old markers

        }
        val filter: AppCompatImageView = rootView.findViewById(R.id.filter_button)
        filter.setOnClickListener {
            MaterialDialog(context!!).show {
                title(text="Suche")
                input(allowEmpty = true, hint = "Stadt") { dialog, text ->

                }
                listItemsMultiChoice(items = listOf("Angebot", "Bedarf")) { dialog, index, text ->  }
                //listItemsMultiChoice(items= MockData.skillset) { dialog, indices, items -> }
                positiveButton(text="Suchen"){
                    mapFragment!!.getMapAsync {mMap ->
                        val googlePlex = CameraPosition.builder()
                            .target(LatLng(52.5127434, 13.390723))
                            .zoom(15f)
                            .bearing(0f)
                            .tilt(45f)
                            .build()
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null)
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5127434, 13.3907238))
                                .title("Pflegehelfer Verein")
                                .snippet("BEDARF (8)")
                        )
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5107434, 13.3900038))
                                .title("Wörstel Bau GmbH")
                                .snippet("ANGEBOT (23)")
                        )
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5147434, 13.3987238))
                                .title("Pflegehelfer Verein")
                                .snippet("BEDARF (8)")
                        )
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5137434, 13.3967238))
                                .title("Pflegehelfer Verein")
                                .snippet("BEDARF (8)")
                        )
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5131906, 13.3923761))
                                .title("Pflegehelfer Verein")
                                .snippet("BEDARF (8)")
                        )
                        mMap.addMarker(
                            MarkerOptions()
                                .position(LatLng(52.5132044, 13.3896392))
                                .title("Pflegehelfer Verein")
                                .snippet("BEDARF (8)")
                        )
                    }
                }
            }
        }
        return rootView
    }
}