package Amine.android.com.project.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import Amine.android.com.project.R
import Amine.android.com.project.entity.Pharmacie


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
            val villepharma = view?.findViewById<TextView>(R.id.villepharmacie) as TextView
            val phonepharmacie = view?.findViewById<TextView>(R.id.phonepharmacie) as TextView
            viewHolder= ViewHolder(name, villepharma, phonepharmacie)
            view.setTag(viewHolder)
        }
        else {
            viewHolder = view.getTag() as ViewHolder

        }


        viewHolder.nompharmacie.setText(listPharmacies.get(position).nom_pharmacie)
        viewHolder.villepharmacie.setText(listPharmacies.get(position).ville)
        viewHolder.phonepharmacie.setText(listPharmacies.get(position).num_tel_pharmacie)
        return view

}

private data class ViewHolder(var nompharmacie:TextView,var villepharmacie:TextView,var phonepharmacie:TextView)
}

