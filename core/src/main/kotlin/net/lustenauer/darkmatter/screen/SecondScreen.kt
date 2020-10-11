package net.lustenauer.darkmatter.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ktx.log.Logger
import ktx.log.debug
import ktx.log.logger
import net.lustenauer.darkmatter.DarkMatter

private val LOG: Logger = logger<SecondScreen>()

class SecondScreen(game: DarkMatter) : DarkMatterScreen(game) {
    override fun show() {
        LOG.debug { "Second screen is shown" }
    }

    override fun render(delta: Float) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            game.setScreen<FirstScreen>()
        }
    }
}