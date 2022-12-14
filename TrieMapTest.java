import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TrieMapTest {
    private TrieMap<String> smallTM = new TrieMap<>();
    private TrieMap<String> penguinTM = new TrieMap<>();
    private TrieMap<String> emptyTM = new TrieMap<>();
    private TrieMap<String> largeTM = new TrieMap<>();

    @Before
    public void setUpTrieMapTest() {
        smallTM.put("hello", "1");
        smallTM.put("root", "2");
        smallTM.put("help", "3");
        smallTM.put("he", "4");

        penguinTM.put("pen", "1");
        penguinTM.put("penguin", "2");

        largeTM.put("hello", "1");
        largeTM.put("root", "2");
        largeTM.put("help", "3");
        largeTM.put("he", "4");
        largeTM.put("", "5");
        largeTM.put("roots", "");
    }

    @Test (expected = IllegalArgumentException.class)
    public void wrongAlphabet() {
        String testKey = "0";
        smallTM.put(testKey, "1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void wronglargeAlphabet() {
        smallTM.put("A", "1");
    }

    //put()
    @Test (expected = IllegalArgumentException.class)
    public void putKeyNull() {
        smallTM.put(null, "1");
    }

    @Test (expected = IllegalArgumentException.class)
    public void putErrorEmptyTM1() {
        emptyTM.put(" hi", "1");

    }

    @Test (expected = IllegalArgumentException.class)
    public void putErrorEmptyTM2() {
        emptyTM.put("h ello", "2");
    }

    @Test (expected = IllegalArgumentException.class)
    public void putErrorEmptyTM3() {
        emptyTM.put("hello ", "3");
    }

    @Test
    public void putKeySmallTM() {
        String ans = "4";
        assertEquals(ans, smallTM.put("he", "4"));
    }

    @Test
    public void putKeyNullSmallTM() {
        assertNull(smallTM.put("trie", "1"));
    }

    @Test
    public void sizeSmallTM() {
        assertEquals(4, smallTM.size());
    }

    @Test (expected = IllegalArgumentException.class)
    public void putValueNull() {
        smallTM.put("ro", null);
    }

    //get()
    @Test
    public void getSmallTM() {
        String ans1 = "1";
        String ans2 = "2";
        String ans3 = "3";
        String ans4 = "4";
        assertEquals(ans1, smallTM.get("hello"));
        assertEquals(ans2, smallTM.get("root"));
        assertEquals(ans3, smallTM.get("help"));
        assertEquals(ans4, smallTM.get("he"));
        assertEquals(4, smallTM.size());
    }

    @Test
    public void putReplaceSmallTM() {
        smallTM.put("hello", "5");

        assertNotEquals("1", smallTM.get("hello"));
        assertEquals("5", smallTM.get("hello"));
        assertEquals(4, smallTM.size());

        smallTM.put("", "6");
        assertEquals(5, smallTM.size());

        smallTM.remove("");
        assertEquals(4, smallTM.size());
    }

    @Test
    public void getLargeTM() {
        String ans5 = "5";
        String ans = "";
        assertEquals(ans5, largeTM.get(""));
        assertEquals(ans, largeTM.get("roots"));
        assertEquals(6, largeTM.size());
        assertEquals("5", largeTM.getRoot().getValue());
    }

    @Test
    public void getNullSmallTM() {
        assertNull(smallTM.get("h"));
        assertNull(smallTM.get("helloworld"));
        assertNull(smallTM.get("a"));
        assertNull(smallTM.get(""));
        assertNull(smallTM.get("roots"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void getKeyNull() {
        smallTM.get(null);
    }

    //containsKey()
    @Test
    public void containsKeySmallTM() {
        assertTrue(smallTM.containsKey("hello"));
        assertTrue(smallTM.containsKey("root"));
        assertTrue(smallTM.containsKey("help"));
        assertTrue(smallTM.containsKey("he"));

        assertFalse(smallTM.containsKey("h"));
        assertFalse(smallTM.containsKey("helloworld"));
        assertFalse(smallTM.containsKey("a"));
        assertFalse(smallTM.containsKey(""));
        assertFalse(smallTM.containsKey("roots"));
    }

    @Test
    public void containsKeyLargeTM() {
        assertTrue(largeTM.containsKey(""));
        assertTrue(largeTM.containsKey("roots"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void containsKeyKeyNull() {
        smallTM.containsKey(null);
    }

    //containsValue()
    @Test
    public void containsValueSmallTM() {
        String ans1 = "1";
        String ans2 = "2";
        String ans3 = "3";
        String ans4 = "4";
        String ans124 = "124";

        assertTrue(smallTM.containsValue(ans1));
        assertTrue(smallTM.containsValue(ans2));
        assertTrue(smallTM.containsValue(ans3));
        assertTrue(smallTM.containsValue(ans4));

        assertFalse(smallTM.containsValue(ans124));
    }

    @Test
    public void containsValueLargeTM() {
        String ans5 = "5";
        String ans = "";
        assertTrue(largeTM.containsValue(ans5));
        assertTrue(largeTM.containsValue(ans));
    }

    @Test(expected = IllegalArgumentException.class)
    public void containsValueValueNull() {
        smallTM.containsValue(null);
    }

    //remove()
    @Test(expected = IllegalArgumentException.class)
    public void removeKeyNull() {
        smallTM.remove(null);
    }

    @Test
    public void removePenguinTM() {
        String ans1 = "1";
        String ans2 = "2";
        assertEquals(ans1, penguinTM.get("pen"));
        assertEquals(ans2, penguinTM.get("penguin"));
        penguinTM.remove("penguin");

        assertEquals(ans1, penguinTM.get("pen"));
        assertNull(penguinTM.get("penguin"));

        assertEquals(1, penguinTM.size());
    }

    @Test
    public void removeSmallTM() {
        String ans1 = "1";
        String ans2 = "2";
        String ans3 = "3";
        String ans4 = "4";

        assertEquals(ans2, smallTM.remove("root"));
        assertEquals(3, smallTM.size());

        assertEquals(ans1, smallTM.remove("hello"));
        assertEquals(2, smallTM.size());

        assertEquals(ans4, smallTM.remove("he"));
        assertEquals(1, smallTM.size());

        assertEquals(ans3, smallTM.remove("help"));
        assertEquals(0, smallTM.size());
    }

    @Test
    public void removeLargeTM() {
        String ans5 = "5";
        String ans = "";
        assertEquals(ans5, largeTM.remove(""));
        assertEquals(ans, largeTM.get("roots"));
    }

    @Test
    public void addCoverage() {
        TrieMap.Node<String> node = new TrieMap.Node<String>("1");
        node.initChildren();
        node.getChildren();
        node.hasChildren();
        node.getChild('a');
        node.hasChild('a');
        node.setChild('a', node);
        node.hasValue();
        node.getValue();
        node.setValue("10");
    }

    @Test
    public void removeErrorPenguinTM() {
        assertNull(smallTM.remove(""));
        assertNull(smallTM.remove("r"));
        assertNull(smallTM.remove("abc"));
        assertNull(smallTM.remove("helloworld"));
    }
}


