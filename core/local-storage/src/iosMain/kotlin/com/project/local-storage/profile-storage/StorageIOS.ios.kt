package com.project.`local-storage`.`profile-storage`

import platform.Foundation.NSUserDefaults


 class StorageIos(
     val context:NSUserDefaults = NSUserDefaults.standardUserDefaults
 ) : ProfileValueStorageApi {
   val NUMBER:String = "NUMBER"
   val NAME:String = "NAME"
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
}
