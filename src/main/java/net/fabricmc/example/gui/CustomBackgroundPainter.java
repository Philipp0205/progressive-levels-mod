package net.fabricmc.example.gui;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.client.ScreenDrawing;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;

public class CustomBackgroundPainter {

    public static BackgroundPainter SLOT2 = (matrices, left, top, panel) -> {
        if (!(panel instanceof WItemSlot)) {
            ScreenDrawing.drawBeveledPanel(matrices, left - 1, top - 1, panel.getWidth() + 2, panel.getHeight() + 2,
                    0xB8000000, 0x4C000000, 0xB8FFFFFF);
        } else {
            WItemSlot slot = (WItemSlot) panel;
            for (int x = 0; x < slot.getWidth() / 18; ++x) {
                for (int y = 0; y < slot.getHeight() / 18; ++y) {
                    int index = x + y * (slot.getWidth() / 18);
                    float px = 1 / 64f;
                    if (slot.isBigSlot()) {
                        int sx = (x * 18) + left - 4;
                        int sy = (y * 18) + top - 4;
                        ScreenDrawing.texturedRect(matrices, sx, sy, 26, 26, WItemSlot.SLOT_TEXTURE, 18 * px, 0,
                                44 * px, 26 * px, 0xFF_FFFFFF);
                        if (slot.getFocusedSlot() == index) {
                            ScreenDrawing.texturedRect(matrices, sx, sy, 26, 26, WItemSlot.SLOT_TEXTURE, 18 * px,
                                    26 * px, 44 * px, 52 * px, 0xFF_FFFFFF);
                        }
                    } else {
                        int sx = (x * 18) + left;
                        int sy = (y * 18) + top;
                        ScreenDrawing.texturedRect(matrices, sx, sy, 18, 18, WItemSlot.SLOT_TEXTURE, 0, 0, 18 * px,
                                18 * px, 0xFF_FFFFFF);
                        if (slot.getFocusedSlot() == index) {
                            ScreenDrawing.texturedRect(matrices, sx, sy, 18, 18, WItemSlot.SLOT_TEXTURE, 0, 26 * px,
                                    18 * px, 44 * px, 0xFF_FFFFFF);
                        }
                    }
                }
            }
        }
    };
}
