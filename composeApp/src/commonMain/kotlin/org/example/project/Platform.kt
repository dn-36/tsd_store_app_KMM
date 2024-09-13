package org.example.project

import org.example.project.presentation.feature.authorization.core.SharedPrefsApi
import org.example.project.presentation.feature.authorization.datasource.sharaedprefs.SharedPrefsIImpl
import org.koin.core.module.Module




expect val platformModule: Module

expect val sharedPrefsImpl:SharedPrefsApi