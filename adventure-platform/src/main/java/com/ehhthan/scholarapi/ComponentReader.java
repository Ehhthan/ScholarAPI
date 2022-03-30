package com.ehhthan.scholarapi;

import com.ehhthan.scholarapi.reader.FontReader;
import com.ehhthan.scholarapi.resourcepack.ResourcePack;
import com.google.inject.Inject;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentIteratorType;

public class ComponentReader implements FontReader<Component> {
    @Override
    public int width(Component component) {
        return 0;
    }

    @Override
    public int height(Component component) {
        return 0;
    }
}
