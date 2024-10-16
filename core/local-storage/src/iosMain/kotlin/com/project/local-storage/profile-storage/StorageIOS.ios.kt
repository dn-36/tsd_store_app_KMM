package com.project.`local-storage`.`profile-storage`

import platform.Foundation.NSUserDefaults


 class StorageIos(
     val context:NSUserDefaults = NSUserDefaults.standardUserDefaults
 ) : SharedPrefsApi {
     private val NUMBER:String = "NUMBER"
     private val NAME:String = "NAME"
     private val TOKEN:String = "TOKEN"
   // private val userDefaults = NSUserDefaults.standardUserDefaults

    override suspend fun saveCurrentNumber( value: String) {
        (context as NSUserDefaults).setObject(value, forKey = NUMBER)
    }

    override suspend fun saveCurrentName( value: String) {
        (context as NSUserDefaults).setObject(value, forKey = NAME)
    }

    override suspend fun getCurrentNumber(): String? {
        return  (context as NSUserDefaults).stringForKey(NUMBER)
    }

    override suspend fun getCurrentName(): String? {
        return  (context as NSUserDefaults).stringForKey(NAME)
    }

     override suspend fun saveToken(value: String) {
         (context as NSUserDefaults).setObject(value, forKey = TOKEN)
     }

     override suspend fun getToken(): String? {
         return  (context as NSUserDefaults).stringForKey(TOKEN)
     }
 }
