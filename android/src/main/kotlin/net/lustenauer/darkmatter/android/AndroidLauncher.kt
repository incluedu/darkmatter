package net.lustenauer.darkmatter.android

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import net.lustenauer.darkmatter.DarkMatter

/** Launches the Android application.  */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize(DarkMatter(), AndroidApplicationConfiguration().apply {
        })
    }
}