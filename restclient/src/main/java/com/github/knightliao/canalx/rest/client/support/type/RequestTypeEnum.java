package com.github.knightliao.canalx.rest.client.support.type;

/**
 * @author knightliao
 * @date 2016/12/15 11:22
 */
public enum RequestTypeEnum {

    GET(0, "get"), POST(1, "post");

    private int type = 0;
    private String modelName = null;

    private RequestTypeEnum(int type, String modelName) {
        this.type = type;
        this.modelName = modelName;
    }

    public static RequestTypeEnum getByType(int type) {

        if (type == 0) {
            return RequestTypeEnum.GET;
        } else if (type == 1) {
            return RequestTypeEnum.POST;
        }

        return null;
    }

    public static RequestTypeEnum getByName(String name) {

        for (RequestTypeEnum protocolEnum : values()) {

            if (protocolEnum.getModelName().equals(name)) {
                return protocolEnum;
            }
        }

        return null;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

}
