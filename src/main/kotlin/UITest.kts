import mchorse.mappet.api.scripts.user.IScriptEvent


fun main(c: IScriptEvent){
    val ui = mappet.ui(c) {
        background()

        button {
            label = "my cool button"
            rxy(0.5f,0.5f).anchor(0.5f).wh(100,20)
        }
    }

    c.player.openUI(ui)
}