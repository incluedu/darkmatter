package net.lustenauer.darkmatter.screen

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.ashley.entity
import ktx.ashley.with
import ktx.log.Logger
import ktx.log.debug
import ktx.log.logger
import net.lustenauer.darkmatter.DarkMatter
import net.lustenauer.darkmatter.ecs.component.*

private val LOG: Logger = logger<GameScreen>()

class GameScreen(game: DarkMatter) : DarkMatterScreen(game) {
    override fun show() {
        LOG.debug { "Game screen is shown" }

        engine.entity {
            with<TransformComponent>() {
                position.set(4.5f, 8f, 0f)
            }
            with<MoveComponent>()
            with<GraphicComponent>()
            with<PlayerComponent>()
            with<FacingComponent>()
        }
    }

    override fun render(delta: Float) {
        (game.batch as SpriteBatch).renderCalls = 0
        engine.update(delta)
        LOG.debug { "Rendercalls := ${(game.batch as SpriteBatch).renderCalls}" }
    }


}