package math;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.GenericArrayType;

public class ViewOperator {
    private Rectangle2D windowSizeScaled;
    private Dimension winSize;
    private Vector2D zoom = new Vector2D(1, 1);

    private final double ZOOM = 0.10;
    private Vector2D zoomDx;
    private Vector2D mouseDx;

    public ViewOperator() {
        this.winSize = new Dimension(0,0);
        this.windowSizeScaled = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
        zoomDx = new Vector2D();
        mouseDx = new Vector2D();
    }

    public ViewOperator(Dimension windowSize) {
        this();
        this.winSize = windowSize;
    }

    public void layScaledWindow() {

    }

    public void setWindowSize(Dimension windowSize) {
        this.winSize = windowSize;
        this.windowSizeScaled = new Rectangle2D.Double(0, 0, this.winSize.width, this.winSize.height);
        scaleWindow();
    }

    /**
     * Zoom method called everytime the user zooms in or out
     */
    public void zoom(double zoomx, double zoomy) {
        zoom.scaleX(1+zoomx);
        zoom.scaleY(1+zoomy);
        zoomDx.add(getZoomDisplacement(zoomx, zoomy));
    }

    /**
     * Returns the displacement of the screen due to the zoom passed applied to the current zoom.
     * @param zoomx
     * @param zoomy
     * @return
     */
    public Vector2D getZoomDisplacement(double zoomx, double zoomy) {
        double xscale = this.zoom.getX() * (1 - (1/(1+zoomx)));
        double yscale = this.zoom.getY() * (1- (1/(1+zoomy)));
        double dx = (winSize.width/2) * (xscale);
        double dy = (winSize.height/2) * (yscale);
        return new Vector2D(-dx, -dy);
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
        mouseDx.add(dx, dy);
    }

    public Dimension getWinSize() {
        return winSize;
    }

    public Rectangle2D getScaledWindow() {
        return this.windowSizeScaled;
    }

    public Rectangle2D getViewBox() {
        return new Rectangle2D.Double(
                mouseDx.getX(),
                mouseDx.getY(),
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
        return zoomDx.addR(mouseDx);
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
                ", displacement=" + mouseDx +
                '}';
    }
}
