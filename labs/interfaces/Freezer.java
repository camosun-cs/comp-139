/**
 * A stand-alone freezer unit.
 * @author Maxwell Terpstra
 */
public class Freezer {
    public enum Size {
        CHEST("5x3x4"),
        UPRIGHT("3x3x5"),
        DRAWER("2x2x4");
        private String actual;
        Size(String s) { this.actual = s; }
        String dimensions() { return this.actual; }
    }

    /**
     * Whether or not the freezer door is open.
     */
    public boolean open = false;

    private int temp;

    private final Size size;
    private final boolean electric;
    private final boolean waterFlow;

    /**
     * Constructs a Freezer of the given type.
     * @param s freezer size
     * @param electric whether or not the freezer uses electricity
     * @param water whether or not the freezer has a water outlet
     */
    public Freezer(Size s, boolean electric, boolean water) {
        this.size = s;
        this.temp = 1;
        this.electric = electric;
        this.waterFlow = water;
    }

    /**
     * Return the dimensions of this Freezer in feet as a
     * {@code <width>x<depth>x<height>} String value.
     * @return dimensions in feet
     */
    public String size() { return this.size.dimensions(); }

    /**
     * Whether or not this freezer requires an electrical hookup.
     * @return true if the freezer uses electricity
     */
    public boolean needsElectricity() { return this.electric; }

    /**
     * Whether or not this freezer needs to be connected to a water drain.
     * @return true if the freezer has a water outlet
     */
    public boolean needsPlumbing() { return this.waterFlow; }

    /**
     * Get the current temperature setting.
     * @return a unitless setting number, from 0 to 5
     */
    public int getTemperature() {
        return this.temp;
    }

    /**
     * Change the freezer temperature setting. A highter setting provides more
     * cooling, resulting in a lower actual temperature.
     * @param t temperature setting, from 0 to 5
     */
    public void setTemperature(int t) {
        if (t < 0 || t > 5) {
            throw new IllegalArgumentException("invalid temperature value");
        } else {
            this.temp = t;
        }
    }
}
