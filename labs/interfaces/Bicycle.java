/**
 * A bicycle.
 * @author Maxwell Terpstra <C0380979@intra.camosun.bc.ca>
 */
public class Bicycle {
    private final int speeds;
    private final boolean collapsible;
    private final boolean electricAssist;

    private static final int[] SIZE = { 120, 30, 150 };
    private static final int[] COLLAPSED_SIZE = { 50, 50, 50 };

    /**
     * Create a new Bicycle
     * @param speeds number of gear speeds
     * @param canCollapse whether or not the bicycle is collapsible
     * @param electric whether or not the bicycle has an electric assist motor
     */
    public Bicycle(int speeds, boolean canCollapse, boolean electric) {
        this.speeds = speeds;
        this.collapsible = canCollapse;
        this.electricAssist = electric;
    }

    /**
     * Get the dimensions of this bicycle, in centimeters.
     * @return a 3D array of measurements in centimeters.
     */
    public int[] size() {
        return SIZE;
    }

    /**
     * Get the minimum amount of space to store this bicycle.
     * @return a 3D array of measurements in centimeters.
     */
    public int[] storageSize() {
        if (collapsible) {
            return COLLAPSED_SIZE;
        } else {
            return SIZE;
        }
    }

    /**
     * Get the number of gear speeds this bicycle has.
     * @return number of speeds
     */
    public int speeds() { return this.speeds; }

    /**
     * Whether or not this bicycle has an electric drive system.
     * @return true if this bicycle needs to
     */
    public boolean needsElectricity() { return this.electricAssist; }
}
