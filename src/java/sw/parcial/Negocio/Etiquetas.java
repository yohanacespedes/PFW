/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sw.parcial.Negocio;

/**
 *
 * @author Rafael
 */
public class Etiquetas {

    public static String[] TYPE
            = {"TEXT", "PASSWORD", "CHECKBOX", "RADIO",
                "RESET", "SUBMIT", "IMAGE", "BUTTON"};
    public static String[] TAG_JSP_MENUS = {"ul", "select"};
    public static String[] TAG_JSP_MENU_ITEMS = {"ol", "li", "option"};
    public static String[] TAG = {"input", "textarea"};

    public static String[] TAG_JSF_MENU = { "menu", "submenu", "selectOneMenu"};
    public static String[] TAG_JSF_MENU_ITEMS = {"menuitem", "selectItem", "option"};

    public static String[] TAG_JSF = {"commandButton", "commandLink", "button", "inputText", "selectBooleanButton", "selectOneListbox"};
    public final static String FIN_MENU = "</ul>";
    public final static String FIN_OPTION = "</select>";

    public final static String FIN_MENU_JSF = "</p:menu>";
    public final static String FIN_OPTION_JSF = "</p:selectOneMenu>";
    public final static String FIN_SUBMENU_JSF = "</p:submenu>";

    // public final static String inicio = "<html>";
    public static boolean esEtiquetaJSP(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG[i])) {
                res = true;
            }
        }
        return res;

    }
    public static boolean esEtiqueta_JSF(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSF.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSF[i])) {
                res = true;
            }
        }
        return res;

    }

    public static boolean esMenuItems(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSP_MENU_ITEMS.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSP_MENU_ITEMS[i])) {
                res = true;
            }
        }
        return res;

    }
     public static boolean esMenuItems_JSF(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSF_MENU_ITEMS.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSF_MENU_ITEMS[i])) {
                res = true;
            }
        }
        return res;

    }

    public static boolean esEtiquetaJSF(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSF.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSF[i])) {
                res = true;
            }
        }
        return res;
    }

    public static boolean esMenu_JSF(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSF_MENU.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSF_MENU[i])) {
                res = true;
            }
        }
        return res;
    }

    public static boolean esMenuJSP(String tag) {
        boolean res = false;
        for (int i = 0; (i < TAG_JSP_MENUS.length) && (!res); i++) {
            if (tag.equalsIgnoreCase(TAG_JSP_MENUS[i])) {
                res = true;
            }
        }
        return res;
    }

    public static int getEtiquetaJSP(String tag) {

        for (int i = 0; (i < TYPE.length); i++) {
            if (tag.equalsIgnoreCase(TYPE[i])) {
                return i;
            }

        }
        return -1;

    }

}
