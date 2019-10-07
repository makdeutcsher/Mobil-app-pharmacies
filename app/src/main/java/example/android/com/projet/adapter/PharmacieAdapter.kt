package example.android.com.projet.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import example.android.com.projet.R
import example.android.com.projet.baseUrl
import example.android.com.projet.entity.Pharmacie
import org.jetbrains.anko.toast




class PharmacieAdapter(_ctx:Context,_listPharmacies:List<Pharmacie>):BaseAdapter() {
    var ctx = _ctx
    val listPharmacies = _listPharmacies


    override fun getItem(p0: Int) = listPharmacies.get(p0)

    override fun getItemId(p0: Int) = listPharmacies.get(p0).hashCode().toLong()

    override fun getCount()= listPharmacies.size

    override fun getView(position: Int, p0: View?, parent: ViewGroup?): View {

        var view = p0
        var viewHolder: ViewHolder
        if(view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.pharmacie_layout,parent,false)

            val name = view?.findViewById<TextView>(R.id.nompharmacie) as TextView
            val numberTourist = view?.findViewById<TextView>(R.id.villepharmacie) as TextView
            viewHolder= ViewHolder(name, numberTourist)
            view.setTag(viewHolder)
        }
        else {
            viewHolder = view.getTag() as ViewHolder

        }


        viewHolder.nompharmacie.setText(listPharmacies.get(position).nom_pharmacie)
        viewHolder.villepharmacie.setText(listPharmacies.get(position).ville)
        return view

}

private data class ViewHolder(var nompharmacie:TextView,var villepharmacie:TextView)
}

