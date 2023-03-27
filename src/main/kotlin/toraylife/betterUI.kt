import mchorse.mappet.api.scripts.user.IScriptEvent
import mchorse.mappet.api.scripts.user.IScriptFactory
import mchorse.mappet.api.scripts.user.mappet.IMappetUIBuilder
import mchorse.mappet.api.scripts.user.mappet.IMappetUIContext
import mchorse.mappet.api.ui.components.UIButtonComponent
import mchorse.mappet.api.ui.components.UIComponent
import mchorse.mappet.api.ui.components.UIIconButtonComponent
import mchorse.mappet.api.ui.components.UILabelComponent
import mchorse.mappet.api.ui.components.UIMorphComponent
import mchorse.mappet.api.ui.components.UIStackComponent
import mchorse.mappet.api.ui.components.UIStringListComponent
import mchorse.mappet.api.ui.components.UITextComponent
import mchorse.mappet.api.ui.components.UITextareaComponent
import mchorse.mappet.api.ui.components.UITextboxComponent
import mchorse.mappet.api.ui.components.UIToggleComponent
import mchorse.mappet.api.ui.components.UITrackpadComponent

class Callback {
    companion object {
        lateinit var onHandler: IScriptEvent.(IMappetUIContext) -> Unit
        private var registry = mutableMapOf<String, IScriptEvent.(IMappetUIContext) -> Unit>()

        fun execute(c: IScriptEvent) {
            val context = c.player.uiContext;
            registry[context.last]?.invoke(c, context)
            onHandler(c, context)
        }

        fun register(id: String, function: IScriptEvent.(IMappetUIContext) -> Unit) {
            registry[id] = function
        }
    }
}

fun handler(c: IScriptEvent) {
    Callback.execute(c)
}

fun IScriptFactory.ui(c: IScriptEvent, builder: IMappetUIBuilder.() -> Unit): IMappetUIBuilder {
    val mappetUI = createUI(c, "toraylife.handler")
    mappetUI.builder()
    return mappetUI
}

fun <T : UIComponent> T.buildUIComponent(id: String? = null, builder: T.() -> Unit): T {
    id?.let { id(id) }
    return apply(builder)
}

fun UIComponent.onClick(function: IScriptEvent.(IMappetUIContext) -> Unit) {
    Callback.register(id, function)
}

fun IMappetUIBuilder.div(name: String, builder: IMappetUIBuilder.() -> Unit) {
    builder()
}

fun IMappetUIBuilder.onHandler(func: IScriptEvent.(IMappetUIContext) -> Unit) {
    Callback.onHandler = func
}

fun IMappetUIBuilder.button(id: String? = null, builder: UIButtonComponent.() -> Unit): UIButtonComponent {
    return button("").buildUIComponent(id, builder)
}

fun IMappetUIBuilder.icon(id: String? = null, builder: UIIconButtonComponent.() -> Unit): UIIconButtonComponent {
    return icon("").buildUIComponent(id, builder)
}

fun IMappetUIBuilder.item(id: String? = null, builder: UIStackComponent.() -> Unit): UIStackComponent {
    return item().buildUIComponent(id, builder)
}

fun IMappetUIBuilder.label(id: String? = null, builder: UILabelComponent.() -> Unit): UILabelComponent {
    return label("").buildUIComponent(id, builder)
}

fun IMappetUIBuilder.morph(id: String? = null, builder: UIMorphComponent.() -> Unit): UIMorphComponent {
    return morph(mappet.createMorph("{}")).buildUIComponent(id, builder)
}

fun IMappetUIBuilder.stringList(id: String? = null, builder: UIStringListComponent.() -> Unit): UIStringListComponent {
    return stringList(mutableListOf<String>()).buildUIComponent(id, builder)
}

fun IMappetUIBuilder.text(id: String? = null, builder: UITextComponent.() -> Unit): UITextComponent {
    return text("").buildUIComponent(id, builder)
}

fun IMappetUIBuilder.textarea(id: String? = null, builder: UITextareaComponent.() -> Unit): UITextareaComponent {
    return textarea().buildUIComponent(id, builder)
}

fun IMappetUIBuilder.textbox(id: String? = null, builder: UITextboxComponent.() -> Unit): UITextboxComponent {
    return textbox().buildUIComponent(id, builder)
}

fun IMappetUIBuilder.toggle(id: String? = null, builder: UIToggleComponent.() -> Unit): UIToggleComponent {
    return toggle("").buildUIComponent(id, builder)
}

fun IMappetUIBuilder.trackpad(id: String? = null, builder: UITrackpadComponent.() -> Unit): UITrackpadComponent {
    return trackpad().buildUIComponent(id, builder)
}

fun IMappetUIBuilder.layout(id: String? = null, builder: IMappetUIBuilder.(current: UIComponent) -> Unit): IMappetUIBuilder {
    return layout().apply {
        id?.let { current.id(id) }
        builder(current)
    }
}

fun IMappetUIBuilder.row(margin: Int = 0, padding: Int = 0, id: String? = null, builder: IMappetUIBuilder.(current: UIComponent) -> Unit): IMappetUIBuilder {
    return row(margin, padding).apply {
        id?.let { current.id(id) }
        builder(current)
    }
}

fun IMappetUIBuilder.column(margin: Int = 0, padding: Int = 0, id: String? = null, builder: IMappetUIBuilder.(current: UIComponent) -> Unit): IMappetUIBuilder {
    return column(margin, padding).apply {
        id?.let { current.id(id) }
        builder(current)
    }
}