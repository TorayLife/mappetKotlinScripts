package toraylife.experimental

import mchorse.mappet.api.scripts.user.IScriptEvent
import mchorse.mappet.api.scripts.user.blocks.IScriptBlockState
import mchorse.mappet.api.scripts.user.data.ScriptVector

fun main(c: IScriptEvent) {
    val radius = 10
    val pos = c.player.position.blockVector

    val blocks = buildList<IScriptBlockState> {
        for (x in (pos.x - 10)..(pos.x + 10)) {
            for (y in (pos.y - 10)..(pos.y + 10)) {
                for (z in (pos.z - 10)..(pos.z + 10)) {
                    val block = c.world.getBlock(x,y,z)
                    add(block);
                }
            }
        }
    }

    val ids = buildSet<String> {
        for (block in blocks) {
            add(block.blockId)
        }
    }

    val counts = mutableMapOf<String, Int>()

    for (block in blocks) {
        val id = block.blockId
        counts[id] = (counts[id] ?: 0) + 1
    }


}

val ScriptVector.blockVector: ScriptBlockVector
   get() = ScriptBlockVector(x.toInt(), y.toInt(), z.toInt())


data class ScriptBlockVector(val x: Int, val y: Int, val z: Int)