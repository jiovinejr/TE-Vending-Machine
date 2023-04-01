import com.techelevator.controller.Financial;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class FinancialTest {
    Financial test = new Financial();

    @Test
    public void testGiveChange_1d40c_returns_change_split(){
        Assert.assertEquals("Dispensing: 1 dollars, 1 quarters, 1 dimes, and 1 nickles.", Financial.giveChange(new BigDecimal("1.40")));
    }
    @Test
    public void testGiveChange_0_returns_0(){
        Assert.assertEquals("Dispensing: 0 dollars, 0 quarters, 0 dimes, and 0 nickles.", Financial.giveChange(new BigDecimal("0")));
    }
    @Test
    public void testGiveChange_0d30c_returns_change_split(){
        Assert.assertEquals("Dispensing: 0 dollars, 1 quarters, 0 dimes, and 1 nickles.", Financial.giveChange(new BigDecimal("0.30")));
    }
    @Test
    public void testApplyDiscount_1d30c_returns_0d30c(){
        Assert.assertEquals(new BigDecimal("0.30"), Financial.applyDiscount(new BigDecimal("1.30")));
    }

    @Test
    public void testApplyDiscount_0d0c_returns_0d0c(){
        Assert.assertEquals(new BigDecimal("0.00"), Financial.applyDiscount(new BigDecimal("0.00")));
    }
    @Test
    public void testApplyDiscount_1d00c_returns_0d00c(){
        Assert.assertEquals(new BigDecimal("0.00"), Financial.applyDiscount(new BigDecimal("1.00")));
    }
    @Test
    public void testApplyDiscount_d40c_returns_0d40c(){
        Assert.assertEquals(new BigDecimal("0.40"), Financial.applyDiscount(new BigDecimal("0.40")));
    }

}
