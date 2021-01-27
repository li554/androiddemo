package com.example.animee.bean;

import java.util.List;

public class LuckBean {
    private String name;
    private String date;
    private int year;
    private MimaBean mima;
    private String luckeyStone;
    private String future;
    private String resultcode;
    private int error_code;
    private List<String> career;
    private List<String> love;
    private List<String> health;
    private List<String> finance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public MimaBean getMima() {
        return mima;
    }

    public void setMima(MimaBean mima) {
        this.mima = mima;
    }

    public String getLuckeyStone() {
        return luckeyStone;
    }

    public void setLuckeyStone(String luckeyStone) {
        this.luckeyStone = luckeyStone;
    }

    public String getFuture() {
        return future;
    }

    public void setFuture(String future) {
        this.future = future;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<String> getCareer() {
        return career;
    }

    public void setCareer(List<String> career) {
        this.career = career;
    }

    public List<String> getLove() {
        return love;
    }

    public void setLove(List<String> love) {
        this.love = love;
    }

    public List<String> getHealth() {
        return health;
    }

    public void setHealth(List<String> health) {
        this.health = health;
    }

    public List<String> getFinance() {
        return finance;
    }

    public void setFinance(List<String> finance) {
        this.finance = finance;
    }

    public static class MimaBean {
        /**
         * info : 精神层面比较紧张
         * text : ["对于绝大部分白羊而言，2020都是比较艰难的一年，尽管付出了很多努力，但获得的收获却非常不尽如人意。步入2021，落座于双鱼和水瓶座的木星在白羊们的星图之上高照，你会拥有许多丰富多彩的创意和想法，也能够一步步地将它们落实到位，生活中遇到的挫折会在木星这颗吉星的帮助下顺利化解。但是，火星的火热能量却会让你在不同的领域变得有些急性子，既不容易有条理地做好事情，也比较容易恼羞成怒甚至发生争执。不妨试着让自己宽容一些，抱着\u201c退一步海阔天空\u201d的心态度过这一年吧。白羊座今年可佩戴一个星盘保岁吉宏项链作为全年的幸运护身符饰物，银币铸造的船舵星符可提升白羊们的能量指数，寓意今年信心十足、目标明确、勇往直前！"]
         */

        private String info;
        private List<String> text;

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public List<String> getText() {
            return text;
        }

        public void setText(List<String> text) {
            this.text = text;
        }
    }
}
