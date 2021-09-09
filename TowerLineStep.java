public class TowerLineStep implements ILineStep{
    @Override
    public IProductPart buildProductPart() {
        return new Tower();
    }
}
