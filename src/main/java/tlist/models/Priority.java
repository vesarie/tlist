package tlist.models;

public enum Priority {

    p1(1), p2(2), p3(3), p4(4);

    public static final int[] list = new int[]{1, 2, 3, 4};
    public static final Priority defaultPriority = p4;

    final int value;

    private Priority(int value) {
        this.value = value;
    }

    public int toInt() {
        return value;
    }
    
    public int getInteger() {
        return value;
    }

    public static Priority convert(int priority) {
        switch (priority) {
            case 1: return p1;
            case 2: return p2;
            case 3: return p3;
            case 4: return p4;
        }

        return defaultPriority;
    }

}
