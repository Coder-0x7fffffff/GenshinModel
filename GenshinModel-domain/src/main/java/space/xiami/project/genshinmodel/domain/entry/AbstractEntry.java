package space.xiami.project.genshinmodel.domain.entry;

/**
 * @author Xiami
 */
public class AbstractEntry implements Entry {

    private double value;

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public double getValue() {
        return value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }
}
