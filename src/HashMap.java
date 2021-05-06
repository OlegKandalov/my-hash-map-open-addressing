import exceptions.KeyDoesNotExistException;

import java.util.Arrays;
import java.util.Objects;

public class HashMap {
    private Node[] arrayOfBuckets;
    private int length = 16;
    private int nodeCount = 0;
    private int probe = 0;


    public HashMap() {
        arrayOfBuckets = new Node[length];
    }

    public void put(int key, long value) {
        double filledOn = (double) nodeCount / length;
        double loadFactor = 0.75;
        if (filledOn >= loadFactor) {
            arrayOfBuckets = sizePlus();
        }

        Node node = new Node(key, value);
        if (isExists(key, value)) {
            return;
        }

        do {
            int indexOfBucket = getIndexForBucket(key);
            Node isNull = arrayOfBuckets[indexOfBucket];
            if (isNull == null) {
                arrayOfBuckets[indexOfBucket] = node;
                nodeCount++;
                probe = 0;
                return;
            }
            probe++;
        } while (probe < length);
    }

    public long get(int key) throws KeyDoesNotExistException {
        int index = getIndexForBucket(key);
        for (int i = index; i < arrayOfBuckets.length; i++) {
            if (!Objects.isNull(arrayOfBuckets[i])) {
                if (arrayOfBuckets[i].key == key) {
                    return arrayOfBuckets[i].value;
                }
            }
        }
        throw new KeyDoesNotExistException("This key does not exist");
    }

    public int size() {
        return nodeCount;
    }

    private int getIndexForBucket(int key) {
        String keyString = key + "";
        int hashCode = keyString.hashCode();
        return (hashCode + probe) % length;
    }

    private boolean isExists(int key, long value) {
        int index = getIndexForBucket(key);
        for (int i = index; i < arrayOfBuckets.length; i++) {
            if (!Objects.isNull(arrayOfBuckets[i])) {
                if (arrayOfBuckets[i].key == key) {
                    arrayOfBuckets[i].value = value;
                    return true;
                }
            }
        }
        return false;
    }

    private Node[] sizePlus() {
        length = length * 2;
        Node[] newArrayForBuckets = new Node[length];
        for (int i = 0; i < arrayOfBuckets.length; i++) {
            if (!Objects.isNull(arrayOfBuckets[i])) {
                newArrayForBuckets[i] = arrayOfBuckets[i];
            }
        }
        return newArrayForBuckets;
    }

    private static class Node {
        private int key;
        private long value;


        public Node(int key, long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }

    }

    @Override
    public String toString() {
        String[] allNodes = new String[nodeCount];
        int idx = 0;
        for (Node arrayOfBucket : arrayOfBuckets) {
            if (arrayOfBucket != null) {
                allNodes[idx++] = arrayOfBucket.toString();
            }
        }
        return Arrays.toString(allNodes);
    }
}
