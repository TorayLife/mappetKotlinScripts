package toraylife

import button
import mchorse.mappet.api.scripts.user.IScriptEvent
import onClick
import ui


fun main(c: IScriptEvent){
    val ui = mappet.ui(c) {
        background()

        button("test") {
            label("my cool toraylife.button")
            rxy(0.5f,0.5f)
            anchor(0.5f)
            wh(100,20)
            onClick {
                this.send("Durak!");
            }
        }
    }
    c.player.openUI(ui)
}