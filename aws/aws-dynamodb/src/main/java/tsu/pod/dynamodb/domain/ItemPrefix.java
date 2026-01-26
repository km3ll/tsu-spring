package tsu.pod.dynamodb.domain;

public enum ItemPrefix {

    CID("CID#", "Customer Id");

    private final String prefix;
    private final String description;

    ItemPrefix(String prefix, String description) {
        this.prefix = prefix;
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getDescription() {
        return description;
    }

}
