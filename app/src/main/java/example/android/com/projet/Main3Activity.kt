package example.android.com.projet

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import example.android.com.projet.entity.Client
import example.android.com.projet.entity.Commande
import example.android.com.projet.retrofit.RetrofitService
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.activity_main3.*
import okhttp3.MediaType
import org.jetbrains.anko.act
import org.jetbrains.anko.intentFor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import okhttp3.RequestBody
import okhttp3.MultipartBody
import java.io.File
import android.R.id
import android.R.attr.data
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.preference.PreferenceManager
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.room.util.CursorUtil.getColumnIndex
import java.lang.Boolean.FALSE

//import sun.font.LayoutPathImpl.getPath








class Main3Activity : AppCompatActivity() {
    lateinit var imageFile:String
    var PICK_IMAGE_MULTIPLE = 1
    lateinit var imagePath: String
    var myEtatcmd=false

    var imagesPathList: MutableList<String> = arrayListOf()
    fun call_intent_photos() {
        if (Build.VERSION.SDK_INT < 19) {
            var intent = Intent()
            intent.type = "image/*"
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(
                Intent.createChooser(intent, "Select Picture")
                , PICK_IMAGE_MULTIPLE
            )
        } else {
            var intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            intent.addCategory(Intent.CATEGORY_OPENABLE)
            intent.type = "image/*"
            startActivityForResult(intent, PICK_IMAGE_MULTIPLE)
        }

    }

    @SuppressLint("NewApi")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        // When an Image is picked
        if (requestCode == PICK_IMAGE_MULTIPLE && resultCode == Activity.RESULT_OK
            && null != data
        ) {
            if (data.getClipData() != null) {
                var count = data.clipData.itemCount
                for (i in 0..count - 1) {

                    var imageUri: Uri = data.clipData.getItemAt(i).uri
                    getPathFromURI(imageUri)

                }
            } else if (data.getData() != null) {
                var imagePath: String = data.data.path
                Log.e("imagePath", imagePath);
            }

            displayImageData()
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun getPathFromURI(uri: Uri) {
        var path: String = uri.path // uri = any content Uri

        val databaseUri: Uri
        val selection: String?
        val selectionArgs: Array<String>?
        if (path.contains("/document/image:")) { // files selected from "Documents"
            databaseUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            selection = "_id=?"
            selectionArgs = arrayOf(DocumentsContract.getDocumentId(uri).split(":")[1])
        } else { // files selected from all other sources, especially on Samsung devices
            databaseUri = uri
            selection = null
            selectionArgs = null
        }
        try {
            val projection = arrayOf(
                MediaStore.Images.Media.DATA,
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.ORIENTATION,
                MediaStore.Images.Media.DATE_TAKEN
            ) // some example data you can query
            val cursor = contentResolver.query(
                databaseUri,
                projection, selection, selectionArgs, null
            )
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndex(projection[0])
                imagePath = cursor.getString(columnIndex)
                // Log.e("path", imagePath);
                imagesPathList.add(imagePath)
            }
            cursor.close()
        } catch (e: Exception) {
            Log.e("TAG", e.message, e)
        }
    }

    fun displayImageData() {

        for (e in imagesPathList) {

            Log.e("imagePath", e)

//            println("The element is $e")
        }
    }
    fun getRealPathFromURI(contentURI: Uri): String {
        val result: String
        val cursor = contentResolver.query(contentURI, null, null, null, null)
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath()
        } else {
            cursor.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            result = cursor.getString(idx)
            cursor.close()
        }
        return result
    }
    /*     override fun onSaveInstanceState(savedInstanceState: Bundle) {
            super.onSaveInstanceState(savedInstanceState)
            // Save UI state changes to the savedInstanceState.
            // This bundle will be passed to onCreate if the process is
            // killed and restarted.



            savedInstanceState.putBoolean(etatcmd.getVisibility().toString(),false)
            // etc.
        }
        override fun onRestoreInstanceState(savedInstanceState: Bundle) {
            super.onRestoreInstanceState(savedInstanceState)
            // Restore UI state from the savedInstanceState.
            // This bundle has also been passed to onCreate.
            //val myBoolean = savedInstanceState.getBoolean("MyBoolean")
        }
    */
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)


          var token=getSharedPreferences("numtext", Context.MODE_PRIVATE)
        bonjour.text= ("Bonjour ")+ token.getString("loginusername"," ")+(" !!")
        //act.getString(R.string.bonjour)+bonjour.text + act.getString(R.string.exc)
        var idpharmacie = ""
        var nss = token.getString("nss"," ")
        if (intent.hasExtra("idpharmacie")) { // vérifie qu'une valeur est associée à la clé “edittext”
            idpharmacie = intent.getStringExtra("idpharmacie") // on récupère la valeur associée à la clé

        }
        choose.setOnClickListener {
                view ->
            call_intent_photos()

          //intent=Intent(Intent.ACTION_PICK)
            //intent.setType("image/*")
            //startActivityForResult(intent,0)



        }
       /* override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            if (resultCode == RESULT_OK) {

                Toast.makeText(this@Main3Activity,"cannot choose image", Toast.LENGTH_LONG).show()
                val selectedImageURI = intent.getData()
                var imageFile = getRealPathFromURI(selectedImageURI!!)
            }

        }*/


        commande.setOnClickListener {
/*


            val file = File(imagePath)


            val requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file)

// MultipartBody.Part is used to send also the actual file name
            val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

// add another part within the multipart request
            val fullName = RequestBody.create(MediaType.parse("multipart/form-data"), "Your Name")

            val call1=RetrofitService.endpoint.upload( body,fullName)
            call1.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {

                    Toast.makeText(this@Main3Activity,t.toString()+"photo", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful){

                        Toast.makeText(this@Main3Activity,"photo uploader", Toast.LENGTH_LONG).show()
                        startActivity(intentFor<Main5Activity>())

                    }
                    else{
                        Toast.makeText(this@Main3Activity,"erreur", Toast.LENGTH_LONG).show()
                    }
                }
            })*/


            //pass it like this


            var nss =   nss
            var id_pharmacie = idpharmacie.toInt()
            var photo ="/images/pharmacie/tv.png"
            var etat ="En Cours"
            var commande1: Commande
            commande1= Commande(etat_commande = etat,nss=nss,id_pharmacie = id_pharmacie,photo=photo)

            val call= RetrofitService.endpoint.addcommande(commande1)

            call.enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {

                    Toast.makeText(this@Main3Activity,t.toString()+"test", Toast.LENGTH_LONG).show()

                }

                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful){

                        Toast.makeText(this@Main3Activity,"Commande effectuè", Toast.LENGTH_LONG).show()
                        etatcmd.text= ("L etat de votre commande est : ")+ etat +(" !!")
                        etatcmd.setVisibility(View.VISIBLE)

                        //startActivity(intentFor<Main5Activity>())


                    }
                    else{
                        Toast.makeText(this@Main3Activity,"erreur", Toast.LENGTH_LONG).show()
                    }
                }

            })
        }

        logout.setOnClickListener {

            var editor=token.edit()
            editor.putString("loginusername"," ")
            editor.commit()
            var intent=Intent(this,Main5Activity::class.java)
            startActivity(intent)
            finish()
        }
    }




}
