package Amine.android.com.project.roomdatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import Amine.android.com.project.entity.Client
import Amine.android.com.project.entity.Pharmacie
import Amine.android.com.project.roomdao.ClientDao
import Amine.android.com.project.roomdao.PharmacieDao

@Database(entities = arrayOf(Client::class,Pharmacie::class),version = 1)
abstract class AppDataBase: RoomDatabase() {

    abstract fun getClientDao():ClientDao
    abstract fun getPharmacieDao(): PharmacieDao

}