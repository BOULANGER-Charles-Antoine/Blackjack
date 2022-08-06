public class Gain {
    private int gain;
    private int mise;

    public Gain() {
        this.gain = 0;
        this.mise = 0;
    }

    public Gain(int gain, int mise) {
        this.gain = gain;
        this.mise = mise;
    }

    public int getGain() {
        return gain;
    }

    public int getMise() {
        return mise;
    }

    public void setGain(int gain) {
        this.gain = gain;
    }

    public void setMise(int mise) {
        this.mise = mise;
    }
}
