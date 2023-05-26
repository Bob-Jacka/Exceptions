import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class JavaTest {
    static Product product1;
    static Product product2;
    static Product product3;
    static ShopRepository sr;

    @BeforeEach
    public void testEquipment() {
        product1 = new Product(1, "pen", 50);
        product2 = new Product(2, "apple", 19);
        product3 = new Product(3, "penApple", 42);
        sr = new ShopRepository();
        sr.add(product1);
        sr.add(product2);
        sr.add(product3);
    }

    @Test
    public void shouldDeleteOne() {
        ShopRepository test = new ShopRepository();
        test.add(product1);
        test.add(product2);

        sr.removeById(3);

        Assertions.assertArrayEquals(test.findAll(), sr.findAll());
    }

    @Test
    public void shouldDeleteTwo() {
        ShopRepository test = new ShopRepository();
        test.add(product1);

        sr.removeById(3);
        sr.removeById(2);

        Assertions.assertArrayEquals(test.findAll(), sr.findAll());
    }
    @Test
    public void shouldDeleteAll() {
        ShopRepository test = new ShopRepository();

        sr.removeById(3);
        sr.removeById(2);
        sr.removeById(1);
        Assertions.assertArrayEquals(test.findAll(), sr.findAll());
    }
    @Test
    public void shouldNotDelete() {
        sr.removeById(3);
        sr.removeById(2);
        sr.removeById(1);

        Assertions.assertThrows(NotFoundException.class, () -> sr.removeById(0));
    }

    @Test
    public void shouldThrowException() {
        Assertions.assertThrows(NotFoundException.class, () -> sr.removeById(15));
    }

    @Test
    public void shouldFindOnlyOneId() {
        Product act = sr.findById(1);
        Product exp = product1;

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldFindSeveralId() {
        Product act1 = sr.findById(1);
        Product act2 = sr.findById(2);
        Product exp1 = product1;
        Product exp2 = product2;

        Assertions.assertEquals(exp1, act1);
        Assertions.assertEquals(exp2, act2);
    }

    @Test
    public void shouldFindNullId() {
        Product act = sr.findById(5);

        Assertions.assertNull(act);
    }

    @Test
    public void shouldAlreadyExists() {
        Assertions.assertThrows(AlreadyExistsException.class, () -> sr.add(product2));
    }

    @Test
    public void shouldAdd() {
        ShopRepository test = new ShopRepository();
        Product product4 = new Product(4, "penPineAppleApplePen", 14);
        test.add(product1);
        test.add(product2);
        test.add(product3);
        test.add(product4);

        sr.add(product4);

        Assertions.assertEquals(4, sr.findAll().length);
        Assertions.assertArrayEquals(test.findAll(), sr.findAll());
    }

}
