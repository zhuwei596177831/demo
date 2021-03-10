package com.example.mianshi.designpatterns.builder;

/**
 * @author 朱伟伟
 * @date 2021-03-08 15:57:45
 * @description
 */
public class Computer {
    private final String id;//必填
    private final String name;//必填
    private final String email;//选填
    private final String address;//选填

    private Computer(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.address = builder.address;
    }

    public static final class Builder {
        private String id;//必填
        private String name;//必填
        private String email;//选填
        private String address;//选填

        public Builder() {

        }

        public Builder(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Builder setId(String id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        /**
         * @author: 朱伟伟
         * @date: 2021-03-08 17:04
         * @description: 有些重要的参数校验需要在最后一步build()里去做。而且这个模式的好处就是隐藏了对象本身，
         * 创建完之后就无法再修改这个对象内部了。这些都是单纯的new完set是无法做到的。
         **/
        public Computer build() {
            return new Computer(this);
        }

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }
}
