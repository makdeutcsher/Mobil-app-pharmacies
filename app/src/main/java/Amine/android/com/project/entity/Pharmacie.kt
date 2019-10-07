package Amine.android.com.project.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey




@Entity(tableName = "pharmacie")
data class Pharmacie(
    @PrimaryKey
    @ColumnInfo(name="id_pharmacie")
    var id_pharmacie:Int,
    @ColumnInfo(name="nom_pharmacie")
    var nom_pharmacie:String="",
    @ColumnInfo(name="adresse_pharmacie")
    var adresse_pharmacie:String="",
    @ColumnInfo(name="heure_ouverture")
    var heure_ouverture:String="",
    @ColumnInfo(name="heure_fermeture")
    var heure_fermeture:String?="",
    @ColumnInfo(name="num_tel_pharmacie")
    var num_tel_pharmacie:String?="",
    @ColumnInfo(name="convention")
    var convention:String,
    @ColumnInfo(name="lien_fb")
    var lien_fb:String,
    @ColumnInfo(name="lien_localisation")
    var lien_localisation:String,
    @ColumnInfo(name="ville")
    var ville:String,
    @ColumnInfo(name="Garde")
    var Garde:Int,
    @ColumnInfo(name="longitude")
    var longitude:Double,
    @ColumnInfo(name="latitude")
    var latitude:Double)