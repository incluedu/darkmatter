package net.lustenauer.darkmatter.screen

import com.badlogic.gdx.graphics.g2d.Batch
import ktx.app.KtxScreen
import net.lustenauer.darkmatter.DarkMatter

abstract class DarkMatterScreen(
        val game: DarkMatter,
        val batch: Batch = game.batch
) : KtxScreen