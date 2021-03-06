package org.hexworks.zircon.internal.component.renderer

import org.hexworks.zircon.api.component.renderer.ComponentRenderContext
import org.hexworks.zircon.api.component.renderer.ComponentRenderer
import org.hexworks.zircon.api.data.Tile
import org.hexworks.zircon.api.graphics.TileGraphics
import org.hexworks.zircon.internal.component.impl.DefaultVBox

class DefaultVBoxRenderer : ComponentRenderer<DefaultVBox> {

    override fun render(tileGraphics: TileGraphics, context: ComponentRenderContext<DefaultVBox>) {
        tileGraphics.fill(Tile.defaultTile())
        tileGraphics.applyStyle(context.currentStyle)
    }
}
