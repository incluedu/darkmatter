package net.lustenauer.darkmatter

import com.badlogic.gdx.Application.LOG_DEBUG
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.app.KtxGame
import ktx.log.Logger
import ktx.log.debug
import ktx.log.logger
import net.lustenauer.darkmatter.screen.DarkMatterScreen
import net.lustenauer.darkmatter.screen.FirstScreen
import net.lustenauer.darkmatter.screen.GameScreen
import net.lustenauer.darkmatter.screen.SecondScreen

private val LOG: Logger = logger<DarkMatter>()

class DarkMatter : KtxGame<DarkMatterScreen>() {
    val batch: Batch by lazy { SpriteBatch() }

    override fun create() {
        Gdx.app.logLevel = LOG_DEBUG
        LOG.debug { "Create game instance" }
        addScreen(FirstScreen(this))
        addScreen(SecondScreen(this))
        addScreen(GameScreen(this))
        setScreen<GameScreen>()
    }

    override fun dispose() {
        batch.dispose()
    }
}