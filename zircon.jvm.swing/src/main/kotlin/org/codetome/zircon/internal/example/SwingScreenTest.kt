package org.codetome.zircon.gui.swing.internal.example

import org.codetome.zircon.api.builder.grid.AppConfigBuilder
import org.codetome.zircon.api.data.*
import org.codetome.zircon.api.grid.TileGrid
import org.codetome.zircon.api.resource.CP437TilesetResource
import org.codetome.zircon.internal.application.SwingApplication
import org.codetome.zircon.internal.graphics.DefaultLayer
import org.codetome.zircon.internal.graphics.MapTileImage
import org.codetome.zircon.internal.screen.TileGridScreen
import java.util.*

fun main(args: Array<String>) {

    val size = Size.create(80, 40)

    val tileset = CP437TilesetResource.WANDERLUST_16X16

    val app = SwingApplication.create(
            AppConfigBuilder.newBuilder()
                    .defaultSize(size)
                    .defaultTileset(tileset)
                    .debugMode(true)
                    .build())

    app.start()

    val screen = TileGridScreen(app.tileGrid)

    screen.display()

    val random = Random()
    val terminalWidth = size.xLength
    val terminalHeight = size.yLength
    val layerCount = 20
    val layerWidth = 20
    val layerHeight = 10
    val layerSize = Size.create(layerWidth, layerHeight)
    val filler = CharacterTile('x')

    val layers = (0..layerCount).map {

        val imageLayer = MapTileImage(layerSize, tileset)
        layerSize.fetchPositions().forEach {
            imageLayer.setTileAt(it, filler)
        }

        val layer = DefaultLayer(
                position = Position.create(
                        x = random.nextInt(terminalWidth - layerWidth),
                        y = random.nextInt(terminalHeight - layerHeight)),
                backend = imageLayer)

        screen.pushLayer(layer)
        layer
    }

    val chars = listOf('a', 'b')

    var currIdx = 0


    while (true) {
        val tile = CharacterTile(chars[currIdx])
        fillGrid(screen, tile)
        layers.forEach {
            it.moveTo(Position.create(
                    x = random.nextInt(terminalWidth - layerWidth),
                    y = random.nextInt(terminalHeight - layerHeight)))
        }
        currIdx = if (currIdx == 0) 1 else 0
    }
}


private fun fillGrid(tileGrid: TileGrid, tile: Tile) {
    (0..tileGrid.getBoundableSize().yLength).forEach { y ->
        (0..tileGrid.getBoundableSize().xLength).forEach { x ->
            tileGrid.setTileAt(GridPosition(x, y), tile)
        }
    }
}