package net.lustenauer.darkmatter.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.MathUtils
import ktx.ashley.entity
import ktx.ashley.with
import ktx.log.Logger
import ktx.log.debug
import ktx.log.logger
import net.lustenauer.darkmatter.DarkMatter
import net.lustenauer.darkmatter.ecs.component.FacingComponent
import net.lustenauer.darkmatter.ecs.component.GraphicComponent
import net.lustenauer.darkmatter.ecs.component.PlayerComponent
import net.lustenauer.darkmatter.ecs.component.TransformComponent

private val LOG: Logger = logger<GameScreen>()

class GameScreen(game: DarkMatter) : DarkMatterScreen(game) {
    override fun show() {
        LOG.debug { "Game screen is shown" }

        engine.entity {
            with<TransformComponent>() {
                position.set(MathUtils.random(0f, 9f), MathUtils.random(0f, 16f), 0f)
            }
            with<GraphicComponent>()
            with<PlayerComponent>()
            with<FacingComponent>()
        }

    }

    override fun render(delta: Float) {
        engine.update(delta)
    }


}