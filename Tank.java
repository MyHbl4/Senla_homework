public class Tank implements IProduct {

    private IProductPart body;
    private IProductPart engine;
    private IProductPart tower;

    public Tank(IProductPart body, IProductPart engine, IProductPart tower) {
        this.body = body;
        this.engine = engine;
        this.tower = tower;
    }

    public Tank() {
    }

    @Override
    public void installFirstPart(IProductPart productPart) {
        this.body = productPart;
        System.out.println("Корпус установлен.");
    }

    @Override
    public void installSecondPart(IProductPart productPart) {
        this.engine = productPart;
        System.out.println("Двигатель установлен.");

    }

    @Override
    public void installThirdPart(IProductPart productPart) {
        this.tower = productPart;
        System.out.println("Башня установлена.");

    }
}
