import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class HashMapTest {

    static class MockHashObject {
        private final int hashCode;

        public MockHashObject(int hashCode) {
            this.hashCode = hashCode;
        }

        @Override
        public int hashCode() {
            return hashCode;
        }
    }

    private HashMap<MockHashObject, Integer> emptyHM = new HashMap(4, 0.75f);
    private HashMap<MockHashObject, Integer> twoHM = new HashMap(2, 0.5f);
    private HashMap<MockHashObject, Integer> smallHM = new HashMap(4, 0.75f);
    private HashMap<MockHashObject, Integer> nullHM = new HashMap(4);
    private HashMap<MockHashObject, Integer> largeHM = new HashMap(8, 0.25f);
    private HashMap<MockHashObject, Integer> largerHM = new HashMap();
    private HashMap<MockHashObject, Integer> specHM = new HashMap(4, 0.75f);
    MockHashObject mho1 = new MockHashObject(1);
    MockHashObject mho2 = new MockHashObject(2);
    MockHashObject mho3 = new MockHashObject(3);
    MockHashObject mho4 = new MockHashObject(4);
    MockHashObject mho5 = new MockHashObject(5);
    MockHashObject mho9 = new MockHashObject(9);
    MockHashObject mho10 = new MockHashObject(10);
    MockHashObject mho12 = new MockHashObject(12);
    @Before
    public void setUpHashMapTest() {
        smallHM.put(mho1, 1);
        smallHM.put(mho2, 2);

        nullHM.put(null, null);

        largeHM.put(mho1, 1);
        largeHM.put(mho2, 2);
        largeHM.put(mho3, 3);
    }

    //size()
    @Test
    public void sizeEmptyHM() {
        assertEquals(0, emptyHM.size());
    }

    @Test
    public void sizeSmallHM() {
        assertEquals(2, smallHM.size());
    }

    @Test
    public void sizeIsNotPowerOf2() {
        HashMap<MockHashObject, Integer> notPowerTwoHM = new HashMap(17, 0.75f);
        assertEquals(32, notPowerTwoHM.getTable().length);
    }

    //get(), containsKey(), containsValve()
    @Test
    public void getContainsSmallHM() {
        HashMap<MockHashObject, Integer> containsHM = new HashMap(4, 0.75f);
        containsHM.put(mho1, 1);
        containsHM.put(mho2, 2);
        assertEquals(1, (int) containsHM.get(mho1));
        assertEquals(2, (int) containsHM.get(mho2));
        assertTrue(containsHM.containsKey(mho1));
        assertTrue(containsHM.containsKey(mho2));
        assertTrue(containsHM.containsValue(1));
        assertTrue(containsHM.containsValue(2));
        assertFalse(containsHM.containsKey(10));
        assertFalse(containsHM.containsValue(10));
    }

    @Test
    public void getPutSameKeySmallHM() {
        HashMap<MockHashObject, Integer> hm = new HashMap(4, 0.75f);
        hm.put(mho1, 1);
        hm.put(mho2, 2);
        assertTrue(hm.containsKey(mho1));
        assertTrue(hm.containsKey(mho2));
        hm.put(mho1, 10);
        hm.put(mho2, 20);
        assertEquals(10, (int) hm.get(mho1));
        assertEquals(20, (int) hm.get(mho2));
        assertTrue(hm.containsKey(mho1));
        assertTrue(hm.containsKey(mho2));
        assertFalse(hm.containsValue(1));
        assertFalse(hm.containsValue(2));
        assertTrue(hm.containsValue(10));
        assertTrue(hm.containsValue(20));
    }

    @Test
    public void getContainsNullHM() {
        assertNull(nullHM.get(null));
        assertTrue(nullHM.containsKey(null));
        assertTrue(nullHM.containsValue(null));

        nullHM.put(null, 1);
        assertNull(nullHM.get(mho1));
        assertTrue(nullHM.containsKey(null));
        assertFalse(nullHM.containsValue(null));
    }

    @Test
    public void tableZeroHasNullHM() {
        HashMap.Entry<MockHashObject, Integer>[] tst = nullHM.getTable();
        HashMap.Entry<MockHashObject, Integer> tstEntry
                = new HashMap.Entry<>(null, null, null);
        assertEquals(tstEntry, tst[0]);
        nullHM.put(null, 1);
        tstEntry = new HashMap.Entry<>(null, 1, null);
        assertEquals(tstEntry, tst[0]);
    }

    @Test
    public void getDoesNotExistSmallHM() {
        assertNull(smallHM.get(mho9));
    }

    //resize()
    @Test
    public void resizeSmallHM() {
        smallHM.put(mho10, 10);
        assertEquals(3, smallHM.size());
        assertEquals(8, smallHM.getTable().length);
    }

    //remove()
    @Test
    public void removeSmallHM() {
        smallHM.remove(mho1);
        assertEquals(1, smallHM.size());
        assertEquals(2, (int) smallHM.get(mho2));
        assertNull(smallHM.get(mho1));
        assertFalse(smallHM.containsKey(mho1));
        assertTrue(smallHM.containsKey(mho2));
        assertFalse(smallHM.containsValue(1));
        assertTrue(smallHM.containsValue(2));
        assertNull(smallHM.remove(mho1));
    }

    @Test
    public void removeNullHM() {
        assertEquals(1, nullHM.size());
        assertNull(nullHM.get(null));
        nullHM.remove(null);
        assertEquals(0, nullHM.size());
        assertFalse(smallHM.containsKey(null));
        assertFalse(smallHM.containsValue(null));
        assertNull(smallHM.remove(null));
    }

    @Test
    public void restOfCode() {
        HashMap.Entry<MockHashObject, Integer> entry = new HashMap.Entry<>(mho1, 1, null);
        entry.getKey();
        entry.getValue();
        entry.setValue(2);
        entry.equals(1);
        entry.hashCode();
    }

    @Test
    public void removeSpecHM() {
        specHM.put(mho1, 10);
        specHM.remove(mho1);
        HashMap.Entry<MockHashObject, Integer>[] tst = specHM.getTable();
        assertNull(tst[2]);

        specHM.put(mho1, 10);
        specHM.put(mho5, 11);
        HashMap.Entry<MockHashObject, Integer> tstEntryTwoNext
                = new HashMap.Entry<MockHashObject, Integer>(mho5, 11, null);
        HashMap.Entry<MockHashObject, Integer> tstEntry2
                = new HashMap.Entry<MockHashObject, Integer>(mho1, 10, tstEntryTwoNext);
        HashMap.Entry<MockHashObject, Integer>[] tst2 = specHM.getTable();
        assertEquals(tstEntry2, tst2[1]);

        specHM.remove(mho1);
        HashMap.Entry<MockHashObject, Integer> tstEntry3
                = new HashMap.Entry<MockHashObject, Integer>(mho5, 11, null);
        HashMap.Entry<MockHashObject, Integer>[] tst3 = specHM.getTable();
        assertEquals(tstEntry3, tst3[1]);
    }

    @Test
    public void resizeSpecHM() {
        specHM.put(mho1, 10);
        specHM.put(mho5, 12);
        specHM.put(mho12, 11);
        assertEquals(3, specHM.size());
        assertEquals(8, specHM.getTable().length);
    }

    @Test
    public void removeDoesNotExistSmallHM() {
        assertNull(smallHM.remove(mho9));
    }

    //clear()
    @Test
    public void clearSmallHM() {
        smallHM.clear();
        assertEquals(0, smallHM.size());
        assertEquals(16, smallHM.getTable().length);
    }

    @Test
    public void clearNullHM() {
        nullHM.clear();
        assertEquals(0, nullHM.size());
        assertEquals(16, nullHM.getTable().length);
    }

    @Test
    public void resizeSquareLargerHM() {
        largerHM.resize((int) Math.pow(16,2));
        assertEquals(0, largerHM.size());
        assertEquals(256, largerHM.getTable().length);
    }

    @Test
    public void putResetValueSmallHM() {
        largeHM.put(mho1,10);
        assertTrue(largeHM.containsValue(10));
        assertFalse(largeHM.containsValue(1));
    }

    //iterator()
    @Test
    public void iteratorSmallHM() {
        Iterator<Map.Entry<MockHashObject, Integer>> iterSmallEntry = smallHM.entryIterator();
        HashMap.Entry<MockHashObject, Integer> entry1
                = new HashMap.Entry<MockHashObject, Integer>(mho1, 1, null);
        assertTrue(iterSmallEntry.hasNext());
        assertEquals(entry1, iterSmallEntry.next());
        HashMap.Entry<MockHashObject, Integer> entry2
                = new HashMap.Entry<MockHashObject, Integer>(mho2, 2, null);
        assertTrue(iterSmallEntry.hasNext());
        assertEquals(entry2, iterSmallEntry.next());

        assertFalse(iterSmallEntry.hasNext());
    }

    @Test
    public void iteratorNullHM() {
        Iterator<Map.Entry<MockHashObject, Integer>> iterSmallEntry = nullHM.entryIterator();
        HashMap.Entry<MockHashObject, Integer> entry1
                = new HashMap.Entry<>(null, null, null);
        assertTrue(iterSmallEntry.hasNext());
        assertEquals(entry1, iterSmallEntry.next());

        assertFalse(iterSmallEntry.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorNoNextSmallHM() {
        Iterator<Map.Entry<MockHashObject, Integer>> iterSmallEntry = smallHM.entryIterator();
        iterSmallEntry.next();
        iterSmallEntry.next();
        iterSmallEntry.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void iteratorEmptyHM() {
        Iterator<Map.Entry<MockHashObject, Integer>> iterSmallEntry = emptyHM.entryIterator();
        assertFalse(iterSmallEntry.hasNext());
        assertNull(iterSmallEntry.next());
    }

    @Test
    public void sizeLoadFactorlargeHM() {
        largeHM.put(mho4, 4);
        assertEquals(4, largeHM.size());
        assertEquals(32, largeHM.getTable().length);
    }

    @Test (expected = IllegalArgumentException.class)
    public void zeroLoadFactor() {
        HashMap<MockHashObject, Integer> testingHM = new HashMap(4, 0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void negativeLoadFactor() {
        HashMap<MockHashObject, Integer> testingHM = new HashMap(4, -1f);
    }

    @Test
    public void overOneLoadFactor() {
        HashMap<MockHashObject, Integer> testingHM = new HashMap(4, 1.5f);
    }

    @Test (expected = IllegalArgumentException.class)
    public void zeroSize() {
        HashMap<MockHashObject, Integer> testingHM = new HashMap(0);
    }

    @Test
    public void resizeTwiceTwoHM() {
        assertEquals(0, twoHM.size());
        assertEquals(2, twoHM.getTable().length);

        twoHM.put(mho1, 1);
        assertEquals(1, twoHM.size());
        assertEquals(4, twoHM.getTable().length);

        twoHM.put(mho2, 2);
        assertEquals(2, twoHM.size());
        assertEquals(8, twoHM.getTable().length);
    }
}



