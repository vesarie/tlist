package tlist.models;

public enum Priority {

    Level1(1), Level2(2), Level3(3), Level4(4);

    public static final int[] list = new int[]{1, 2, 3, 4};
    public static final Priority defaultPriority = Level4;

    int value;

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
            case 1: return Level1;
            case 2: return Level2;
            case 3: return Level3;
            case 4: return Level4;
        }

        return defaultPriority;
    }

}
