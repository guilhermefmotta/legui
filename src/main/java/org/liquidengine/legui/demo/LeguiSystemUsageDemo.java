package org.liquidengine.legui.demo;

import org.liquidengine.legui.component.Button;
import org.liquidengine.legui.component.Label;
import org.liquidengine.legui.component.optional.TextState;
import org.liquidengine.legui.event.MouseClickEvent;
import org.liquidengine.legui.listener.impl.AbstractMouseClickEventListener;
import org.liquidengine.legui.style.color.ColorConstants;
import org.liquidengine.legui.system.LeguiSystem;
import org.liquidengine.legui.system.Window;

public class LeguiSystemUsageDemo {

    private static Integer[] clickedTimes;

    public static void main(String[] args) {
        System.setProperty("joml.nounsafe", Boolean.TRUE.toString());
        System.setProperty("java.awt.headless", Boolean.TRUE.toString());
        clickedTimes = new Integer[]{0};

        LeguiSystem.initialize();

        Window w1 = LeguiSystem.createWindow(200, 200, "HELLO");
        w1.setPosition(50, 50);
        w1.setVisible(true);
        createGUI(w1);
        w1.addCloseEventListener(event -> {
            System.out.println("CLOSE EVENT");
            LeguiSystem.destroyWindow(w1);
        });

        Window w2 = LeguiSystem.createWindow(200, 200, "HELLO 2");
        w2.setPosition(260, 50);
        createGUI(w2);
        w2.setVisible(true);
        w2.addCloseEventListener(event -> {
            System.out.println("CLOSE EVENT");
            LeguiSystem.destroyWindow(w2);
        });

        Button b = new Button("Clone context to first window", 10, 110, 180, 30);
        b.getListenerMap().addListener(MouseClickEvent.class, e -> w1.setFrame(w2.getFrame()));
        w2.getFrame().getContainer().add(b);
        w2.getFrame().getContainer().getStyle().getBackground().setColor(ColorConstants.lightBlue());

        while (!LeguiSystem.getWindows().isEmpty()) {
        }
        LeguiSystem.destroy();
    }

    private static void createGUI(Window window) {
        Button button = new Button("Button", 10, 20, 100, 30);
        //@formatter:off
        class TS extends TextState {
            public final String getText() {return genText(clickedTimes[0]);}
            public void setText(String text) {}
        }
        class L extends Label {
            private TS ts;
            public L(float x, float y, float width, float height) {super(x, y, width, height);}
            public TextState getTextState() {return ts != null ? ts : (ts = new TS());}
        }
        //@formatter:on
        Label l = new L(10, 60, 100, 30);
        button.getListenerMap().addListener(MouseClickEvent.class, new AbstractMouseClickEventListener() {
            public void onClick(MouseClickEvent event) {
                clickedTimes[0]++;
                l.getTextState().setText(genText(clickedTimes[0]));
            }
        });

        window.getFrame().getContainer().add(button);
        window.getFrame().getContainer().add(l);
    }

    private static String genText(Integer clickedTimes) {
        return "Button clicked: " + clickedTimes;
    }

}
