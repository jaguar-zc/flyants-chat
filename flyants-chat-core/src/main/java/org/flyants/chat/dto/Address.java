package org.flyants.chat.dto;

/**
 * @Author zhangchao
 * @Date 2019/7/1 17:54
 * @Version v1.0
 */
public class Address {
    private String name;
    private String addreadd;

    public Address(String name, String addreadd) {
        this.name = name;
        this.addreadd = addreadd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddreadd() {
        return addreadd;
    }

    public void setAddreadd(String addreadd) {
        this.addreadd = addreadd;
    }
}
