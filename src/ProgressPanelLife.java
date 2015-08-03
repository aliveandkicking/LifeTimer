import javafx.scene.text.*;

import java.awt.*;
import java.awt.Font;

public class ProgressPanelLife extends ProgressPanelBase {
    @Override
    protected void  onPaint(Graphics2D g){

        BasicStroke p2 = new BasicStroke(2);
        g.setStroke(p2);

        Integer i = 0;
        while (i<=80){

            Double x = 110 * Math.sin(Math.toRadians((i*4.5)));
            Double y = 110 * Math.sin(Math.toRadians(90- (i*4.5)));

            if ((i % 5) == 0) {
                g.drawString(i.toString(), 125 + x.intValue(), 160 - y.intValue());
            }
            i++;

        }

    }
}
