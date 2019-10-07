package example.android.com.projet.roomdatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import example.android.com.projet.entity.Client
import example.android.com.projet.entity.Pharmacie
import example.android.com.projet.roomdao.ClientDao
import example.android.com.projet.roomdao.PharmacieDao

@Database(entities = arrayOf(Client::class,Pharmacie::class),version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getClientDao():ClientDao
    abstract fun getPharmacieDao(): PharmacieDao

}