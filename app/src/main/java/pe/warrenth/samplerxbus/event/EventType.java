package pe.warrenth.samplerxbus.event;

/**
 * Created by warrenth on 2018-01-25.
 */

public class EventType {
    public static final String LOG = EventType.class.getSimpleName();
    public static final boolean DEBUG_MODE = true;

    public interface TAG {
        public static final String LEFT_MENU = "call_left_menu";
        public static final String RIGHT_MENU = "call_right_menu";
        public static final String ALL = "call_all";
        public static final String MIDDLE = "call_middle";
    }
}
