package Amine.android.com.project.roomdao
import androidx.room.*
import Amine.android.com.project.entity.Commande
@Dao
interface CommandeDao {

    @Query("select * from commande")
    fun getCommandes():List<Commande>

    @Query("select * from commande where id_commande=:id_commande")
    fun getCommandeById(id_commande:Int):Commande

    @Insert
    fun addCommande(commandes:List<Commande>)

    @Update
    fun updateCommande(commande: Commande)
}
