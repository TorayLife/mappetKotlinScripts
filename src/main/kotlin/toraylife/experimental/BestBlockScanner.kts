package toraylife.experimental

import mchorse.mappet.api.scripts.user.IScriptEvent
import mchorse.mappet.api.scripts.user.blocks.IScriptBlockState
import mchorse.mappet.api.scripts.user.data.ScriptVector

fun main(c: IScriptEvent) {
    val radius = 10
    val pos = c.player.position.blockVector

    val blocks = buildList<IScriptBlockState> {
        for (x in (pos.x - radius)..(pos.x + radius)) {
            for (y in (pos.y - radius)..(pos.y + radius)) {
                for (z in (pos.z - radius)..(pos.z + radius)) {
                    val block = c.world.getBlock(x,y,z)
                    add(block)
                }
            }
        }
    }

    val counts = blocks.groupingBy { it.blockId }.eachCount()
    val counts1 = blocks.groupBy { it.blockId }.mapValues { it.value.size }

    for ((id, count) in counts) {
        c.send("$id: $count")
    }
}

val ScriptVector.blockVector: ScriptBlockVector
   get() = ScriptBlockVector(x.toInt(), y.toInt(), z.toInt())


data class ScriptBlockVector(val x: Int, val y: Int, val z: Int)