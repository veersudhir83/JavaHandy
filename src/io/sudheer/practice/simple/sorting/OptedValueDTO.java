package io.sudheer.practice.simple.sorting;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OptedValueDTO sample = (OptedValueDTO) o;

        if (!value.equals(sample.value)) return false;
        return value.equals(sample.value);

    }

    @Override
    public int hashCode() {
        int result = value.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }


}
