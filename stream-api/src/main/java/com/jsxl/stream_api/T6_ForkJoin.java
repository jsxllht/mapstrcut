package com.jsxl.stream_api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.Optional;
@Data
@AllArgsConstructor
@NoArgsConstructor
class Goodness {
    private String Name;
}
@Data
@AllArgsConstructor
@NoArgsConstructor
class Man{
    private String name;
    private Goodness goodness;
}

class NewMan {
    private Optional<Goodness> goodness = Optional.empty();

    public NewMan() {
    }

    public NewMan(Optional<Goodness> goodness) {
        this.goodness = goodness;
    }

    public Optional<Goodness> getGoodness() {
        return goodness;
    }

    public void setGoodness(Optional<Goodness> goodness) {
        this.goodness = goodness;
    }

    @Override
    public String toString() {
        return "NewMan{" +
                "goodness=" + goodness +
                '}';
    }
}
public class T6_ForkJoin{
        @Test
        public void test1(){
                Man man = new Man();
                System.out.println(getGoodnessName(man));

                Optional<Goodness> gn = Optional.ofNullable(new Goodness("自带对象"));
                Optional<NewMan> op = Optional.ofNullable(new NewMan(gn));
        //        Optional<NewMan> op = Optional.ofNullable(new NewMan());
                System.out.println(getGoodnessName2(op));
                }
        //获取一个男人心中女神的名字
        public String getGoodnessName(Man man){
                if(man != null){
                Goodness gn = man.getGoodness();
                if (gn != null){
                return gn.getName();
                }
                }
                return "新对象";
                }
        public String getGoodnessName2(Optional<NewMan> man){
                return man.orElse(new NewMan())
                .getGoodness()
                .orElse(new Goodness("新对象"))
                .getName();
                }
}
