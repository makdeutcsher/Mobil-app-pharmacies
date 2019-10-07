package example.android.com.projet



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import example.android.com.projet.adapter.PharmacieAdapter
import example.android.com.projet.entity.Pharmacie
import example.android.com.projet.viewmodel.PharmacieModel
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.support.v4.intentFor


class MainFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_main, container, false)


        }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // View Model instance
        val pharmacieModel = ViewModelProviders.of(activity!!).get(PharmacieModel::class.java)
        // If the list of cities is null, load the list from DB
        if (pharmacieModel.pharmacies==null) {
            pharmacieModel.loadData(activity!!)
        }
        else {
            // After the rotation of the screen, use cities of the ViewModel instance
            listpharmacies.adapter = PharmacieAdapter(activity!!, pharmacieModel.pharmacies!!)
        }


        listpharmacies.setOnItemClickListener { adapterView, view, i, l ->
            val pharmacie = (adapterView.getItemAtPosition(i) as Pharmacie)
            var bundle = bundleOf("id" to pharmacie.id_pharmacie)
            view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
        }

        imageButton.setOnClickListener() {
            val ville =editText.text.toString()
            val pharmacieModel = ViewModelProviders.of(activity!!).get(PharmacieModel::class.java)
            // If the list of cities is null, load the list from DB
           /* if (pharmacieModel.pharmacies==null) { }*/
                pharmacieModel.loadDataFilter(activity!!,ville)

          /*  else {
                // After the rotation of the screen, use cities of the ViewModel instance
                listcities.adapter = PharmacieAdapter(activity!!, pharmacieModel.pharmacies!!)
            }*/


            listpharmacies.setOnItemClickListener { adapterView, view, i, l ->
                val pharmacie = (adapterView.getItemAtPosition(i) as Pharmacie)
                var bundle = bundleOf("id" to pharmacie.id_pharmacie)
                view.findNavController().navigate(R.id.action_mainFragment_to_detailFragment,bundle)
            }
        }

        imageButton2.setOnClickListener() {
            startActivity(intentFor<MapsActivityCurrentPlace>())
        }
    }


}


