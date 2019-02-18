package ckmbks.demo.domain.user.enums;

public enum Sex {
    Man{
        public String getDesc(){
            return "男性";
        }
    },
    Woman{
        public String getDesc(){
            return "女性";
        }
    };

    public abstract String getDesc();
    public String getName(){
        return this.name();
    }
}