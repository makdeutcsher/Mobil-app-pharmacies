package example.android.com.projet.roomdao
import androidx.room.*
import example.android.com.projet.entity.Commande
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
