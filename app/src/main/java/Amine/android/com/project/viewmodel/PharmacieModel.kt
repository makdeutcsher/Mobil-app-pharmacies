package Amine.android.com.project.viewmodel


import android.app.Activity
import androidx.lifecycle.ViewModel
import android.view.View
import Amine.android.com.project.entity.Pharmacie
import Amine.android.com.project.adapter.PharmacieAdapter
import Amine.android.com.project.R
import Amine.android.com.project.retrofit.RetrofitService
import Amine.android.com.project.roomdatabase.RoomService
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_main.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class PharmacieModel:ViewModel() {

    var pharmacie: Pharmacie? = null
    var pharmacies:List<Pharmacie>? = null


    fun loadData(act:Activity) {
        act.progressBar1.visibility = View.VISIBLE
        // Get cities from SQLite DB
        pharmacies = RoomService.appDataBase.getPharmacieDao().getpharmacies()

        if (pharmacies?.size == 0) {
            // If the list of cities is empty, load from server and save them in SQLite DB
            getPharmaciesFromRemote(act)
        } else {
            act.progressBar1.visibility = View.GONE
            act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)
        }
    }

        fun loadGrade(act:Activity) : List<Pharmacie>?{
            //act.progressBar1.visibility = View.VISIBLE
            // Get cities from SQLite DB
            pharmacies = RoomService.appDataBase.getPharmacieDao().getpharmacies()

            if (pharmacies?.size == 0) {
                // If the list of cities is empty, load from server and save them in SQLite DB
                getPharmaciesFromRemoteGrade(act)
            } else {
//                act.progressBar1.visibility = View.GONE
//                act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)

            }
            return pharmacies
    }


      fun loadDataFilter(act:Activity,ville : String) {
        act.progressBar1.visibility = View.VISIBLE
        // Get cities from SQLite DB
        pharmacies = RoomService.appDataBase.getPharmacieDao().getPharmaciesByVille(ville)

        if (pharmacies?.size == 0) {
            // If the list of cities is empty, load from server and save them in SQLite DB
            getPharmaciesFromRemoteFilter(act,ville)
        }
        else {
            act.progressBar1.visibility = View.GONE
            act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)
        }




    }

    private fun getPharmaciesFromRemoteFilter(act: Activity,ville: String) {
        val call = RetrofitService.endpoint.getPharmaciesByVille(ville)
        call.enqueue(object : Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                act.progressBar1.visibility = View.GONE
                if (response?.isSuccessful!!) {
                    pharmacies = response?.body()
                    act.progressBar1.visibility = View.GONE
                    act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)
                    // save cities in SQLite DB

                    RoomService.appDataBase.getPharmacieDao().addPharmacies(pharmacies!!)
                } else {
                    act.toast("Une erreur s'est produite")
                }
            }

            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                act.progressBar1.visibility = View.GONE
                act.toast("Une erreur s'est produite")
            }


        })

    }

    private fun getPharmaciesFromRemote(act:Activity) {
        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object : Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
                act.progressBar1.visibility = View.GONE
                if (response?.isSuccessful!!) {
                    pharmacies = response?.body()
                    act.progressBar1.visibility = View.GONE
                    act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)
                    // save cities in SQLite DB

                    RoomService.appDataBase.getPharmacieDao().addPharmacies(pharmacies!!)
                } else {
                    act.toast("Une erreur s'est produite")
                }
            }

            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
                act.progressBar1.visibility = View.GONE
                act.toast("Une erreur s'est produite")
            }


        })
    }

    private fun getPharmaciesFromRemoteGrade(act:Activity){
        val call = RetrofitService.endpoint.getPharmacies()
        call.enqueue(object : Callback<List<Pharmacie>> {
            override fun onResponse(call: Call<List<Pharmacie>>?, response: Response<List<Pharmacie>>?) {
//                act.progressBar1.visibility = View.GONE
                if (response?.isSuccessful!!) {
                    pharmacies = response?.body()
//                    act.progressBar1.visibility = View.GONE
//                    act.listpharmacies.adapter = PharmacieAdapter(act, pharmacies!!)
                    // save cities in SQLite DB
                    RoomService.appDataBase.getPharmacieDao().addPharmacies(pharmacies!!)
                } else {
                    act.toast("Une erreur s'est produite")
                }
            }

            override fun onFailure(call: Call<List<Pharmacie>>?, t: Throwable?) {
//                act.progressBar1.visibility = View.GONE
                act.toast("Une erreur s'est produite")
            }


        })
    }

    fun loadDetail(act:Activity,idpharmacie:Int) {
        act.progressBar2.visibility = View.VISIBLE
        // load pharmacie detail from SQLite DB
        this.pharmacie = RoomService.appDataBase.getPharmacieDao().getPharmacieById(idpharmacie)
        if(this.pharmacie?.id_pharmacie==null) {
            // if the pharmacie details don't exist, load the details from server and update SQLite DB
            loadDetailFromRemote(act,idpharmacie)
        }
        else {
            act.progressBar2.visibility = View.GONE
            displayDatail(act, this.pharmacie!!)
        }

    }

    private fun loadDetailFromRemote(act:Activity,idPhrmacie:Int) {
        val call = RetrofitService.endpoint.getDetailPharmacie(idPhrmacie)
        call.enqueue(object : Callback<Pharmacie> {
            override fun onResponse(call: Call<Pharmacie>?, response: Response<Pharmacie>?) {
                act.progressBar2.visibility = View.GONE
                if(response?.isSuccessful!!) {
                    var pharmacieDetail = response?.body()
                    pharmacieDetail = pharmacie!!.copy(
                        id_pharmacie = pharmacieDetail!!.id_pharmacie.toInt(),
                        convention =     pharmacieDetail?.convention.toString(),
                        num_tel_pharmacie =      pharmacieDetail?.num_tel_pharmacie,
                        adresse_pharmacie =   pharmacieDetail?.adresse_pharmacie.toString(),
                        heure_ouverture = pharmacieDetail?.heure_ouverture.toString(),
                      heure_fermeture =   pharmacieDetail?.heure_fermeture.toString(),
                        lien_fb=pharmacieDetail?.lien_fb.toString(),
                        lien_localisation= pharmacieDetail?.lien_localisation.toString(),
                        ville = pharmacieDetail?.ville.toString())
                    displayDatail(act,                    pharmacieDetail)
                    // update pharmacie in the SQLite DB to support offline mode
                    RoomService.appDataBase.getPharmacieDao().updatePharmacie(pharmacieDetail)
                    // update ViewModel
                    this@PharmacieModel.pharmacie = pharmacieDetail

                }
                else {
                    act.toast("Une erreur s'est produite")

                }


            }

            override fun onFailure(call: Call<Pharmacie>?, t: Throwable?) {
                act.progressBar2.visibility = View.GONE
                act.toast("Une erreur s'est produite")

            }
        })
    }

    fun displayDatail(act: Activity,pharmacie: Pharmacie) {


        act.adressepharmacie.text=act.getString(R.string.adressepharmacie)+pharmacie.adresse_pharmacie
        act.convention.text=act.getString(R.string.convention)+pharmacie.convention
        act.numtelpharmacie.text=act.getString(R.string.numtelpharmacie)+pharmacie.num_tel_pharmacie
        act.ville.text=act.getString(R.string.ville)+pharmacie.ville
        act.heureouverture.text  = act.getString(R.string.heureouverture)+pharmacie.heure_ouverture
        act.heurefermeture.text = act.getString(R.string.heurefermeture)+pharmacie.heure_fermeture
        act.lienlocalisation.text = act.getString(R.string.localisation)+pharmacie.lien_localisation
        act.lienfb.text = act.getString(R.string.lienfb)+pharmacie.lien_fb
    }


}