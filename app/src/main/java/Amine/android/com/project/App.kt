package Amine.android.com.project

import android.app.Application
import Amine.android.com.project.roomdatabase.RoomService

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        RoomService.context = applicationContext
    }
}