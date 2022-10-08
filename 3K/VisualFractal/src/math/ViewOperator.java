package math;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class ViewOperator {
    private Rectangle2D windowSizeScaled;
    private Dimension winSize;
    private Vector2D zoom = new Vector2D(1, 1);

    private final double ZOOM = 0.10;

    private Vector2D displacement = new Vector2D();

    public ViewOperator(Dimension windowSize) {
        this.winSize = windowSize;
        this.windowSizeScaled = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
    }

    public ViewOperator() {
        this.winSize = new Dimension(0,0);
        this.windowSizeScaled = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
    }

    public void layScaledWindow() {

    }

    public void setWindowSize(Dimension windowSize) {
        this.winSize = windowSize;
        this.windowSizeScaled = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
        scaleWindow();
    }

    public void zoom(double zoomx, double zoomy) {
        double xprevZoom = this.zoom.getX();
        double yprevZoom = this.zoom.getY();
        zoom.scaleX(1+zoomx);
        zoom.scaleY(1+zoomy);
        double dx = (this.winSize.width + displacement.getX()) * (zoom.getX() - xprevZoom);
        double dy = (this.winSize.height + displacement.getY()) * (zoom.getY() - yprevZoom);
        displacement.add(-dx, -dy);
    }

    public void zoomXUnits(int units) {
        double scaleXFactor = ZOOM*units;
        zoom(scaleXFactor, 1);
    }

    public void zoomYUnits(int units) {
        double scaleXFactor = ZOOM*units;
        zoom(1, scaleXFactor);
    }

    public void zoomUnits(int units) {
        double scaleFactor = ZOOM*units;
        zoom(scaleFactor, scaleFactor);
    }

    public void scaleWindow() {
        this.windowSizeScaled.setRect(
                windowSizeScaled.getX(),
                windowSizeScaled.getY(),
                windowSizeScaled.getWidth() * zoom.getX(),
                windowSizeScaled.getHeight() * zoom.getY()
        );
    }

    public void translation(double dx, double dy) {
        displacement.add(dx, dy);
    }

    public Dimension getWinSize() {
        return winSize;
    }

    public Rectangle2D getScaledWindow() {
        return this.windowSizeScaled;
    }

    public Rectangle2D getViewBox() {
        return new Rectangle2D.Double(
                displacement.getX(),
                displacement.getY(),
                winSize.width,
                winSize.height
        );
    }

    public Rectangle2D getDrawBox() {
        Rectangle2D windowBox = new Rectangle2D.Double(0, 0, windowSizeScaled.getWidth(), windowSizeScaled.getHeight());
        Rectangle2D viewBox = getViewBox();
        return viewBox.createIntersection(windowBox);
    }

    public Vector2D getDisplacement() {
        return displacement;
    }

    public Vector2D getZoom() {
        return zoom;
    }

    @Override
    public String toString() {
        return "ViewOperator{" +
                "windowSizeScaled=" + windowSizeScaled +
                ", winSize=" + winSize +
                ", zoom=" + zoom +
                ", displacement=" + displacement +
                '}';
    }
}
