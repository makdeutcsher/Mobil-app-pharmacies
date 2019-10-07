package example.android.com.projet

import android.app.Application
import example.android.com.projet.roomdatabase.RoomService


class App: Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}