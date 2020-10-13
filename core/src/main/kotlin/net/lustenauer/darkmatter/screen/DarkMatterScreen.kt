package net.lustenauer.darkmatter.screen

import com.badlogic.ashley.core.Engine
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.app.KtxScreen
import net.lustenauer.darkmatter.DarkMatter

abstract class DarkMatterScreen(
        val game: DarkMatter,
        val batch: Batch = game.batch,
        val engine: Engine = game.engine,
        val gameViewport: Viewport = game.gameViewport
) : KtxScreen {
    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
    }
}