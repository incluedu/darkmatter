package net.lustenauer.darkmatter.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import net.lustenauer.darkmatter.DarkMatter

/** Launches the desktop (LWJGL3) application.  */
object Lwjgl3Launcher {
    @JvmStatic
    fun main(args: Array<String>) {
        Lwjgl3Application(DarkMatter(), Lwjgl3ApplicationConfiguration().apply {
            setTitle("Dark Matter")
            setWindowedMode(9 * 32, 16 * 32)
            setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
        })
    }
}