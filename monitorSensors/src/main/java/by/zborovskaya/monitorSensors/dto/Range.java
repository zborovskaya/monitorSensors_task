package by.zborovskaya.monitorSensors.dto;

import io.swagger.annotations.ApiModelProperty;

public class Range {
    @ApiModelProperty(example = "22", required = false)
    private int from;
    @ApiModelProperty(example = "45", required = false)
    private int to;

    public Range(int from, int to) {
        this.from = from;
        this.to = to;
    }
    public Range() {

    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Range{" +
                "from=" + from +
                ", to=" + to +
                '}';
    }
}
