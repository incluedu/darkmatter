package net.lustenauer.darkmatter.ecs.system

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.GdxRuntimeException
import ktx.ashley.allOf
import ktx.ashley.get
import ktx.log.debug
import ktx.log.error
import ktx.log.logger
import net.lustenauer.darkmatter.ecs.component.Animation2D
import net.lustenauer.darkmatter.ecs.component.AnimationComponent
import net.lustenauer.darkmatter.ecs.component.AnimationType
import net.lustenauer.darkmatter.ecs.component.GraphicComponent
import java.util.*

private val LOG = logger<AnimationSystem>()

class AnimationSystem(
        private val atlas: TextureAtlas
) : IteratingSystem(allOf(AnimationComponent::class, GraphicComponent::class).get()), EntityListener {
    private val animationCache = EnumMap<AnimationType, Animation2D>(AnimationType::class.java)

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(family, this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun entityRemoved(entity: Entity) = Unit

    override fun entityAdded(entity: Entity) {
        entity[AnimationComponent.mapper]?.let {
            it.animation = getAnimation(it.type)
            val frame = it.animation.getKeyFrame(it.stateTime)
            entity[GraphicComponent.mapper]?.setSpriteRegion(frame)
        }
    }


    private fun getAnimation(type: AnimationType): Animation2D {
        var animation = animationCache[type]
        if (animation == null) {
            var regions = atlas.findRegions(type.atlasKey)
            if (regions.isEmpty) {
                LOG.error { "No regions found for ${type.atlasKey}" }
                regions = atlas.findRegions("error")
                if (regions.isEmpty) {
                    throw GdxRuntimeException("There is no error region in the atlas")
                }
            } else {
                LOG.debug { "Adding animation of type $type with ${regions.size} " }
            }
            animation = Animation2D(type, regions, type.playMode, type.speedRate)
            animationCache[type] = animation
        }
        return animation
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val animationComponent = entity[AnimationComponent.mapper]
        require(animationComponent != null) { "Entity |entity| must have a AnimationComponent. entity=$entity" }
        val graphic = entity[GraphicComponent.mapper]
        require(graphic != null) { { "Entity |entity| must have a GraphicComponent. entity=$entity" } }

        if (animationComponent.type == AnimationType.NONE) {
            LOG.error { "No type specified for animation component $animationComponent for |entiy| $entity" }
        }

        if (animationComponent.type == animationComponent.animation.type) {
            // animation is correctly set -> update it
            animationComponent.stateTime += deltaTime
        } else {
            // change animation
            animationComponent.stateTime = 0f
            animationComponent.animation = getAnimation(animationComponent.type)
        }

        val frame = animationComponent.animation.getKeyFrame(animationComponent.stateTime)
        graphic.setSpriteRegion(frame)
    }

}
