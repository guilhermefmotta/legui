package org.liquidengine.legui.font;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Aliaksandr_Shcherbin on 1/26/2017.
 */
public class FontRegistry {
    public static final  String            ENTYPO                 = "entypo";
    public static final  String            ROBOTO_BOLD            = "roboto italic";
    public static final  String            ROBOTO_LIGHT           = "roboto-light";
    public static final  String            ROBOTO_REGULAR         = "roboto-regular";
    public static final  String            MATERIAL_ICONS_REGULAR = "MaterialIcons-Regular";
    public static final  String            FONT_AWESOME_ICONS     = "FontAwesomeIcons";
    public static final  String            MATERIAL_DESIGN_ICONS  = "materialdesignicons";
    public static final  String            DEFAULT                = ROBOTO_BOLD;
    private static final Map<String, Font> fontRegister           = new ConcurrentHashMap<>();

    static {
        registerFont(ENTYPO, "org/liquidengine/legui/font/entypo.ttf");
        registerFont(ROBOTO_BOLD, "org/liquidengine/legui/font/Roboto-Bold.ttf");
        registerFont(ROBOTO_LIGHT, "org/liquidengine/legui/font/Roboto-Light.ttf");
        registerFont(ROBOTO_REGULAR, "org/liquidengine/legui/font/Roboto-Regular.ttf");
        registerFont(MATERIAL_ICONS_REGULAR, "org/liquidengine/legui/font/MaterialIcons-Regular.ttf");
        registerFont(FONT_AWESOME_ICONS, "org/liquidengine/legui/font/FontAwesome.otf");
        registerFont(MATERIAL_DESIGN_ICONS, "org/liquidengine/legui/font/materialdesignicons.ttf");

        registerFont(DEFAULT, "org/liquidengine/legui/font/Roboto-Bold.ttf");
    }

    private FontRegistry() {
    }

    public static void registerFont(final String name, final String path) {
        Font font = new Font(path);
        fontRegister.put(name, font);
    }

    public static Map<String, Font> getFontRegister() {
        return new HashMap<>(fontRegister);
    }

    public static Font getFont(String name) {
        return fontRegister.get(name);
    }
}