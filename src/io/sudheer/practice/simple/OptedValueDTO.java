package io.sudheer.practice.simple;

public class OptedValueDTO {
    private int rank;
    private int id;
    private String value;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public OptedValueDTO(int rank, int id, String value) {
        this.rank = rank;
        this.id = id;
        this.value = value;
    }

}
