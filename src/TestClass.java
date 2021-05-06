import exceptions.KeyDoesNotExistException;
import org.junit.*;
import org.junit.Test;

public class TestClass {

    @Test(expected = KeyDoesNotExistException.class)
    public void shouldReturnKeyDoesNotExistException() throws KeyDoesNotExistException {
        HashMap testHashMap = new HashMap();
        testHashMap.put(1, 4);
        testHashMap.put(2, 5);
        testHashMap.get(4);
    }

    @Test
    public void shouldIncreaseSizeOfArray() {
        HashMap testHashMap = new HashMap();
        Assert.assertEquals(16, testHashMap.getLength());
        for (int i = 0; i < 17; i++) {
            testHashMap.put(i + 1, i);
        }
        Assert.assertEquals(32, testHashMap.getLength());
    }

    @Test
    public void shouldReturnValueByKey() throws KeyDoesNotExistException {
        HashMap testHashMap = new HashMap();
        testHashMap.put(2, 5);
        testHashMap.put(4, 6);
        long actual = testHashMap.get(2);
        Assert.assertEquals(5, actual);
    }

    @Test
    public void shouldPutKeyWithValue() {
        HashMap testHashMap = new HashMap();
        Assert.assertEquals(testHashMap.size(), 0);
        testHashMap.put(2, 5);
        Assert.assertEquals(1, testHashMap.size());
    }

    @Test
    public void shouldReturnNumberOfFilledCells() {
        HashMap testHashMap = new HashMap();
        for (int i = 0; i < 17; i++) {
            testHashMap.put(i + 1, i);
        }
        Assert.assertEquals(17, testHashMap.size());
    }

    @Test
    public void shouldFindFreeCellOnCollision() {
        HashMap testHashMap = new HashMap();
        int indexOf4 = testHashMap.getIndexForBucket(4);
        int indexOf444 = testHashMap.getIndexForBucket(444);

        Assert.assertEquals(indexOf4, indexOf444);

        testHashMap.put(4, 12);
        testHashMap.put(444, 555);

        Assert.assertEquals(2, testHashMap.size());
        System.out.println(testHashMap);
    }

    @Test
    public void shouldRewriteValueWithSameKey() throws KeyDoesNotExistException {
        HashMap testHashMap = new HashMap();
        testHashMap.put(22, 12);
        testHashMap.put(22, 15);

        Assert.assertEquals(15, testHashMap.get(22));
    }
}
