package com.github.knightliao.canalx.rest.client.protocol;

/**
 * @author liaoqiqi
 * @version 2014-8-30
 */
public enum ProtocolEnum {

    JACKSON(0, "json");

    private int type = 0;
    private String modelName = null;

    private ProtocolEnum(int type, String modelName) {
        this.type = type;
        this.modelName = modelName;
    }

    public static ProtocolEnum getByType(int type) {

        if (type == 0) {
            return ProtocolEnum.JACKSON;
        }

        return ProtocolEnum.JACKSON;
    }

    public static ProtocolEnum getByName(String name) {

        for (ProtocolEnum protocolEnum : values()) {

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
