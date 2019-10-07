package example.android.com.projet.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "client")
data class Client(
    @PrimaryKey
    @ColumnInfo(name="nss")
    var nss:String,
    @ColumnInfo(name="nom")
    var nom:String="",
    @ColumnInfo(name="prenom")
    var prenom:String="",
    @ColumnInfo(name="adresse_client")
    var adresse_client:String?="",
    @ColumnInfo(name="num_tel_client")
    var num_tel_client:String?="",
    @ColumnInfo(name="motdepasse")
    var motdepasse:String?="",
    @ColumnInfo(name="log")
    var log:Int)