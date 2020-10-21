package net.lustenauer.darkmatter.ecs.system

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.core.EntityListener
import com.badlogic.ashley.systems.IteratingSystem
import ktx.ashley.addComponent
import ktx.ashley.allOf
import ktx.ashley.get
import net.lustenauer.darkmatter.ecs.component.AttachComponent
import net.lustenauer.darkmatter.ecs.component.GraphicComponent
import net.lustenauer.darkmatter.ecs.component.RemoveComponent
import net.lustenauer.darkmatter.ecs.component.TransformComponent

class AttachSystem : EntityListener, IteratingSystem(allOf(AttachComponent::class, TransformComponent::class, GraphicComponent::class).get()) {
    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        engine.addEntityListener(this)
    }

    override fun removedFromEngine(engine: Engine) {
        super.removedFromEngine(engine)
        engine.removeEntityListener(this)
    }

    override fun entityAdded(entity: Entity) = Unit

    override fun entityRemoved(removedEntity: Entity) {
        entities.forEach { entity ->
            entity[AttachComponent.mapper]?.let { attach ->
                if (attach.entity == removedEntity) {
                    entity.addComponent<RemoveComponent>(engine)
                }
            }
        }
    }

    override fun processEntity(entity: Entity, deltaTime: Float) {
        val attach = entity[AttachComponent.mapper]
        require(attach != null) { { "Entity |entity| must have a AttachComponent. entity=$entity" } }
        val graphic = entity[GraphicComponent.mapper]
        require(graphic != null) { { "Entity |entity| must have a GraphicComponent. entity=$entity" } }
        val transform = entity[TransformComponent.mapper]
        require(transform != null) { { "Entity |entity| must have a GraphicComponent. entity=$entity" } }

        // update position
        attach.entity[TransformComponent.mapper]?.let { attachTransfrom ->
            transform.interpolatedPosition.set(
                    attachTransfrom.interpolatedPosition.x + attach.offset.x,
                    attachTransfrom.interpolatedPosition.y + attach.offset.y,
                    transform.position.z
            )
        }

        // update graphic alpha value
        attach.entity[GraphicComponent.mapper]?.let { attachGraphic ->
            graphic.sprite.setAlpha(attachGraphic.sprite.color.a)
        }

    }
}