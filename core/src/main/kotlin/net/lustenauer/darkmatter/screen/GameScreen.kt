package net.lustenauer.darkmatter.screen

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import ktx.ashley.entity
import ktx.ashley.with
import ktx.log.Logger
import ktx.log.debug
import ktx.log.logger
import net.lustenauer.darkmatter.DarkMatter
import net.lustenauer.darkmatter.V_HEIGHT
import net.lustenauer.darkmatter.V_WIDTH
import net.lustenauer.darkmatter.ecs.component.*
import net.lustenauer.darkmatter.ecs.system.DAMAGE_AREA_HEIGHT
import kotlin.math.min

private val LOG: Logger = logger<GameScreen>()
private const val MAX_DELTA_TIME = 1 / 20f

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

        engine.entity {
            with<TransformComponent> {
                size.set(
                        V_WIDTH.toFloat(),
                        DAMAGE_AREA_HEIGHT
                )
            }
            with<AnimationComponent> { type = AnimationType.DARK_MATTER }
            with<GraphicComponent>()
        }
    }

    override fun render(delta: Float) {
        (game.batch as SpriteBatch).renderCalls = 0
        engine.update(min(MAX_DELTA_TIME, delta))
        LOG.debug { "Rendercalls := ${(game.batch as SpriteBatch).renderCalls}" }
    }


}