package tsu.pod.dynamodb.domain;

public enum ItemType {

    CUSTOMER("customer");

    private final String value;

    ItemType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
