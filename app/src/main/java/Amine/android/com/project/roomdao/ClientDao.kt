package Amine.android.com.project.roomdao
import androidx.room.*
import Amine.android.com.project.entity.Client
@Dao
interface ClientDao {

    @Query("select * from client")
    fun getClients():List<Client>

    @Query("select * from client where nss=:nss")
    fun getClientById(nss:Int):Client

    @Insert
    fun addClients(clients:List<Client>)

    @Update
    fun updateClient(client: Client)
}
