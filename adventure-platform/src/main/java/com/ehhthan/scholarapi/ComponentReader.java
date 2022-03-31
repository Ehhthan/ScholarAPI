package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.reader.FontReader;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentIteratorType;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.format.Style;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ComponentReader implements FontReader<Component> {
    // Public method to get width.
    @Override
    public int width(@NotNull final Component component) {
        return width(component, null);
    }

    // Width implementation
    private int width(@NotNull final Component component, @Nullable Style parent) {
        int width = 0;
//        Style style = (parent == null) ? component.style() : component.style().merge(parent, Style.Merge.Strategy.IF_ABSENT_ON_TARGET);
//
//        // Determines width of individual component.
//        if (component instanceof TranslatableComponent translatable) {
//            if (SpaceManager.isSpace(translatable)) {
//                width += SpaceManager.getWidth(translatable);
//            } else {
//                TranslationKey key = new TranslationKey(translatable.key());
//                if (TranslationManager.INSTANCE.has(key))
//                    width += TranslationManager.INSTANCE.get(key).getWidth();
//            }
//        } else if (component instanceof TextComponent text) {
//            for (char c : text.content().toCharArray())
//                width += FontManager.INSTANCE.get(style.font()).get(c).getWidth();
//        }
//
//        // If the component has children, recurse.
//        if (!component.children().isEmpty()) {
//            for (final Component child : component.children()) {
//                width += width(child, style);
//            }
//        }

        return width;
    }

    @Override
    public int height(Component component) {
        return 0;
    }
}
