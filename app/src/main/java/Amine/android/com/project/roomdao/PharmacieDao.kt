package Amine.android.com.project.roomdao
import androidx.room.*
import Amine.android.com.project.entity.Pharmacie
@Dao
interface PharmacieDao {

    @Query("select * from Pharmacie")
    fun getpharmacies():List<Pharmacie>

    @Query("select * from Pharmacie where ville=:ville")
    fun getPharmaciesByVille(ville:String):List<Pharmacie>

    @Query("select * from Pharmacie where id_pharmacie=:id_pharmacie")
    fun getPharmacieById(id_pharmacie:Int):Pharmacie

    @Insert
    fun addPharmacies(pharmacies:List<Pharmacie>)

    @Update
    fun updatePharmacie(pharmacie: Pharmacie)
}
