package Amine.android.com.project.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey


@Entity(tableName = "commande",
    foreignKeys = arrayOf(
        ForeignKey(entity =
        Client::class, parentColumns = arrayOf("nss"),
            childColumns = arrayOf("nss"),
            onDelete = ForeignKey.CASCADE),
        ForeignKey(entity =
        Pharmacie::class, parentColumns = arrayOf("id_pharmacie"),
            childColumns = arrayOf("id_pharmacie"),
            onDelete = ForeignKey.CASCADE)
    ))
data class Commande(
    @ColumnInfo(name="etat_commande")
    var etat_commande:String="",
    @ColumnInfo(name="nss")
    var nss:String="",
    @ColumnInfo(name="id_pharmacie")
    var id_pharmacie:Int?,
    @ColumnInfo(name="photo")
    var photo:String?="")